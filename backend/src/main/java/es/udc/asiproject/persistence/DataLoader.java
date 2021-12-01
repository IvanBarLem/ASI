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
		if (userDao.count() == 0) {
			userDao.save(User.builder().email("gerente@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Gerente").lastName("Perez").role(RoleType.GERENTE).build());

			userDao.save(User.builder().email("agente@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Agente").lastName("Guzman").role(RoleType.AGENTE).build());

			userDao.save(User.builder().email("informatico@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Informático").lastName("Salgado").role(RoleType.INFORMATICO).build());

			userDao.save(User.builder().email("agente1@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Agente1").lastName("Ferrer").role(RoleType.AGENTE).build());

			userDao.save(User.builder().email("agente2@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Agente2").lastName("Gil").role(RoleType.AGENTE).build());

			userDao.save(User.builder().email("agente3@gmail.com").password(passwordEncoder.encode("pass"))
					.firstName("Agente3").lastName("Pita").role(RoleType.AGENTE).build());
		}

		if (accommodationDao.count() == 0) {
			accommodationDao.save(Accommodation.builder().name("Hesperia").location("Madrid")
					.price(new BigDecimal(50.0)).hidden(false).build());
			accommodationDao.save(Accommodation.builder().name("AC Hoteles").location("Madrid")
					.price(new BigDecimal(49.99)).hidden(false).build());
			accommodationDao.save(Accommodation.builder().name("Casa Pepa").location("Madrid")
					.price(new BigDecimal(70.99)).hidden(false).build());
			accommodationDao.save(Accommodation.builder().name("Pension Luis").location("Madrid")
					.price(new BigDecimal(120.00)).hidden(false).build());
		}

		if (activityDao.count() == 0) {
			activityDao.save(Activity.builder().name("Paseo en Bicicleta").location("Madrid")
					.price(new BigDecimal(10.00)).hidden(false).build());
			activityDao.save(Activity.builder().name("Motos de Agua").location("Madrid").price(new BigDecimal(25.00))
					.hidden(false).build());
			activityDao.save(Activity.builder().name("Karts").location("Madrid").price(new BigDecimal(15.00))
					.hidden(false).build());
			activityDao.save(Activity.builder().name("Surf").location("Madrid").price(new BigDecimal(30.00))
					.hidden(false).build());
		}

		if (transportDao.count() == 0) {
			transportDao.save(Transport.builder().name("Avión").location("Madrid").price(new BigDecimal(150.00))
					.hidden(false).build());
			transportDao.save(Transport.builder().name("Barco").location("Madrid").price(new BigDecimal(90.00))
					.hidden(false).build());
			transportDao.save(Transport.builder().name("Bus").location("Madrid").price(new BigDecimal(8.50))
					.hidden(false).build());
			transportDao.save(Transport.builder().name("Tren").location("Madrid").price(new BigDecimal(18.00))
					.hidden(false).build());
		}

		if (travelDao.count() == 0) {
			travelDao.save(Travel.builder().name("Madrid Cultural").location("Madrid").price(new BigDecimal(20.99))
					.hidden(false).build());
			travelDao.save(Travel.builder().name("Amazonas Salvaje").location("Madrid").price(new BigDecimal(14.99))
					.hidden(false).build());
			travelDao.save(Travel.builder().name("Caribe Tropical").location("Madrid").price(new BigDecimal(90.00))
					.hidden(false).build());
			travelDao.save(Travel.builder().name("Tibet Espiritual").location("Madrid").price(new BigDecimal(290.00))
					.hidden(false).build());
		}

		if (saleDao.count() == 0) {
			User client = User.builder().email("test_user@gmail.com").password("pass").firstName("User")
					.lastName("Pita").role(RoleType.USER).build();
			userDao.save(client);

			List<Accommodation> accommodations = new ArrayList<Accommodation>();
			for (Long accommodationId = 0L; accommodationId < 4; accommodationId++) {
				Accommodation accommodation = Accommodation.builder().name("accommodation" + accommodationId)
						.location("A Coruna").price(new BigDecimal(1.25)).hidden(false).build();
				accommodationDao.save(accommodation);
				accommodations.add(accommodation);
			}

			List<Activity> activities = new ArrayList<Activity>();
			for (Long activityId = 0L; activityId < 5; activityId++) {
				Activity activity = Activity.builder().name("activity" + activityId).location("Lugo")
						.price(new BigDecimal(1)).hidden(false).build();
				activityDao.save(activity);
				activities.add(activity);
			}

			List<Transport> transports = new ArrayList<Transport>();
			for (Long transportId = 0L; transportId < 10; transportId++) {
				Transport transport = Transport.builder().name("transport" + transportId).location("Ourense")
						.price(new BigDecimal(0.5)).hidden(false).build();
				transportDao.save(transport);
				transports.add(transport);
			}

			List<Travel> travels = new ArrayList<Travel>();
			for (Long travelId = 0L; travelId < 2; travelId++) {
				Travel travel = Travel.builder().name("travel" + travelId).location("Pontevedra")
						.price(new BigDecimal(2.5)).hidden(false).build();
				travelDao.save(travel);
				travels.add(travel);
			}

			for (Long agentId = 0L; agentId < 5; agentId++) {
				User agent = User.builder().email("test_agente" + agentId + "@gmail.com").password("pass")
						.firstName("Agente" + agentId).lastName("Pita").role(RoleType.AGENTE).build();
				userDao.save(agent);

				for (Long saleId = 0L; saleId < 5; saleId++) {
					Sale sale = Sale.builder().state(SaleState.PAID).price(new BigDecimal(10)).agent(agent)
							.client(client).createdAt(parseDate("202" + saleId + "-01-01")).build();
					saleDao.save(sale);

					Set<SaleProduct> saleProduct = new HashSet<SaleProduct>();
					for (Accommodation accommodation : accommodations) {
						saleProduct.add(new SaleProduct(sale, accommodation, 4));
					}
					for (Activity activity : activities) {
						saleProduct.add(new SaleProduct(sale, activity, 4));
					}
					for (Transport transport : transports) {
						saleProduct.add(new SaleProduct(sale, transport, 4));
					}
					for (Travel travel : travels) {
						saleProduct.add(new SaleProduct(sale, travel, 4));
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
}
