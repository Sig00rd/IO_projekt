package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.test_utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UserDaoIntegrationTest
{
    @Autowired
    EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    private User persistValidUser() {
        User seba = TestUtils.sampleValidUser();
        entityManager.persist(seba);
        entityManager.flush();
        return seba;
    }

    @Transactional
    @Test
    public void whenFindByUserName_thenReturnUser() {
        // given
        User seba = persistValidUser();

        // when
        Optional<User> found = userDao.findByUserName(seba.getUserName());

        // then
        assertThat(found.get().getUserName())
                .isEqualTo(seba.getUserName());
    }

    @Test
    public void whenExistsByUserNameAndUserPresent_thenReturnTrue() {
        // given
        User seba = persistValidUser();

        // when
        Boolean exists = userDao.existsByUserName(seba.getUserName());

        // then
        assertThat(exists)
                .isEqualTo(true);
    }

    @Test
    public void whenExistsByUserNameAndUserAbsent_thenReturnFalse() {
        // given
        User seba = TestUtils.sampleValidUser();

        // when
        Boolean exists = userDao.existsByUserName(seba.getUserName());

        // then
        assertThat(exists)
                .isEqualTo(false);
    }

    @Test
    public void whenExistsByEmailAndUserPresent_thenReturnTrue() {
        // given
        User seba = persistValidUser();

        // when
        Boolean exists = userDao.existsByEmail(seba.getEmail());

        // then
        assertThat(exists)
                .isEqualTo(true);
    }

    @Test
    public void whenExistsByEmailAndUserPresent_thenReturnFalse() {
        // given
        User seba = TestUtils.sampleValidUser();

        // when
        Boolean exists = userDao.existsByEmail(seba.getEmail());

        // then
        assertThat(exists)
                .isEqualTo(false);
    }
}
