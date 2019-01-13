package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SportObjectDao;
import com.example.demo.entity.SportObject;
import com.example.demo.utils.EarthDist;
import com.google.maps.errors.ApiException;

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
	public void addObject(SportObject sportObject)
			throws ApiException, InterruptedException, IOException {

		Double[] latLng = EarthDist.lookupCoord(
				sportObject.getAddress() + ", " + sportObject.getCity());
		sportObject.setLatitude(latLng[0]);
		sportObject.setLongitude(latLng[1]);
		sportObjectDao.save(sportObject);

	}

}
