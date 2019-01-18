package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.User;
import com.example.demo.form.NotificationForm;
import com.example.demo.wrapper.NotificationWrapper;

public interface UserService {

	User findUserByUserName(String userName);

	List<NotificationWrapper> showNotificationWrappers();

	ResponseEntity<?> sendNotification(NotificationForm notificationForm);

	ResponseEntity<?> markNotificationAsReaded(Long id);

}
