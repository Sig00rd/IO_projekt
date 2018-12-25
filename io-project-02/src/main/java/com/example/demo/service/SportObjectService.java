package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SportObject;

public interface SportObjectService {

	List<SportObject> getSportObjects();

	void addObject(SportObject sportObject);

}
