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

import com.example.demo.form.InvitationForm;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.InvitationWrapper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvitationRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/invitations/{id}") // parametr id to identyfikator gry
	public ResponseEntity<?> sendInvitation(@PathVariable Long id,
			@RequestBody InvitationForm invitationForm) {

		return userService.sendInvitation(id, invitationForm);

	}
	@GetMapping("/invitations/{id}") // parametr id to identyfikator uzytkownika
	public List<InvitationWrapper> showInvitationWrappers(
			@PathVariable Long id) {

		return userService.showInvitationWrappers(id);

	}

}
