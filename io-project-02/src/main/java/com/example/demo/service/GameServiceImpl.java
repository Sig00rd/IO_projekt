package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DisciplineDao;
import com.example.demo.dao.GameDao;
import com.example.demo.dao.PitchRoleDao;
import com.example.demo.dao.SportObjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Discipline;
import com.example.demo.entity.Game;
import com.example.demo.entity.GamePriorities;
import com.example.demo.entity.SportObject;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGames;
import com.example.demo.form.GameForm;

@Service
public class GameServiceImpl implements GameService {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private GameDao gameDao;

	@Autowired
	private SportObjectDao sportObjectDao;

	@Autowired
	private DisciplineDao disciplineDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PitchRoleDao pitchRoleDao;

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
		Map<String, Integer> priorityRoles = gameForm.getPitchRoles();
		for (Map.Entry<String, Integer> entry : priorityRoles.entrySet()) {
			GamePriorities gamePriority = new GamePriorities(
					pitchRoleDao.findPitchRoleByName(entry.getKey()),
					entry.getValue());
			game.addPriorityPitchRole(gamePriority);
		}

		gameDao.save(game);

	}

	@Override
	@Transactional
	public void signUpPlayer(Long id, String role) {

		User player = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		Game game = gameDao.findById(id).orElse(null);

		List<GamePriorities> priorities = game.getGamePriorities();

		Boolean priorityFlag = assignPlayerPriorityFlag(role, game, priorities);

		UserGames userGames = new UserGames(player, game, priorityFlag);

		game.addPlayer(userGames);

		player.addGame(userGames);

	}

	private Boolean assignPlayerPriorityFlag(String role, Game game,
			List<GamePriorities> priorities) {
		Boolean priorityFlag = false;

		if (game.getNeeded() > game.getUserGames().size()) {
			priorityFlag = true;
		}
		logger.info(">>>>Input role: " + role);
		for (GamePriorities priority : priorities) {
			logger.info(
					">>>>Role from DB: " + priority.getPitchRole().getName());
			if (priority.getPitchRole().getName().equals(role)
					&& priority.getNeeded() > 0) {
				priority.setNeeded(priority.getNeeded() - 1);
				priorityFlag = true;
			}
		}
		return priorityFlag;
	}

}
