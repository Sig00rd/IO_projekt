package com.example.demo.form;

import java.util.Date;

public class GameFilterForm {

	private String chosenSport;
	private String pitchType;
	private String minLevel;
	private String maxLevel;
	private Integer minStillNeeded;
	private String address;
	private Double radius;
	private Date fromDate;
	private Date toDate;
	private String pitchRole;

	public String getChosenSport() {
		return chosenSport;
	}
	public void setChosenSport(String chosenSport) {
		this.chosenSport = chosenSport;
	}
	public String getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(String minLevel) {
		this.minLevel = minLevel;
	}
	public String getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
	}
	public Integer getMinStillNeeded() {
		return minStillNeeded;
	}
	public void setMinStillNeeded(Integer minStillNeeded) {
		this.minStillNeeded = minStillNeeded;
	}

	public Date getFromDate() {
		if (fromDate != null) {
			return (Date) fromDate.clone();
		}
		return null;
	}
	public void setFromDate(Date fromDate) {
		if (fromDate != null) {
			this.fromDate = (Date) fromDate.clone();
		}
	}
	public Date getToDate() {
		if (toDate != null) {
			return (Date) toDate.clone();
		}
		return null;
	}
	public void setToDate(Date toDate) {
		if (toDate != null) {
			this.toDate = (Date) toDate.clone();
		}
	}
	public String getPitchRole() {
		return pitchRole;
	}
	public void setPitchRole(String role) {
		this.pitchRole = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public String getPitchType() {
		return pitchType;
	}
	public void setPitchType(String pitchType) {
		this.pitchType = pitchType;
	}

}
