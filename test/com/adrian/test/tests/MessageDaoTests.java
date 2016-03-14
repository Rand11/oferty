package com.adrian.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adrian.dao.MessagesDao;
import com.adrian.dao.UsersDao;
import com.adrian.entity.Message;
import com.adrian.entity.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/adrian/config/dao-context.xml",
		"classpath:com/adrian/config/security-context.xml",
		"classpath:com/adrian/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("adrianz1234", "adrian z", "haslo123",
			"adrian@adrian.com", true, "ROLE_USER");
	private User user2 = new User("ktosinny", "ktos inny",
			"haslo123", "ktos@adrian.com", true, "ROLE_ADMIN");
	
	
	private Message message1 = new Message("Message 1 subject", "Message 1 content", "Jan Kowalski", "jan@adrian.com", user1.getUsername());
	private Message message2 = new Message("Message 2 subject", "Message 2 content", "Jan Kowalski", "jan@adrian.com", user1.getUsername());
	private Message message3 = new Message("Message 3 subject", "Message 3 content", "Jan Kowalski", "jan@adrian.com", user2.getUsername());
	

	

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
		
		usersDao.create(user1);
		usersDao.create(user2);
	}
	
	@Test
	public void testSaveRetrieve() {

		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		assertEquals(2, messages.size());
		
		messages = messagesDao.getMessages(user2.getUsername());
		assertEquals(1, messages.size());
	}
	
	@Test
	public void testRetrieveById() {
		
		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		
		for(Message message: messages) {
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
	}
	
	@Test
	public void testDelete() {
		
		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		
		for(Message message: messages) {
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
		
		Message toDelete = messages.get(1);
		
		assertNotNull("Message shouldn't be deleted yet.", messagesDao.getMessage(toDelete.getId()));
		
		messagesDao.delete(toDelete.getId());
		
		assertNull("Message should be deleted.", messagesDao.getMessage(toDelete.getId()));
	}
	
}
