package com.example.demo.form;

public class NotificationForm {

	private Long receiverId;
	private Long gameId;
	private String body;
	private String type;
	private Boolean read;

	public NotificationForm(Long receiverId, Long gameId, String body,
			String type, Boolean read) {
		this.receiverId = receiverId;
		this.gameId = gameId;
		this.body = body;
		this.type = type;
		this.read = read;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long userId) {
		this.receiverId = userId;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
