package com.example.demo.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.SportObject;
import com.example.demo.utils.LevelType;

public class LobbyWrapper {

	private Long id;
	private String discipline;
	private Float cost;
	private Integer needed;
	private Date date;
	private LevelType level;
	private Date priorityDate;
	private SportObject sportObject;
	private String owner;
	private List<String> firstSquad = new ArrayList<>();
	private List<String> reserve = new ArrayList<>();
	private Map<String, Integer> rolePriorities = new HashMap<>();
}
