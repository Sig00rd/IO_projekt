package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GamePrioritiesDao;

@Service
public class GamePrioritiesServiceImpl implements GamePrioritiesService {

	@Autowired
	private GamePrioritiesDao gamePrioritiesDao;

	@Override
	public void save(Map<String, Integer> pitchRoles) {

	}

}
