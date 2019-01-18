package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GameDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Game;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import com.example.demo.form.NotificationForm;
import com.example.demo.response.ResponseMessage;
import com.example.demo.utils.NotificationType;
import com.example.demo.wrapper.NotificationWrapper;

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
	public ResponseEntity<?> sendNotification(
			NotificationForm notificationForm) {
		User receiver = userDao.findById(notificationForm.getReceiverId())
				.orElse(null);

		User sender = userDao.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName()).orElse(null);
		Game game = gameDao.findById(notificationForm.getGameId()).orElse(null);

		Notification notification = new Notification(sender, receiver, game,
				NotificationType.valueOf(notificationForm.getType()),
				notificationForm.getBody(), notificationForm.getRead());
		receiver.addNotification(notification);
		return new ResponseEntity<>(
				new ResponseMessage("Notification sent successfully!"),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public List<NotificationWrapper> showNotificationWrappers(Long id) {
		User receiver = userDao.findById(id).orElse(null);
		List<Notification> notifications = receiver.getNotificationsReceived();
		List<NotificationWrapper> notificationWrappers = new ArrayList<>();
		Long gameId;

		for (Notification notification : notifications) {
			if (notification.getGame() == null) {
				gameId = (long) -1;
			} else {
				gameId = notification.getGame().getId();
			}
			notificationWrappers.add(new NotificationWrapper(gameId,
					notification.getSender().getUserName(),
					notification.getType().name(),
					notification.getMessageBody(), notification.getRead()));
		}
		return notificationWrappers;
	}

}
