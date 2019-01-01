package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.utils.LevelType;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "cost")
	private Float cost;

	@Column(name = "needed")
	private Integer needed;

	@Column(name = "priority_needed")
	private Integer priorityNeeded;

	@Column(name = "enrolled")
	private Integer enrolled = 0;

	@Column(name = "priority_enrolled")
	private Integer priorityEnrolled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	@Column(name = "level", columnDefinition = "smallint")
	@Enumerated
	private LevelType level;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "priority_date")
	private Date priorityDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "sport_object_id")
	private SportObject sportObject;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "discipline_id")
	private Discipline discipline;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "game")
	private List<UserGames> userGames = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private List<GamePriorities> gamePriorities = new ArrayList<>();

	public Game(Float cost, Integer needed, Date date, LevelType level,
			SportObject sportObject, User user, Discipline discipline) {
		this.cost = cost;
		this.needed = needed;
		this.date = date;
		this.level = level;
		this.sportObject = sportObject;
		this.user = user;
		this.discipline = discipline;
	}

	public List<UserGames> getUserGames() {
		return userGames;
	}

	public void setUserGames(List<UserGames> userGames) {
		this.userGames = userGames;
	}

	public Game() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getNeeded() {
		return needed;
	}

	public void setNeeded(Integer needed) {
		this.needed = needed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public void addPlayer(UserGames player) {
		this.userGames.add(player);
		if (player.getPitchRole() == null) {
			this.enrolled += 1;
		} else
			this.priorityEnrolled += 1;

	}

	public Date getPriorityDate() {
		return priorityDate;
	}

	public void setPriorityDate(Date priorityDate) {
		this.priorityDate = priorityDate;
	}

	public List<GamePriorities> getGamePriorities() {
		return gamePriorities;
	}

	public void setGamePriorities(List<GamePriorities> gamePriorities) {
		this.gamePriorities = gamePriorities;
	}

	public LevelType getLevel() {
		return level;
	}

	public void setLevel(LevelType level) {
		this.level = level;
	}
	public void addPriorityPitchRole(GamePriorities priorityRole) {
		this.priorityNeeded += priorityRole.getNeeded();
		this.gamePriorities.add(priorityRole);
	}

	public Integer getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}

	public Integer getPriorityNeeded() {
		return priorityNeeded;
	}

	public void setPriorityNeeded(Integer priorityNeeded) {
		this.priorityNeeded = priorityNeeded;
	}

	public Integer getPriorityEnrolled() {
		return priorityEnrolled;
	}

	public void setPriorityEnrolled(Integer priorityEnrolled) {
		this.priorityEnrolled = priorityEnrolled;
	}

}
