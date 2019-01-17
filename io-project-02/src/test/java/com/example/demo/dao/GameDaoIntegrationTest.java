package com.example.demo.dao;

import com.example.demo.entity.Discipline;
import com.example.demo.entity.Game;
import com.example.demo.entity.SportObject;
import com.example.demo.entity.User;
import com.example.demo.test_utils.TestUtils;
import com.example.demo.utils.LevelType;
import com.example.demo.utils.SportObjectType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class GameDaoIntegrationTest
{
    @Autowired
    EntityManager entityManager;

    @Autowired
    private GameDao gameDao;

    @Test
    public void whenGetFilteredGames_thenReturnCorrectGame() {
        // given
        Discipline kosz = new Discipline();
        kosz.setName("koszykówka");
        User seba = TestUtils.sampleValidUser();
        SportObject mosir = TestUtils.sampleValidSportObject();
        Date data = new Date();
        List<SportObject> accepted = Arrays.asList(mosir);

        Game game = new Game(
                new Float(0.0), 4, data,
                LevelType.ADVANCED, mosir, seba, kosz
        );

        entityManager.persist(game);
        entityManager.flush();

        // when
        List<Game> found = gameDao.getFilteredGames(
                accepted, "koszykówka",
                LevelType.GOOD, LevelType.ADVANCED,
                data, new Date()
        );

        assertThat(found.get(0).getUser().getUserName()).isEqualTo(seba.getUserName());
        
    }
}
