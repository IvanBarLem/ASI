package es.udc.asiproject.persistence.data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.SaleDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.persistence.model.Sale.SaleState;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.User.RoleType;

@Component
@Profile("!test")
public class SaleDataLoader implements ApplicationRunner {

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private AccommodationDao accommodationDao;

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private TransportDao transportDao;

	@Autowired
	private TravelDao travelDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (accommodationDao.count() == 0 || accommodationDao.count() == 4 && activityDao.count() == 0
				|| activityDao.count() == 4 && transportDao.count() == 0
				|| transportDao.count() == 4 && travelDao.count() == 0 || travelDao.count() == 4 && userDao.count() == 0
				|| userDao.count() == 5 && saleDao.count() == 0) {

			Accommodation accommodation1 = accommodationDao.save(new Accommodation("Melia", new BigDecimal(50.0)));
			Accommodation accommodation2 = accommodationDao
					.save(new Accommodation("Attica Hoteles", new BigDecimal(49.99)));
			Accommodation accommodation3 = accommodationDao
					.save(new Accommodation("Casa Juana", new BigDecimal(70.99)));
			Accommodation accommodation4 = accommodationDao
					.save(new Accommodation("Pension Roberto", new BigDecimal(120.00)));

			Activity activity1 = activityDao.save(new Activity("Paseo en Monociclo", new BigDecimal(10.00)));
			Activity activity2 = activityDao.save(new Activity("Motos-Cross", new BigDecimal(25.00)));
			Activity activity3 = activityDao.save(new Activity("Quads", new BigDecimal(15.00)));
			Activity activity4 = activityDao.save(new Activity("Bodyboard", new BigDecimal(30.00)));

			Transport transport1 = transportDao.save(new Transport("Boeing 747", new BigDecimal(150.00)));
			Transport transport2 = transportDao.save(new Transport("Titanic", new BigDecimal(90.00)));
			Transport transport3 = transportDao.save(new Transport("Double Decker Bus", new BigDecimal(8.50)));
			Transport transport4 = transportDao.save(new Transport("AVE", new BigDecimal(18.00)));

			Travel travel1 = travelDao.save(new Travel("Madrid Barrios Antiguos", new BigDecimal(20.99)));
			Travel travel2 = travelDao.save(new Travel("Tanzania Salvaje", new BigDecimal(14.99)));
			Travel travel3 = travelDao.save(new Travel("Bosques Tropicales", new BigDecimal(90.00)));
			Travel travel4 = travelDao.save(new Travel("Everest", new BigDecimal(290.00)));

			User user1 = new User(passwordEncoder.encode("pass"), "Gerent", "Perez", "gerent@gmail.com");
			user1.setRole(RoleType.GERENTE);
			user1 = userDao.save(user1);

			User user2 = new User(passwordEncoder.encode("pass"), "Agent1", "Ferrer", "agent1@gmail.com");
			user2.setRole(RoleType.AGENTE);
			user2 = userDao.save(user2);

			User user3 = new User(passwordEncoder.encode("pass"), "Agent2", "Gil", "agent2@gmail.com");
			user3.setRole(RoleType.AGENTE);
			user3 = userDao.save(user3);

			User user4 = new User(passwordEncoder.encode("pass"), "Agent3", "Pita", "agent3@gmail.com");
			user4.setRole(RoleType.AGENTE);
			user4 = userDao.save(user4);

			User user5 = new User(passwordEncoder.encode("pass"), "Client", "Salgado", "client@gmail.com");
			user5.setRole(RoleType.USER);
			user5 = userDao.save(user5);

			Set<Accommodation> accommodations1 = new HashSet<Accommodation>();
			accommodations1.add(accommodation1);
			accommodations1.add(accommodation2);

			Set<Activity> activities1 = new HashSet<Activity>();
			activities1.add(activity1);
			activities1.add(activity2);

			Set<Transport> transports1 = new HashSet<Transport>();
			transports1.add(transport1);
			transports1.add(transport2);

			Set<Travel> travels1 = new HashSet<Travel>();
			travels1.add(travel1);
			travels1.add(travel2);

			Sale sale1 = new Sale();
			sale1.setState(SaleState.FREEZE);
			sale1.setPrice(new BigDecimal(30000));
			sale1.setCreatedAt(parseDate("2018-01-01"));
			sale1.setAgent(user2);
			sale1.setClient(user5);
			sale1.setAccommodations(accommodations1);
			sale1.setActivities(activities1);
			sale1.setTransports(transports1);
			sale1.setTravels(travels1);
			saleDao.save(sale1);

		}

	}

}
