package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Game;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {

}
