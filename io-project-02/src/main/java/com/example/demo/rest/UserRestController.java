package com.example.demo.rest;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.service.UserService;
import com.example.demo.user.CrmUser;

@RestController
public class UserRestController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public CrmUser registerUser(@RequestBody CrmUser crmUser) {
		logger.info(">>>>Processing user: " + crmUser);
		logger.info(">>>>Encoded password: "
				+ passwordEncoder.encode(crmUser.getPassword()));
		User existing = userService.findByUserName(crmUser.getUserName());
		if (existing != null) {
			throw new UserAlreadyExistsException(
					"user '" + crmUser.getUserName() + "' already exists!");

		} else {
			userService.save(crmUser);
		}

		return crmUser;
	}
}
