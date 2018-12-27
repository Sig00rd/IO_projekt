package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PitchRole;

@Repository
public interface PitchRoleDao extends JpaRepository<PitchRole, Long> {

	@Query(value = "select p from PitchRole p where p.name = ?1")
	public PitchRole findPitchRoleByName(String key);

}
