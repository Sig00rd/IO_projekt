package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.entity.User;
import com.example.demo.user.CrmUser;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	void save(CrmUser crmUser);
}
