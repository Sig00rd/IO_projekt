package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.form.InvitationForm;

public interface UserService {

	User findUserByUserName(String userName);

	void sendInvitation(Long game_id, InvitationForm invitationForm);

}
