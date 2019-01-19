package com.example.demo.service;

import com.example.demo.dao.GameDao;
import com.example.demo.dao.NotificationDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.test_utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest
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

    @MockBean
    private GameDao gameDao;

    @MockBean
    private NotificationDao notificationDao;

    @Before
    public void setUp() {
        User seba = TestUtils.sampleValidUser();

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

    @Test
    public void whenInvalidUserName_thenReturnNull() {
        String userName = "matiRU";
        User found = userService.findUserByUserName(userName);

        assertThat(found)
                .isEqualTo(null);
    }
}
