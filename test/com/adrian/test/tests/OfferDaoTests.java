package com.adrian.test.tests;

import static org.junit.Assert.*;

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

import com.adrian.dao.OffersDao;
import com.adrian.dao.UsersDao;
import com.adrian.entity.Offer;
import com.adrian.entity.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/adrian/config/dao-context.xml",
		"classpath:com/adrian/config/security-context.xml", "classpath:com/adrian/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("adrianz1234", "adrian z", "hellothere", "adrian@adrian.com", true, "ROLE_USER");
	private User user2 = new User("ktosinny", "ktos inny", "haslo123", "ktos@adrian.com", true, "ROLE_ADMIN");
	private User user3 = new User("janina1337", "Janina Kowalska", "haslo123", "janina1337@mail.com", true,
			"ROLE_USER");
	private User user4 = new User("johnwayne", "D¿on £ejn", "haslo123", "john@adrian.com", false, "user");

	private Offer offer1 = new Offer(user1, "Offer 1 text.");
	private Offer offer2 = new Offer(user1, "Offer 2 text.");
	private Offer offer3 = new Offer(user2, "Offer 3 text.");
	private Offer offer4 = new Offer(user3, "Offer 4 text.");
	private Offer offer5 = new Offer(user3, "Offer 5 text.");
	private Offer offer6 = new Offer(user3, "Offer 6 text.");
	private Offer offer7 = new Offer(user4, "Offer 7 text. Sprzedam Opla.");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
	}

	@Test
	public void testDelete() {

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		assertNotNull("Offer with ID = " + retrieved1.getId() + " shouldn't be null", retrieved1);

		offersDao.delete(offer2.getId());

		Offer retrieved2 = offersDao.getOffer(offer2.getId());
		assertNull("Offer with ID = " + retrieved1.getId() + " should be null", retrieved2);
	}

	@Test
	public void testGetById() {

		saveAllOffers();

		Offer retrieved1 = offersDao.getOffer(offer1.getId());
		assertEquals("Offers should be equal", offer1, retrieved1);

		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		assertNull("Shouldn't retrieve offer for disabled user.", retrieved2);
	}

	@Test
	public void testCreateRetrieve() {

		offersDao.saveOrUpdate(offer1);

		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be 1 offer.", 1, offers1.size());

		assertEquals("Retrieved offer should be equal to the saved offer.", offer1, offers1.get(0));

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Enabled users should have 6 offers.", 6, offers2.size());

	}

	@Test
	public void testUpdate() {

		saveAllOffers();

		offer3.setText("Updated offer text.");
		offersDao.saveOrUpdate(offer3);

		Offer retrieved = offersDao.getOffer(offer3.getId());
		assertEquals("Offer should be updated.", offer3, retrieved);
	}

	@Test
	public void testGetUsername() {

		saveAllOffers();

		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be 3 offers.", 3, offers1.size());

		List<Offer> offers2 = offersDao.getOffers("gfdsgfdsgfdsgfdsgfds");
		assertEquals("Should be no offers.", 0, offers2.size());

		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be 1 offer.", 1, offers3.size());
	}

	private void saveAllOffers() {
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
	}

}
