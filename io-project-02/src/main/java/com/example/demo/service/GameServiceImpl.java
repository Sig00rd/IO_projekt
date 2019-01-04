package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.form.GameForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

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
		logger.info(gameForm.toString());

		User owner = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		Discipline discipline = disciplineDao
				.findDisciplineByName(gameForm.getDisciplineName());

		SportObject sportObject = extractSportObject(gameForm);

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

	private SportObject extractSportObject(GameForm gameForm) {
		SportObject sportObject;
		String sportObjectNameOrAddress = gameForm.getSportObjectName();

		if (sportObjectNameOrAddress.contains(",")) {
			String[] addressAndCity = sportObjectNameOrAddress.trim()
					.split("\\s*,\\s*");
			String address = addressAndCity[0];
			String city = addressAndCity[1];
			sportObject = sportObjectDao
					.findSportObjectByAddressAndCity(address, city);
		} else {
			sportObject = sportObjectDao
					.findSportObjectByName(gameForm.getSportObjectName());
		}
		return sportObject;
	}

	@Override
	@Transactional
	public void signUpPlayer(Long id, String role) {

		User player = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		Game game = gameDao.findById(id).orElse(null);

		PitchRole pitchRole = pitchRoleDao.findPitchRoleByName(role);

		UserGames userGames = new UserGames(player, game, pitchRole);

		game.addPlayer(userGames);

		player.addGame(userGames);

	}

}
