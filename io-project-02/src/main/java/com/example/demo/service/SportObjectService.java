package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.entity.SportObject;
import com.google.maps.errors.ApiException;

public interface SportObjectService {

	List<SportObject> getSportObjects();

	void addObject(SportObject sportObject)
			throws ApiException, InterruptedException, IOException;

}
