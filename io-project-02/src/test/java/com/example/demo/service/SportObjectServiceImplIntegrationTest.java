package com.example.demo.service;

import com.example.demo.dao.SportObjectDao;
import com.example.demo.entity.SportObject;
import com.example.demo.test_utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SportObjectServiceImplIntegrationTest
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

    @Before
    public void setUp() {
        SportObject mosir = TestUtils.sampleValidSportObject();

        List<SportObject> sportObjectList = new ArrayList<>();
        sportObjectList.add(mosir);

        Mockito.when(sportObjectDao.findAll())
                .thenReturn(sportObjectList);
    }

    @Test
    public void whenGetSportObjects_givenObjectPresent_thenReturnSportObjectsList() {
        // given
        SportObject mosir = TestUtils.sampleValidSportObject();

        // when
        List<SportObject> found = sportObjectService.getSportObjects();

        // then
        assertThat(found.get(0).toString()).isEqualTo(mosir.toString());
        assertThat(found.size()).isEqualTo(1);
    }


}
