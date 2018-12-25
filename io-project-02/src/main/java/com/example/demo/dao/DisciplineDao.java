package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Discipline;

@Repository
public interface DisciplineDao extends JpaRepository<Discipline, Integer> {

	@Query(value = "SELECT d FROM Discipline d WHERE d.name = ?1")
	public Discipline findDisciplineByName(String name);

}
