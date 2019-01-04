package com.example.demo.config;

import com.example.demo.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String SIGNING_KEY = "cyferki";
    private final String AUTHORITIES_KEY = "scopes";
    private final String HEADER_STRING = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    public final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;


    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        if(header != null && header.startsWith(TOKEN_PREFIX)){
            authToken = header.replace(TOKEN_PREFIX, "");
            try {
                username = tokenProvider.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("error during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("token expired", e);
            } catch(SignatureException e) {
                logger.error("username or password invaild");
            }
        } else {
            logger.warn("header will be ignored");
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);

            if(tokenProvider.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        tokenProvider.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("authenticated user: " + username);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
