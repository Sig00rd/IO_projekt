package com.example.demo.service;

import com.example.demo.SpringBootRestRegistrationApplicationTest;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceImplIntegrationTest extends SpringBootRestRegistrationApplicationTest
{
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @Before
    public void setUp() {
        User seba = new User(
                "sebaPL", "123456", "seba.galecki@gmail.com");

        Mockito.when(userDao.findByUserName(seba.getUserName()))
                .thenReturn(Optional.of(seba));
    }

    @Test
    public void whenValidUserName_thenUserShouldBeFound() {
        String userName = "sebaPL";
        User found = userService.findUserByUserName(userName);

        assertThat(found.getUserName())
                .isEqualTo(userName);
    }
}
