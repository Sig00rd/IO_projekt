package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SportObject;

@Repository
public interface SportObjectDao extends JpaRepository<SportObject, Integer> {

	@Query("select s from SportObject s where s.name = ?1")
	public SportObject findSportObjectByName(String name);

}
