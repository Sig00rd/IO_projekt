package com.example.demo.wrapper;

public class InvitationWrapper {

	private Long game;
	private Long sender;
	private Boolean read;

	public InvitationWrapper(Long game, Long sender, Boolean read) {
		this.game = game;
		this.sender = sender;
		this.read = read;
	}
	public Long getGame() {
		return game;
	}
	public void setGame(Long game) {
		this.game = game;
	}
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}

}
