package com.adrian.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import com.adrian.dao.UsersDao;
import com.adrian.entity.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/adrian/config/dao-context.xml",
		"classpath:com/adrian/config/security-context.xml",
		"classpath:com/adrian/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("adrianz1234", "adrian z", "hellothere",
			"adrian@adrian.com", true, "ROLE_USER");
	private User user2 = new User("ktosinny", "ktos inny", "the39steps",
			"ktos@adrian.com", true, "ROLE_ADMIN");
	private User user3 = new User("janina1337", "Janina Kowalska", "iloveviolins",
			"janina1337@mail.com", true, "ROLE_USER");
	private User user4 = new User("johnwayne", "D¿on £ejn", "liberator",
			"rog@adrian.com", false, "user");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		
		List<User> users1 = usersDao.getAllUsers();
		
		assertEquals("Exactly 1 user should be created and retrieved.", 1, users1.size());
		
		assertEquals("Inserted and retrieved user should be the same.", user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users2 = usersDao.getAllUsers();
		
		assertEquals("Should retreive 4 users.", 4, users2.size());
	}
	
	@Test
	public void testExists() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
		assertFalse("User shouldn't exist.", usersDao.exists("jkhgkjhgkjgkjg"));
	}
}
