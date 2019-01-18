package com.example.demo.wrapper;

public class NotificationWrapper {

	private Long id;
	private Long game;
	private String sender;
	private String type;
	private String body;
	private Boolean read;

	public NotificationWrapper(Long id, Long game, String sender, String type,
			String body, Boolean read) {
		this.id = id;
		this.game = game;
		this.sender = sender;
		this.type = type;
		this.body = body;
		this.read = read;
	}
	public Long getGame() {
		return game;
	}
	public void setGame(Long game) {
		this.game = game;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
