package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Game;
import com.example.demo.form.GameForm;
import com.example.demo.wrapper.GameWrapper;
import com.example.demo.wrapper.LobbyWrapper;

public interface GameService {

	Optional<Game> getGame(Long id);

	List<Game> getGames();

	void save(GameForm gameForm);

	void signUpPlayer(Long id, String role);

	LobbyWrapper getLobby(Long id);

	GameWrapper getGameWrapper(Long id);

}
