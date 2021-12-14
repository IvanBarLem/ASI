package es.udc.asiproject.persistence;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import es.udc.asiproject.persistence.model.SaleProduct;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.enums.RoleType;
import es.udc.asiproject.persistence.model.enums.SaleState;

@Component
@Profile("!test")
public class DataLoader implements ApplicationRunner {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AccommodationDao accommodationDao;
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private TransportDao transportDao;
	@Autowired
	private TravelDao travelDao;
	@Autowired
	SaleDao saleDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String[] locations = { "Madrid", "A Coruna", "Lugo", "Ourense", "Pontevedra" };

		if (userDao.count() == 0) {
			userDao.save(User.builder().email("gerente@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Gerente").lastName("Perez").role(RoleType.GERENTE).build());

			userDao.save(User.builder().email("agente@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Agente").lastName("Guzman").role(RoleType.AGENTE).build());

			userDao.save(User.builder().email("informatico@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Informático").lastName("Salgado").role(RoleType.INFORMATICO).build());
		}

		if (accommodationDao.count() == 0) {
			accommodationDao.save(Accommodation.builder().name("Hesperia").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			accommodationDao.save(Accommodation.builder().name("AC Hoteles").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			accommodationDao.save(Accommodation.builder().name("Casa Pepa").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			accommodationDao
					.save(Accommodation.builder().name("Pension Luis").location(locations[getRandomNumber(0, 5)])
							.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
		}

		if (activityDao.count() == 0) {
			activityDao.save(Activity.builder().name("Paseo en Bicicleta").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			activityDao.save(Activity.builder().name("Motos de Agua").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			activityDao.save(Activity.builder().name("Karts").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			activityDao.save(Activity.builder().name("Surf").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
		}

		if (transportDao.count() == 0) {
			transportDao.save(Transport.builder().name("Avión").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			transportDao.save(Transport.builder().name("Barco").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			transportDao.save(Transport.builder().name("Bus").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			transportDao.save(Transport.builder().name("Tren").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
		}

		if (travelDao.count() == 0) {
			travelDao.save(Travel.builder().name("Madrid Cultural").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			travelDao.save(Travel.builder().name("Amazonas Salvaje").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			travelDao.save(Travel.builder().name("Caribe Tropical").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
			travelDao.save(Travel.builder().name("Tibet Espiritual").location(locations[getRandomNumber(0, 5)])
					.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build());
		}

		if (saleDao.count() == 0) {
			User client = User.builder().email("user@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("User").lastName("Pita").role(RoleType.USER).build();
			userDao.save(client);

			List<Accommodation> accommodations = new ArrayList<Accommodation>();
			for (Long accommodationId = 0L; accommodationId < 10; accommodationId++) {
				Accommodation accommodation = Accommodation.builder().name("accommodation" + accommodationId)
						.location(locations[getRandomNumber(0, 5)]).price(new BigDecimal(getRandomNumber(1, 100)))
						.hidden(false).build();
				accommodationDao.save(accommodation);
				accommodations.add(accommodation);
			}

			List<Activity> activities = new ArrayList<Activity>();
			for (Long activityId = 0L; activityId < 10; activityId++) {
				Activity activity = Activity.builder().name("activity" + activityId)
						.location(locations[getRandomNumber(0, 5)]).price(new BigDecimal(getRandomNumber(1, 100)))
						.hidden(false).build();
				activityDao.save(activity);
				activities.add(activity);
			}

			List<Transport> transports = new ArrayList<Transport>();
			for (Long transportId = 0L; transportId < 10; transportId++) {
				Transport transport = Transport.builder().name("transport" + transportId)
						.location(locations[getRandomNumber(0, 5)]).price(new BigDecimal(getRandomNumber(1, 100)))
						.hidden(false).build();
				transportDao.save(transport);
				transports.add(transport);
			}

			List<Travel> travels = new ArrayList<Travel>();
			for (Long travelId = 0L; travelId < 10; travelId++) {
				Travel travel = Travel.builder().name("travel" + travelId).location(locations[getRandomNumber(0, 5)])
						.price(new BigDecimal(getRandomNumber(1, 100))).hidden(false).build();
				travelDao.save(travel);
				travels.add(travel);
			}

			for (Long agentId = 0L; agentId < 5; agentId++) {
				User agent = User.builder().email("agente" + agentId + "@gmail.com")
						.password(passwordEncoder.encode("pass")).firstName("Agente" + agentId).lastName("Pita")
						.role(RoleType.AGENTE).build();
				userDao.save(agent);

				for (Long saleId = 0L; saleId < 50; saleId++) {
					Sale sale = Sale.builder().state(SaleState.PAID).price(new BigDecimal(getRandomNumber(1, 1000)))
							.agent(agent).client(client)
							.createdAt(saleId < 25
									? parseDate("2021-" + getRandomNumber(10, 11) + "-" + getRandomNumber(1, 15))
									: parseDate("2021-" + getRandomNumber(11, 12) + "-" + getRandomNumber(15, 30)))
							.build();
					saleDao.save(sale);

					Set<SaleProduct> saleProduct = new HashSet<SaleProduct>();
					for (Accommodation accommodation : accommodations) {
						saleProduct.add(new SaleProduct(sale, accommodation, getRandomNumber(1, 10)));
					}
					for (Activity activity : activities) {
						saleProduct.add(new SaleProduct(sale, activity, getRandomNumber(1, 10)));
					}
					for (Transport transport : transports) {
						saleProduct.add(new SaleProduct(sale, transport, getRandomNumber(1, 10)));
					}
					for (Travel travel : travels) {
						saleProduct.add(new SaleProduct(sale, travel, getRandomNumber(1, 10)));
					}
					sale.setProducts(saleProduct);

					saleDao.save(sale);
				}
			}
		}
	}

	private Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

	private Integer getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
