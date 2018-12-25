package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SportObjectDao;
import com.example.demo.entity.SportObject;

@Service
public class SportObjectServiceImpl implements SportObjectService {

	@Autowired
	private SportObjectDao sportObjectDao;

	@Override
	@Transactional
	public List<SportObject> getSportObjects() {
		return sportObjectDao.findAll();
	}

	@Override
	public void addObject(SportObject sportObject) {
		sportObjectDao.save(sportObject);

	}

}
