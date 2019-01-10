package com.example.demo.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SportObject;
import com.example.demo.service.SportObjectService;
import com.google.maps.errors.ApiException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class SportObjectRestController {

	@Autowired
	private SportObjectService sportObjectService;

	@GetMapping("/sportObjects")
	public List<SportObject> getSportObjects() {
		List<SportObject> sportObjects = sportObjectService.getSportObjects();

		return sportObjects;
	}

	@PostMapping("/sportObjects")
	public List<SportObject> addObject(@RequestBody SportObject sportObject)
			throws ApiException, InterruptedException, IOException {

		sportObjectService.addObject(sportObject);
		return this.getSportObjects();

	}

}
