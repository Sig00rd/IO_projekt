package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Game;
import com.example.demo.form.GameForm;

public interface GameService {

	Optional<Game> getGame(Long id);

	List<Game> getGames();

	void save(GameForm gameForm);

	void signUpPlayer(Long id, String role);

}
