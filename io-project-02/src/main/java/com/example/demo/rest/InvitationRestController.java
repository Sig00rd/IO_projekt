package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.InvitationForm;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class InvitationRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/invitations/{id}")
	public void sendInvitation(@PathVariable Long game_id,
			@RequestBody InvitationForm invitationForm) {

		userService.sendInvitation(game_id, invitationForm);

	}

}
