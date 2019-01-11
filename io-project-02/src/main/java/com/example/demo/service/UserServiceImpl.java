package com.example.demo.service;

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
		User receiver = userDao.findById(invitationForm.getUserId())
				.orElse(null);
		User sender = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName()).orElse(null);
		Game game = gameDao.findById(game_id).orElse(null);
		Invitation invitation = new Invitation(sender, receiver, game,
				invitationForm.getRead());

		receiver.addInvitiation(invitation);

	}

}
