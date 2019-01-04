package com.example.demo.rest;

import com.example.demo.config.AuthToken;
import com.example.demo.config.TokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.user.CrmUser;
import com.example.demo.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class UserRestController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private TokenProvider tokenProvider;

//	@PostMapping("/api/register")
//	public CrmUser registerUser(@RequestBody CrmUser crmUser) {
//		logger.info(">>>>Processing user: " + crmUser);
//		logger.info(">>>>Encoded password: "
//				+ passwordEncoder.encode(crmUser.getPassword()));
//		User existing = userService.findByUserName(crmUser.getUserName());
//		if (existing != null) {
//			return new CrmUser();
//		} else {
//			userService.save(crmUser);
//		}
//
//		return crmUser;
//	}

	@PostMapping("/api/register")
	public ResponseEntity<?> registerUser(@RequestBody CrmUser crmUser) {
		logger.info(">>>>Processing user: " + crmUser);
		logger.info(">>>>Encoded password: "
				+ passwordEncoder.encode(crmUser.getPassword()));

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						crmUser.getUserName(),
						crmUser.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));

	}

	@PostMapping(value = "/api/login", produces = "application/json")
	public UserLogin loginUser(@RequestBody UserLogin userLogin) {
		logger.info(">>>>Logging in user: " + userLogin);
		String username = userService.verifyUser(userLogin);
		return new UserLogin(username);
	}
}
