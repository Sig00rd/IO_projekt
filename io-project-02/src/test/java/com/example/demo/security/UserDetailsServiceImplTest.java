package com.example.demo.security;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.security.service.UserDetailsServiceImpl;
import com.example.demo.test_utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsServiceImplTest
{
    @TestConfiguration
    static class UserDetailsServiceImplTestContextConfiguration {
        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImpl();
        }
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserDao userDao;

    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadUserByUsername_givenDaoDidNotFindHim_thenThrowNotFoundException() {
        // given
        when(userDao.findByUserName("seba")).thenReturn(Optional.empty());

        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername("seba");
    }


}
