package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.form.InvitationForm;
import com.example.demo.wrapper.InvitationWrapper;

public interface UserService {

	User findUserByUserName(String userName);

	void sendInvitation(Long game_id, InvitationForm invitationForm);

	List<InvitationWrapper> showInvitationWrappers(Long id);

}
