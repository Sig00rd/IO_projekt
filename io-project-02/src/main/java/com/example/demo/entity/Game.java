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

	@Column(name = "total_needed")
	private Integer totalNeeded;

	@Column(name = "priority_needed")
	private Integer priorityNeeded;

	@Column(name = "ordinary_enrolled")
	private Integer ordinaryEnrolled = 0;

	@Column(name = "priority_enrolled")
	private Integer relevantPriorityEnrolled;

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

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private List<GameMessage> gameMessages;

	public Game(Float cost, Integer needed, Date date, LevelType level,
			SportObject sportObject, User user, Discipline discipline) {
		this.cost = cost;
		this.totalNeeded = needed;
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

	public Integer getTotalNeeded() {
		return totalNeeded;
	}

	public void setTotalNeeded(Integer needed) {
		this.totalNeeded = needed;
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
			this.ordinaryEnrolled += 1;
		} else
			for (GamePriorities gamePriority : this.getGamePriorities()) {
				if (gamePriority.getPitchRole() == player.getPitchRole()) {
					gamePriority.setEnrolled(gamePriority.getEnrolled() + 1);
					if (gamePriority.getEnrolled() <= gamePriority
							.getNeeded()) {
						this.setRelevantPriorityEnrolled(
								this.getRelevantPriorityEnrolled() + 1);
					}
					break;
				}

			}

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

	public Integer getOrdinaryEnrolled() {
		return ordinaryEnrolled;
	}

	public void setOrdinaryEnrolled(Integer ordinaryEnrolled) {
		this.ordinaryEnrolled = ordinaryEnrolled;
	}

	public Integer getPriorityNeeded() {
		return priorityNeeded;
	}

	public void setPriorityNeeded(Integer priorityNeeded) {
		this.priorityNeeded = priorityNeeded;
	}

	public Integer getRelevantPriorityEnrolled() {
		return relevantPriorityEnrolled;
	}

	public void setRelevantPriorityEnrolled(Integer priorityEnrolled) {
		this.relevantPriorityEnrolled = priorityEnrolled;
	}

	public void addMessage(GameMessage gameMessage) {
		if (gameMessages == null) {
			gameMessages = new ArrayList<>();
		}
		gameMessages.add(gameMessage);

	}

	public List<GameMessage> getGameMessages() {
		return gameMessages;
	}

	public void setGameMessages(List<GameMessage> gameMessages) {
		this.gameMessages = gameMessages;
	}

}
