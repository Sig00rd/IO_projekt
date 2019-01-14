package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.form.GameFilterForm;
import com.example.demo.form.GameForm;
import com.example.demo.response.ResponseMessage;
import com.example.demo.utils.EarthDist;
import com.example.demo.utils.LevelType;
import com.example.demo.wrapper.GameWrapper;
import com.example.demo.wrapper.LobbyWrapper;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
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
	public GameWrapper getGameWrapper(Long id) {

		Game game = this.getGame(id).orElse(null);

		Integer stillNeeded = getStillNeeded(game);

		GameWrapper gameWrapper = new GameWrapper(game.getId(),
				game.getDiscipline().getName(), game.getCost(), stillNeeded,
				game.getDate(), game.getPriorityDate(), game.getLevel(),
				game.getSportObject(), game.getUser().getUserName());

		return gameWrapper;
	}

	private Integer getStillNeeded(Game game) {
		Integer stillNeeded;
		if (game.getPriorityDate() == null) {
			stillNeeded = Math
					.max(game.getTotalNeeded() - game.getOrdinaryEnrolled(), 0);
		} else {
			stillNeeded = Math
					.max(game.getTotalNeeded() - game.getOrdinaryEnrolled()
							- game.getRelevantPriorityEnrolled(), 0);
			if (new Date().before(game.getPriorityDate())) {
				stillNeeded = Math.max(
						(game.getPriorityNeeded()
								- game.getRelevantPriorityEnrolled()),
						stillNeeded);

			}
		}
		return stillNeeded;
	}

	@Override
	@Transactional
	public Long save(GameForm gameForm) {

		User owner = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName()).orElse(null);
		Discipline discipline = disciplineDao
				.findDisciplineByName(gameForm.getDisciplineName());

		SportObject sportObject = extractSportObject(gameForm);

		Game game = new Game(gameForm.getCost(), gameForm.getNeeded(),
				gameForm.getDate(), gameForm.getLevel(), sportObject, owner,
				discipline);

		if (gameForm.getPriorityDate() != null) {
			game.setPriorityDate(gameForm.getPriorityDate());
			game.setPriorityNeeded(0);
			game.setRelevantPriorityEnrolled(0);
			Map<String, Integer> priorityRoles = gameForm.getPitchRoles();
			for (Map.Entry<String, Integer> entry : priorityRoles.entrySet()) {
				GamePriorities gamePriority = new GamePriorities(
						pitchRoleDao.findPitchRoleByName(entry.getKey()),
						entry.getValue());
				game.addPriorityPitchRole(gamePriority);
			}
		}
		gameDao.save(game);
		return game.getId();

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
				.getAuthentication().getName()).orElse(null);

		Game game = gameDao.findById(id).orElse(null);

		PitchRole pitchRole = pitchRoleDao.findPitchRoleByName(role);

		UserGames userGames = new UserGames(player, game, pitchRole);

		game.addPlayer(userGames);

		player.addGame(userGames);

	}

	@Override
	@Transactional
	public LobbyWrapper getLobby(Long id) {

		Game game = gameDao.findById(id).orElse(null);
		LobbyWrapper lobbyWrapper = getLobbyWrapperFromGame(game);
		List<GamePriorities> priorities = game.getGamePriorities();
		lobbyWrapper.addPrioritiesNeeded(priorities);
		List<UserGames> players = game.getUserGames();
		players.sort(Comparator.comparing(UserGames::getCreated));
		lobbyWrapper.addPlayerRolesAndSquadMembership(game, players);

		return lobbyWrapper;
	}

	private LobbyWrapper getLobbyWrapperFromGame(Game game) {
		LobbyWrapper lobbyWrapper = new LobbyWrapper(game.getId(),
				game.getDiscipline().getName(), game.getCost(),
				getStillNeeded(game), game.getDate(), game.getPriorityDate(),
				game.getLevel(), game.getSportObject(),
				game.getUser().getUserName());
		return lobbyWrapper;
	}

	@Override
	@Transactional
	public List<GameWrapper> getFilteredGames(GameFilterForm gameFilterForm)
			throws ApiException, InterruptedException, IOException {
		List<GameWrapper> gameWrappers = new ArrayList<>();

		String minLevel = handleMinLevelNull(gameFilterForm);
		String maxLevel = handleMaxLevelNull(gameFilterForm);
		String pitchRole = gameFilterForm.getPitchRole();

		List<SportObject> acceptedObjects = getAcceptedObjects(gameFilterForm);

		if (acceptedObjects.isEmpty()) {
			return gameWrappers;
		}

		List<Game> games = getFilteredGames(gameFilterForm, minLevel, maxLevel,
				acceptedObjects, pitchRole);

		return getFilteredGameWrappers(gameFilterForm, gameWrappers, games);
	}

	private List<GameWrapper> getFilteredGameWrappers(
			GameFilterForm gameFilterForm, List<GameWrapper> gameWrappers,
			List<Game> games) {
		for (Game game : games) {
			GameWrapper gameWrapper = this.getGameWrapper(game.getId());
			if (gameFilterForm.getMinStillNeeded() != null && gameWrapper
					.getStillNeeded() < gameFilterForm.getMinStillNeeded()) {
				continue;
			}
			gameWrappers.add(gameWrapper);

		}
		return gameWrappers;
	}

	private List<Game> getFilteredGames(GameFilterForm gameFilterForm,
			String minLevel, String maxLevel, List<SportObject> acceptedObjects,
			String pitchRole) {
		List<Game> games = gameDao.getFilteredGames(acceptedObjects,
				gameFilterForm.getChosenSport(), LevelType.valueOf(minLevel),
				LevelType.valueOf(maxLevel), gameFilterForm.getFromDate(),
				gameFilterForm.getToDate());

		if (pitchRole != null) {
			return handleSpecifiedPitchRole(pitchRole, games);
		}
		return games;
	}

	private List<Game> handleSpecifiedPitchRole(String pitchRole,
			List<Game> games) {
		List<Game> gamesAfterRoleFiltering = new ArrayList<>();
		for (Game game : games) {
			List<GamePriorities> gamePriorities = game.getGamePriorities();
			for (GamePriorities gamePriority : gamePriorities) {
				if (gamePriority.getPitchRole().getName().equals(pitchRole)) {
					gamesAfterRoleFiltering.add(game);
				}
			}
		}
		return gamesAfterRoleFiltering;
	}

	private String handleMaxLevelNull(GameFilterForm gameFilterForm) {

		String maxLevel = gameFilterForm.getMaxLevel();
		if (maxLevel == null) {
			maxLevel = "ADVANCED";
		}
		return maxLevel;
	}

	private String handleMinLevelNull(GameFilterForm gameFilterForm) {

		String minLevel = gameFilterForm.getMinLevel();
		if (minLevel == null) {
			minLevel = "RECREATION";
		}
		return minLevel;
	}

	private List<SportObject> getAcceptedObjects(GameFilterForm gameFilterForm)
			throws ApiException, InterruptedException, IOException {
		List<SportObject> sportObjects = sportObjectDao.findAll();
		List<SportObject> acceptedObjects = new ArrayList<>();

		if (gameFilterForm.getAddress() == null
				|| gameFilterForm.getRadius() == null) {
			return sportObjects;
		}

		Double[] coords = EarthDist.lookupCoord(gameFilterForm.getAddress());
		for (SportObject sportObject : sportObjects) {
			if (sportObject.distanceBetween(coords) <= gameFilterForm
					.getRadius()) {
				if (gameFilterForm.getPitchType() != null
						&& !sportObject.getType().name()
						.equals(gameFilterForm.getPitchType())) {
					continue;
				}
				acceptedObjects.add(sportObject);
			}
		}
		return acceptedObjects;
	}


	@Override
	@Transactional
	public ResponseEntity<?> sendMessageToLobby(Long id, String message) {
		Game game = gameDao.findById(id).orElse(null);
		if (game == null) {
			return new ResponseEntity<>(
					new ResponseMessage(
							"Failed! - Game of such id does not exists."),
					HttpStatus.BAD_REQUEST);
		}

		if (!SecurityContextHolder.getContext().getAuthentication().getName()
				.equals(game.getUser().getUserName())) {
			return new ResponseEntity<>(
					new ResponseMessage(
							"Failed! - You are not owner of this game."),
					HttpStatus.UNAUTHORIZED);
		}
		game.addMessage(new GameMessage(message));
		return new ResponseEntity<>(
				new ResponseMessage(
						"Successfully added message to game lobby."),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public List<String> showLobbyMessages(Long id) {
		Game game = gameDao.findById(id).orElse(null);
		List<String> messages = new ArrayList<>();
		for (GameMessage message : game.getGameMessages()) {
			messages.add(message.getMessage());
		}
		return messages;
	}

}
