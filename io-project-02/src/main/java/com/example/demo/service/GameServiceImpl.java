package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DisciplineDao;
import com.example.demo.dao.GameDao;
import com.example.demo.dao.SportObjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Discipline;
import com.example.demo.entity.Game;
import com.example.demo.entity.SportObject;
import com.example.demo.entity.User;
import com.example.demo.form.GameForm;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;

	@Autowired
	private SportObjectDao sportObjectDao;

	@Autowired
	private DisciplineDao disciplineDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Optional<Game> getGame(Long id) {
		return gameDao.findById(id);
	}

	@Override
	public List<Game> getGames() {
		return gameDao.findAll();
	}

	@Override
	@Transactional
	public void save(GameForm gameForm) {

		User owner = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		Discipline discipline = disciplineDao
				.findDisciplineByName(gameForm.getDisciplineName());
		SportObject sportObject = sportObjectDao
				.findSportObjectByName(gameForm.getSportObjectName());
		Game game = new Game(gameForm.getCost(), gameForm.getNeeded(),
				gameForm.getDate(), gameForm.getLevel(), sportObject, owner,
				discipline);
		game.setPriorityDate(gameForm.getPriorityDate());

		gameDao.save(game);

	}

}
