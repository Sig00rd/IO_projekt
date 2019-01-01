package com.example.demo.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.GamePriorities;
import com.example.demo.entity.SportObject;
import com.example.demo.entity.UserGames;
import com.example.demo.utils.LevelType;

public class LobbyWrapper extends GameWrapper {

	private List<String> firstSquad = new ArrayList<>();
	private List<String> reserve = new ArrayList<>();
	private Map<String, Integer> prioritiesNeeded;
	private Map<String, Integer> prioritiesEnrolled;
	private Map<String, List<String>> playerRoles;

	public LobbyWrapper(Long id, String discipline, Float cost, Integer needed,
			Integer priorityNeeded, Integer enrolled, Integer priorityEnrolled,
			Date date, Date priorityDate, LevelType level,
			SportObject sportObject, String owner) {
		super(id, discipline, cost, needed, priorityNeeded, enrolled,
				priorityEnrolled, date, priorityDate, level, sportObject,
				owner);
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

	public Map<String, Integer> getPrioritiesEnrolled() {
		return prioritiesEnrolled;
	}

	public void setPrioritiesEnrolled(Map<String, Integer> prioritiesEnrolled) {
		this.prioritiesEnrolled = prioritiesEnrolled;
	}

	public void addPrioritiesNeeded(List<GamePriorities> priorities) {
		if (prioritiesNeeded == null) {
			prioritiesNeeded = new HashMap<>();
			prioritiesEnrolled = new HashMap<>();
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

	public void addPlayerRoles(List<UserGames> players) {
		this.playerRoles = new HashMap<>();
		List<String> playerNames = new ArrayList<>();
		String defaultRoleName = "DEFAULT";
		this.playerRoles.put(defaultRoleName, playerNames);

		for (UserGames player : players) {
			if (player.getPitchRole() == null) {
				playerNames = this.playerRoles.get(defaultRoleName);
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
			}

		}

	}

}
