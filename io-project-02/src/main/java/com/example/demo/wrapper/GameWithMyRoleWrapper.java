package com.example.demo.wrapper;

import com.example.demo.entity.PitchRole;

public class GameWithMyRoleWrapper {

	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public String getMyPitchRole() {
		return myPitchRole;
	}
	public void setMyPitchRole(String myPitchRole) {
		this.myPitchRole = myPitchRole;
	}
	private Long gameId;
	private String myPitchRole;
	public GameWithMyRoleWrapper(Long gameId, PitchRole myPitchRole) {
		this.gameId = gameId;
		if (myPitchRole != null)
			this.myPitchRole = myPitchRole.getName();
	}

}
