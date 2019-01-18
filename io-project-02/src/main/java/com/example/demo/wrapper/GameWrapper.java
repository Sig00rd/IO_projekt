package com.example.demo.wrapper;

import java.util.Date;

import com.example.demo.entity.SportObject;
import com.example.demo.utils.LevelType;

public class GameWrapper {

	private Long id;
	private String discipline;
	private Float cost;
	private Integer stillNeeded;
	private Date date;
	private Date priorityDate;
	private LevelType level;
	private SportObject sportObject;
	private String owner;

	public GameWrapper(Long id, String discipline, Float cost,
			Integer stillNeeded, Date date, Date priorityDate, LevelType level,
			SportObject sportObject, String owner) {
		this.id = id;
		this.discipline = discipline;
		this.cost = cost;
		this.stillNeeded = stillNeeded;
		this.date = (Date) date.clone();
		if (priorityDate != null) {
			this.priorityDate = (Date) priorityDate.clone();
		}
		this.level = level;
		this.sportObject = sportObject;
		this.owner = owner;
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

	public Date getDate() {
		return (Date) date.clone();
	}
	public void setDate(Date date) {
		this.date = (Date) date.clone();
	}
	public LevelType getLevel() {
		return level;
	}
	public void setLevel(LevelType level) {
		this.level = level;
	}
	public SportObject getSportObject() {
		return sportObject;
	}
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public Date getPriorityDate() {
		if (priorityDate != null) {
			return (Date) priorityDate.clone();
		}
		return null;
	}

	public void setPriorityDate(Date priorityDate) {
		if (priorityDate != null) {
			this.priorityDate = (Date) priorityDate.clone();
		}
	}
	public Integer getStillNeeded() {
		return stillNeeded;
	}
	public void setStillNeeded(Integer stillNeeded) {
		this.stillNeeded = stillNeeded;
	}
	@Override
	public String toString() {
		return "GameWrapper [id=" + id + ", discipline=" + discipline
				+ ", cost=" + cost + ", stillNeeded=" + stillNeeded + ", date="
				+ date + ", priorityDate=" + priorityDate + ", level=" + level
				+ ", sportObject=" + sportObject + ", owner=" + owner + "]";
	}

}
