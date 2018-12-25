package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_priorities")
public class GamePriorities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pitch_role_id")
	private PitchRole pitchRole;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "game_id")
	private Game game;

	@Column(name = "needed")
	private int needed;

	public GamePriorities() {
	}

	public GamePriorities(Game game, PitchRole pitchRoleId, int needed) {
		this.game = game;
		this.pitchRole = pitchRoleId;
		this.needed = needed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PitchRole getPitchRole() {
		return pitchRole;
	}

	public void setPitchRole(PitchRole pitchRole) {
		this.pitchRole = pitchRole;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getNeeded() {
		return needed;
	}

	public void setNeeded(int needed) {
		this.needed = needed;
	}

	@Override
	public String toString() {
		return "GamePriorities [id=" + id + ", pitchRole=" + pitchRole + ", game=" + game + ", needed=" + needed + "]";
	}

}
