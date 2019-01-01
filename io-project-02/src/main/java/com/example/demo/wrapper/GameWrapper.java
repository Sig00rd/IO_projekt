package com.example.demo.wrapper;

import java.util.Date;

import com.example.demo.entity.SportObject;
import com.example.demo.utils.LevelType;

public class GameWrapper {

	private Long id;
	private String discipline;
	private Float cost;
	private Integer needed;
	private Integer priorityNeeded;
	private Integer enrolled;
	private Integer priorityEnrolled;
	private Date date;
	private Date priorityDate;
	private LevelType level;
	private SportObject sportObject;
	private String owner;

	public GameWrapper(Long id, String discipline, Float cost, Integer needed,
			Integer priorityNeeded, Integer enrolled, Integer priorityEnrolled,
			Date date, Date priorityDate, LevelType level,
			SportObject sportObject, String owner) {
		this.id = id;
		this.discipline = discipline;
		this.cost = cost;
		this.needed = needed;
		this.priorityNeeded = priorityNeeded;
		this.enrolled = enrolled;
		this.priorityEnrolled = priorityEnrolled;
		this.date = date;
		this.priorityDate = priorityDate;
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
	public Date getPriorityDate() {
		return priorityDate;
	}
	public void setPriorityDate(Date priorityDate) {
		this.priorityDate = priorityDate;
	}

}
