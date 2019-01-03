package com.example.demo.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.Game;
import com.example.demo.entity.GamePriorities;
import com.example.demo.entity.SportObject;
import com.example.demo.entity.UserGames;
import com.example.demo.utils.LevelType;

public class LobbyWrapper extends GameWrapper {

	private List<String> firstSquad = new ArrayList<>();
	private List<String> reserve = new ArrayList<>();
	private Map<String, Integer> prioritiesNeeded;
	private Map<String, List<String>> playerRoles;

	public LobbyWrapper(Long id, String discipline, Float cost,
			Integer stillNeeded, Date date, Date priorityDate, LevelType level,
			SportObject sportObject, String owner) {
		super(id, discipline, cost, stillNeeded, date, priorityDate, level,
				sportObject, owner);
	}

	public List<String> getFirstSquad() {
		return firstSquad;
	}

	public void setFirstSquad(List<String> firstSquad) {
		this.firstSquad = firstSquad;
	}

	public List<String> getReserve() {
		return reserve;
	}

	public void setReserve(List<String> reserve) {
		this.reserve = reserve;
	}

	public Map<String, Integer> getPrioritiesNeeded() {
		return prioritiesNeeded;
	}

	public void setPrioritiesNeeded(Map<String, Integer> prioritiesNeeded) {
		this.prioritiesNeeded = prioritiesNeeded;
	}

	public void addPrioritiesNeeded(List<GamePriorities> priorities) {
		if (prioritiesNeeded == null) {
			prioritiesNeeded = new HashMap<>();
		}
		for (GamePriorities priority : priorities) {
			prioritiesNeeded.put(priority.getPitchRole().getName(),
					priority.getNeeded());
		}

	}

	public Map<String, List<String>> getPlayerRoles() {
		return playerRoles;
	}

	public void setPlayerRoles(Map<String, List<String>> playerRoles) {
		this.playerRoles = playerRoles;
	}

	public void addPlayerRolesAndSquadMembership(Game game,
			List<UserGames> players) {
		String ordinaryPlayerRole = "DEFAULT";

		setupOrdinaryPlayersList(ordinaryPlayerRole);

		assignPlayersToRolesAndPriorityPlayersToSquads(players,
				ordinaryPlayerRole);
		assignOrdinaryPlayersToSquads(ordinaryPlayerRole,
				getOrdinaryPlayersNeeded(game));

	}

	private void setupOrdinaryPlayersList(String ordinaryPlayerRole) {
		this.playerRoles = new HashMap<>();
		List<String> playerNames = new ArrayList<>();
		this.playerRoles.put(ordinaryPlayerRole, playerNames);
	}

	private void assignPlayersToRolesAndPriorityPlayersToSquads(
			List<UserGames> players, String ordinaryPlayerRole) {
		List<String> playerNames;
		for (UserGames player : players) {
			if (player.getPitchRole() == null) {
				playerNames = this.playerRoles.get(ordinaryPlayerRole);
				playerNames.add(player.getUser().getUserName());
			} else {
				playerNames = this.playerRoles
						.get(player.getPitchRole().getName());
				if (playerNames == null) {
					playerNames = new ArrayList<>();
					this.playerRoles.put(player.getPitchRole().getName(),
							playerNames);
				}
				playerNames.add(player.getUser().getUserName());
				assignPriorityPlayersToSquads(playerNames, player);
			}
		}
	}

	private void assignPriorityPlayersToSquads(List<String> playerNames,
			UserGames player) {
		if (playerNames.size() <= this.prioritiesNeeded
				.get(player.getPitchRole().getName())) {
			this.firstSquad.add(player.getUser().getUserName());
		} else {
			this.reserve.add(player.getUser().getUserName());
		}
	}

	private void assignOrdinaryPlayersToSquads(String ordinaryPlayerRole,
			int ordinaryNeeded) {
		List<String> playerNames;
		playerNames = this.playerRoles.get(ordinaryPlayerRole);
		for (int i = 0; i < playerNames.size(); i++) {
			if (i < ordinaryNeeded) {
				this.firstSquad.add(playerNames.get(i));
			} else {
				this.reserve.add(playerNames.get(i));
			}
		}
	}

	private int getOrdinaryPlayersNeeded(Game game) {
		int ordinaryNeeded = game.getTotalNeeded();
		if (game.getPriorityDate() != null) {
			if (new Date().before(game.getPriorityDate())) {
				ordinaryNeeded -= game.getPriorityNeeded();
			} else {
				ordinaryNeeded -= game.getRelevantPriorityEnrolled();
			}
		}
		return ordinaryNeeded;
	}

}
