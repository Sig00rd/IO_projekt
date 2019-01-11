package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Game;
import com.example.demo.form.GameFilterForm;
import com.example.demo.form.GameForm;
import com.example.demo.wrapper.GameWrapper;
import com.example.demo.wrapper.LobbyWrapper;
import com.google.maps.errors.ApiException;

public interface GameService {

	Optional<Game> getGame(Long id);

	List<Game> getGames();

	Long save(GameForm gameForm);

	void signUpPlayer(Long id, String role);

	LobbyWrapper getLobby(Long id);

	GameWrapper getGameWrapper(Long id);

<<<<<<< HEAD
	List<GameWrapper> getFilteredGames(GameFilterForm gameFilterForm)
			throws ApiException, InterruptedException, IOException;
=======
	ResponseEntity<?> sendMessageToLobby(Long id, String message);

	List<String> showLobbyMessages(Long id);
>>>>>>> owner of game can now send message to lobby, users can view messages

}
