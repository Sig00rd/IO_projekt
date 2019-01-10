package com.example.demo.service;

import com.example.demo.SpringBootRestRegistrationApplicationTest;
import com.example.demo.dao.SportObjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

public class SportObjectServiceImplIntegrationTest extends SpringBootRestRegistrationApplicationTest
{
    @TestConfiguration
    static class SportObjectServiceImplTestContextConfiguration {
        @Bean
        public SportObjectService sportObjectService() {
            return new SportObjectServiceImpl();
        }
    }

    @Autowired
    private SportObjectService sportObjectService;

    @MockBean
    private SportObjectDao sportObjectDao;


}
