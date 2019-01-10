package com.example.demo.dao;

import com.example.demo.SpringBootRestRegistrationApplicationTest;
import com.example.demo.entity.SportObject;
import com.example.demo.test_utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class SportObjectDaoIntegrationTest extends SpringBootRestRegistrationApplicationTest
{
    @Autowired
    EntityManager entityManager;

    @Autowired
    private SportObjectDao sportObjectDao;

    private SportObject sampleValidSportObject;

    @Before
    public void setUp() {
        this.sampleValidSportObject = TestUtils.sampleValidSportObject();
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
