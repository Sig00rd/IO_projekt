package com.example.demo.dao;

import com.example.demo.SpringBootRestRegistrationApplication;
import com.example.demo.entity.SportObject;
import com.example.demo.utils.SportObjectType;
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
public class SportObjectDaoTest
{
    @Autowired
    EntityManager entityManager;

    @Autowired
    private SportObjectDao sportObjectDao;

    private SportObject sampleValidSportObject() {
        SportObject mosirKrosno = new SportObject(
                "mosir",
                "bursaki 40",
                "Krosno",
                SportObjectType.ORLIK);
        return mosirKrosno;
    }

    @Transactional
    @Test
    public void whenFindByName_thenReturnSportObject() {
        // given
        SportObject mosir = sampleValidSportObject();
        entityManager.persist(mosir);
        entityManager.flush();

        // when
        SportObject found = sportObjectDao.findSportObjectByName("mosir");

        // then
        assertThat(found.getName())
                .isEqualTo(mosir.getName());
    }
}
