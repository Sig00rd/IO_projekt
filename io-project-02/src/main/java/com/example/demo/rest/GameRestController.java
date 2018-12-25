package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Game;
import com.example.demo.form.GameForm;
import com.example.demo.service.GamePrioritiesService;
import com.example.demo.service.GameService;
import com.example.demo.wrapper.GameWrapper;
import com.example.demo.wrapper.LobbyWrapper;

@RestController
@RequestMapping("/api")
public class GameRestController {

	@Autowired
	private GameService gameService;

	@Autowired
	private GamePrioritiesService gamePrioritiesService;

	@GetMapping("/games/{id}")
	public GameWrapper getGame(@PathVariable Long id) {

		Game game = gameService.getGame(id).orElse(null);

		GameWrapper gameWrapper = new GameWrapper(game.getId(),
				game.getDiscipline().getName(), game.getCost(),
				game.getNeeded(), game.getDate(), game.getLevel(),
				game.getSportObject(), game.getUser().getUserName());

		return gameWrapper;

	}
	@GetMapping("/games")
	public List<GameWrapper> getGames() {

		List<Game> games = gameService.getGames();
		List<GameWrapper> gameWrappers = new ArrayList<>();

		for (Game game : games) {
			gameWrappers.add(new GameWrapper(game.getId(),
					game.getDiscipline().getName(), game.getCost(),
					game.getNeeded(), game.getDate(), game.getLevel(),
					game.getSportObject(), game.getUser().getUserName()));
		}

		return gameWrappers;
	}

	@GetMapping("/lobby/{id}")
	public LobbyWrapper getLobby(@PathVariable Long id) {

		// List<String> players = gameService.getPlayers(id);

		return null;
	}

	@PostMapping("/games")
	public void createGame(@RequestBody GameForm gameForm) {

		gameService.save(gameForm);
		if (gameForm.getPriorityDate() != null) {
			gamePrioritiesService.save(gameForm.getPitchRoles());
		}

	}
}
