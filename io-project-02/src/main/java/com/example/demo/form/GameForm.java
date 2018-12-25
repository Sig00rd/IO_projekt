package com.example.demo.form;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.utils.LevelType;

public class GameForm {

	@NotNull(message = "is required")
	@Min(value = 0, message = "Minimum 0!")
	private Float cost;

	@NotNull(message = "is required")
	@Min(value = 0, message = "Minimum 0!")
	@Max(value = 100, message = "Too much!")
	private Integer needed;

	@NotNull(message = "is required")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date date;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date priorityDate;

	@NotNull(message = "is required")
	@Size(min = 3, message = "minimum 3 char")
	private String sportObjectName;

	@NotNull(message = "is required")
	@Size(min = 3, message = "minimum 3 char")
	private String disciplineName;

	private Map<String, Integer> pitchRoles = new HashMap<String, Integer>();

	@NotNull(message = "is required")
	private LevelType level;

	public GameForm() {

	}

	public String getDisciplineName() {
		return disciplineName;
	}

	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}

	public String getSportObjectName() {
		return sportObjectName;
	}

	public void setSportObjectName(String sportObjectName) {
		this.sportObjectName = sportObjectName;
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

	public Map<String, Integer> getPitchRoles() {
		return pitchRoles;
	}

	public void setPitchRoles(Map<String, Integer> pitchRoles) {
		this.pitchRoles = pitchRoles;
	}

	public Date getPriorityDate() {
		return priorityDate;
	}

	public void setPriorityDate(Date priorityDate) {
		this.priorityDate = priorityDate;
	}

	public LevelType getLevel() {
		return level;
	}

	public void setLevel(LevelType level) {
		this.level = level;
	}

}
