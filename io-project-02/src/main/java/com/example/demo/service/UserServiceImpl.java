package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GameDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Game;
import com.example.demo.entity.Invitation;
import com.example.demo.entity.User;
import com.example.demo.form.InvitationForm;
import com.example.demo.wrapper.InvitationWrapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private GameDao gameDao;

	@Override
	@Transactional
	public User findUserByUserName(String userName) {
		User user = userDao.findByUserName(userName).orElse(null);
		return user;
	}

	@Override
	@Transactional
	public void sendInvitation(Long game_id, InvitationForm invitationForm) {
		User receiver = userDao.findById(invitationForm.getReceiverId())
				.orElse(null);
		User sender = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName()).orElse(null);
		Game game = gameDao.findById(game_id).orElse(null);

		Invitation invitation = new Invitation(sender, receiver, game,
				invitationForm.getRead());
		System.out.println(">>>>>>>>>>>>>>>>>>>>Receiver: "
				+ receiver.getUserName() + " Sender: " + sender.getUserName()
				+ " Game: " + game.getId());
		receiver.addInvitiation(invitation);

	}

	@Override
	@Transactional
	public List<InvitationWrapper> showInvitationWrappers(Long id) {
		User receiver = userDao.findById(id).orElse(null);
		List<Invitation> invitations = receiver.getInvitationsReceived();
		List<InvitationWrapper> invitationWrappers = new ArrayList<>();

		for (Invitation invitation : invitations) {
			invitationWrappers.add(new InvitationWrapper(
					invitation.getGame().getId(),
					invitation.getSender().getId(), invitation.getRead()));
		}
		return invitationWrappers;
	}

}
