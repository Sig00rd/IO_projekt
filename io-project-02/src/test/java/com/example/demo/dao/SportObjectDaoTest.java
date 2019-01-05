package com.example.demo.dao;

import com.example.demo.SpringBootRestRegistrationApplication;
import com.example.demo.SpringBootRestRegistrationApplicationTests;
import com.example.demo.entity.SportObject;
import com.example.demo.utils.SportObjectType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestRegistrationApplication.class)
public class SportObjectDaoTest extends SpringBootRestRegistrationApplicationTests
{
    @Autowired
    EntityManager entityManager;

    @Autowired
    private SportObjectDao sportObjectDao;

    private SportObject sampleValidSportObject;

    @Before
    public void setUp() {
        this.sampleValidSportObject = sampleValidSportObject();
    }

    private SportObject sampleValidSportObject() {
        return new SportObject(
                "Mosir",
                "Bursaki 40",
                "Krosno",
                SportObjectType.ORLIK);
    }

    @Transactional
    @Test
    public void whenFindByName_thenReturnSportObject() {
        // given;
        entityManager.persist(this.sampleValidSportObject);
        entityManager.flush();

        // when
        SportObject found = sportObjectDao.findSportObjectByName("Mosir");

        // then
        assertThat(found.getName())
                .isEqualTo(this.sampleValidSportObject.getName());
    }

    @Transactional
    @Test
    public void whenFindByAddressAndCity_thenReturnSportObject() {
        // given
        entityManager.persist(this.sampleValidSportObject);
        entityManager.flush();

        // when
        SportObject found = sportObjectDao.findSportObjectByAddressAndCity("Bursaki 40", "Krosno");

        // then
        assertThat(found.getName())
                .isEqualTo(this.sampleValidSportObject.getName());
    }
}
