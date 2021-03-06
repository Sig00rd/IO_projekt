package com.example.demo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Game;
import com.example.demo.form.GameFilterForm;
import com.example.demo.form.GameForm;
import com.example.demo.service.GameService;
import com.example.demo.wrapper.GameWithMyRoleWrapper;
import com.example.demo.wrapper.GameWrapper;
import com.example.demo.wrapper.LobbyWrapper;
import com.google.maps.errors.ApiException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameRestController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private GameService gameService;

	@GetMapping("/games/{id}")
	public GameWrapper getGame(@PathVariable Long id) {

		return gameService.getGameWrapper(id);

	}

	@GetMapping("/games")
	public List<GameWrapper> getGames() {

		List<Game> games = gameService.getGames();
		List<GameWrapper> gameWrappers = new ArrayList<>();

		for (Game game : games) {
			gameWrappers.add(this.getGame(game.getId()));
		}

		return gameWrappers;
	}

	@GetMapping("/lobby/{id}")
	public LobbyWrapper getLobby(@PathVariable Long id) {

		return gameService.getLobby(id);
	}

	@PostMapping("/games")
	public LobbyWrapper createGame(@RequestBody GameForm gameForm) {

		Long gameId = gameService.save(gameForm);
		return gameService.getLobby(gameId);

	}

	@PostMapping("/games/{id}")
	public LobbyWrapper signUpForGame(@PathVariable Long id,
			@RequestBody(required = false) String role) {

		if (role == null) {
			role = "";
		}
		gameService.signUpPlayer(id, role);
		return this.getLobby(id);
	}

	@PostMapping("/games/filter")
	public List<GameWrapper> getFilteredGames(
			@RequestBody GameFilterForm gameFilterForm)
			throws ApiException, InterruptedException, IOException {

		logger.warning(gameFilterForm.toString());
		List<GameWrapper> filteredGames = gameService
				.getFilteredGames(gameFilterForm);

		return filteredGames;
	}

	@PostMapping("/messages/lobby/{id}")
	public ResponseEntity<?> sendMessageToLobby(@PathVariable Long id,
			@RequestBody String message) {

		return gameService.sendMessageToLobby(id, message);
	}

	@GetMapping("/messages/lobby/{id}")
	public List<String> showLobbyMessages(@PathVariable Long id) {

		return gameService.showLobbyMessages(id);
	}
	@DeleteMapping({"/games/{gameId}/{role}", "/games/{gameId}"})
	public ResponseEntity<?> signOffFromTheGame(
			@PathVariable("gameId") Long gameId,
			@PathVariable(name = "role", required = false) String role) {

		return gameService.signOffFromTheGame(gameId, role);
	}
	@DeleteMapping("/mygames/{gameId}")
	public ResponseEntity<?> removeGame(@PathVariable("gameId") Long gameId) {

		return gameService.removeGame(gameId);
	}
	@GetMapping("/mygames")
	public List<GameWrapper> getMyGames() {

		return gameService.getMyGames();
	}
	@GetMapping("/games/signedUp")
	public List<GameWithMyRoleWrapper> getGamesISignedUp() {

		return gameService.getGamesISignedUp();
	}
}