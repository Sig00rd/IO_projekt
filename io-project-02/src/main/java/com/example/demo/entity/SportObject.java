package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.utils.SportObjectType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sport_object")
public class SportObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "is required")
	@Size(min = 3, message = "must be at least 3 character long")
	@Column(name = "name")
	private String name;

	@NotNull(message = "is required")
	@Size(min = 4, message = "must be at least 4 character long")
	@Column(name = "address")
	private String address;

	@NotNull(message = "is required")
	@Size(min = 4, message = "must be at least 4 character long")
	@Column(name = "city")
	private String city;

	@Column(name = "type")
	@NotNull(message = "is required")
	@Enumerated(EnumType.STRING)
	private SportObjectType type;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sportObject")
	private List<Game> games = new ArrayList<>();

	public SportObject() {
	}

	public SportObject(String name, String address, String city,
			SportObjectType type) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SportObjectType getType() {
		return type;
	}

	public void setType(SportObjectType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SportObject [id=" + id + ", name=" + name + ", address="
				+ address + ", type=" + type + ", games=" + games + "]";
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
