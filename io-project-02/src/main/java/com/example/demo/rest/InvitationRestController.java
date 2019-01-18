package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.NotificationForm;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.NotificationWrapper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvitationRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/notifications")
	public ResponseEntity<?> sendNotification(
			@RequestBody NotificationForm notificationForm) {

		return userService.sendNotification(notificationForm);

	}
	@GetMapping("/notifications/{id}") // parametr id to identyfikator
										// uzytkownika
	public List<NotificationWrapper> showNotificationWrappers(
			@PathVariable Long id) {

		return userService.showNotificationWrappers(id);

	}

}
