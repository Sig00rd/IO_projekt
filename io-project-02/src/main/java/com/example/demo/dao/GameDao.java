package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Game;
import com.example.demo.entity.SportObject;
import com.example.demo.utils.LevelType;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {

	@Query("select g from Game g where g.sportObject in ?1"
			+ " and (g.discipline.name = ?2 or ?2=null)  and (g.level between ?3 and ?4) "
			+ "and ((g.date>=?5 and null=?6) or (g.date between ?5 and ?6) or (null=?5 and null=?6))")
	List<Game> getFilteredGames(List<SportObject> acceptedObjects,
			String chosenSport, LevelType levelType, LevelType levelType2,
			Date date, Date date2);

}
