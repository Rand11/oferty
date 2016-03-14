package com.adrian.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.adrian.dao.MessagesDao;
import com.adrian.dao.UsersDao;
import com.adrian.entity.Message;
import com.adrian.entity.User;

@Service("usersService")
public class UsersService {
	
	private static Logger logger = Logger.getLogger(UsersService.class);
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;
	
	public void create(User user) {
		logger.info("User created: " + user.toString());
		usersDao.create(user);
	}


	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public void sendMessage(Message message) {
		messagesDao.saveOrUpdate(message);
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}


	public List<Message> getMessages(String username) {
		return messagesDao.getMessages(username);
	}

}
