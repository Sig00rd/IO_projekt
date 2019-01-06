package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Role;
import com.example.demo.utils.RoleName;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}
