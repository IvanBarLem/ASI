package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.controller.dto.CreateSaleParamsDto;
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
import es.udc.asiproject.service.SaleService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class SaleServiceTest {
	@Autowired
	SaleService saleService;
	@Autowired
	UserDao userDao;
	@Autowired
	AccommodationDao accommodationDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	TransportDao transportDao;
	@Autowired
	TravelDao travelDao;
	@Autowired
	SaleDao saleDao;

	private Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

	private User seedClientDatabase(String email) {
		User user = User.builder().email(email).password("password").firstName("firstName").lastName("lastName")
				.role(RoleType.USER).build();
		userDao.save(user);

		return user;
	}

	private User seedAgentDatabase(String email) {
		User user = User.builder().email(email).password("password").firstName("firstName").lastName("lastName")
				.role(RoleType.AGENTE).build();
		userDao.save(user);

		return user;
	}

	private User seedGerenteDatabase(String email) {
		User user = User.builder().email(email).password("password").firstName("firstName").lastName("lastName")
				.role(RoleType.GERENTE).build();
		userDao.save(user);

		return user;
	}

	private Accommodation seedAccommodationDatabase() {
		Accommodation accommodation = Accommodation.builder().name("Hesperia marineda").location("Madrid")
				.price(new BigDecimal(1.23)).hidden(false).build();
		accommodationDao.save(accommodation);

		return accommodation;
	}

	private Activity seedActivityDatabase() {
		Activity activity = Activity.builder().name("Motos de Agua").location("Madrid").price(new BigDecimal(1.23))
				.hidden(false).build();
		activityDao.save(activity);

		return activity;
	}

	private Transport seedTransportDatabase() {
		Transport transport = Transport.builder().name("Patineta").location("Madrid").price(new BigDecimal(1.23))
				.hidden(false).build();
		transportDao.save(transport);

		return transport;
	}

	private Travel seedTravelDatabase() {
		Travel travel = Travel.builder().name("Egipto Antiguo").location("Madrid").price(new BigDecimal(1.23))
				.hidden(false).build();
		travelDao.save(travel);

		return travel;
	}

	private void seedSaleDatabase() throws ParseException {
		User client = User.builder().email("test_user@gmail.com").password("pass").firstName("User").lastName("Pita")
				.role(RoleType.USER).build();
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
			Travel travel = Travel.builder().name("travel" + travelId).location("Pontevedra").price(new BigDecimal(2.5))
					.hidden(false).build();
			travelDao.save(travel);
			travels.add(travel);
		}

		for (Long agentId = 0L; agentId < 5; agentId++) {
			User agent = User.builder().email("test_agente" + agentId + "@gmail.com").password("pass")
					.firstName("Agente" + agentId).lastName("Pita").role(RoleType.AGENTE).build();
			userDao.save(agent);

			for (Long saleId = 0L; saleId < 5; saleId++) {
				Sale sale = Sale.builder().state(SaleState.PAID).price(new BigDecimal(10)).agent(agent).client(client)
						.createdAt(parseDate("202" + saleId + "-01-01")).build();
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
			}
		}
	}

	/*
	 * Resuelve CU 2. Prueba para comprobar que una venta puede darse de alta en la
	 * aplicación.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorías a las que pertenece: prueba funcional dinámica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de selección de datos: prueba con generación de datos de entrada
	 * estática.
	 */
	@Test
	public void should_return_new_sale() throws InstanceNotFoundException, InvalidOperationException {
		User agent = seedClientDatabase("agent@gmail.com");
		User client = seedClientDatabase("client@gmail.com");
		Accommodation accommodation = seedAccommodationDatabase();
		Activity activity = seedActivityDatabase();
		Travel travel = seedTravelDatabase();
		Transport transport = seedTransportDatabase();
		CreateSaleParamsDto saleParams = new CreateSaleParamsDto();
		saleParams.setPrice(new BigDecimal(10));
		saleParams.setClientId(client.getId());
		saleParams.setAccommodation(accommodation.getId(), 1);
		saleParams.setActivity(activity.getId(), 2);
		saleParams.setTransport(transport.getId(), 3);
		saleParams.setTravel(travel.getId(), 4);

		Sale sale = saleService.createSale(saleParams, agent.getId());

		assertAll(() -> {
			assertEquals(sale.getState(), SaleState.NORMAL);
			assertEquals(sale.getPrice(), new BigDecimal(10));
			assertEquals(sale.getAgent(), agent);
			assertEquals(sale.getClient(), client);
			assertEquals(sale.getProducts().size(), 4);
			for (SaleProduct saleProduct : sale.getProducts()) {
				switch (saleProduct.getQuantity()) {
				case 1:
					assertEquals(saleProduct.getProduct(), accommodation);
					break;
				case 2:
					assertEquals(saleProduct.getProduct(), activity);
					break;
				case 3:
					assertEquals(saleProduct.getProduct(), transport);
					break;
				case 4:
					assertEquals(saleProduct.getProduct(), travel);
					break;
				}
			}
		});
	}

	/*
	 * Resuelve CU 2. Prueba para comprobar que una venta puede darse de alta en la
	 * aplicación.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorías a las que pertenece: prueba funcional dinámica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de selección de datos: prueba con generación de datos de entrada
	 * estática.
	 */
//	@Test
//	public void testFindAllSales() throws ParseException {
//		User client1 = createUser("cliente1â‚¬gmail.com");
//		userDao.save(client1);
//
//		User client2 = createUser("cliente2â‚¬gmail.com");
//		userDao.save(client2);
//
//		User agent1 = createAgent("agente1â‚¬gmail.com");
//		userDao.save(agent1);
//
//		User agent2 = createAgent("agente2â‚¬gmail.com");
//		userDao.save(agent2);
//
//		User gerent = createGerent("gerenteâ‚¬gmail.com");
//		userDao.save(gerent);
//
//		Sale sale1 = new Sale();
//		sale1.setState(SaleState.NORMAL);
//		sale1.setPrice((new BigDecimal(100)));
//		sale1.setCreatedAt(parseDate("2022-01-01"));
//		sale1.setAgent(agent1);
//		sale1.setClient(client1);
//		saleDao.save(sale1);
//
//		Sale sale2 = new Sale();
//		sale2.setState(SaleState.NORMAL);
//		sale2.setPrice((new BigDecimal(100)));
//		sale2.setCreatedAt(parseDate("2022-01-10"));
//		sale2.setAgent(agent1);
//		sale2.setClient(client2);
//		saleDao.save(sale2);
//
//		Sale sale3 = new Sale();
//		sale3.setState(SaleState.NORMAL);
//		sale3.setPrice((new BigDecimal(100)));
//		sale3.setCreatedAt(parseDate("2022-01-10"));
//		sale3.setAgent(agent2);
//		sale3.setClient(client2);
//		saleDao.save(sale3);
//
//		assertAll(() -> {
//			assertEquals(3, saleService.findSales(gerent.getId(), null, null, 0, 3).getNumberOfElements());
//			assertEquals(2, saleService.findSales(agent1.getId(), null, null, 0, 2).getNumberOfElements());
//			assertEquals(1, saleService.findSales(agent2.getId(), null, null, 0, 1).getNumberOfElements());
//			assertEquals(2, saleService.findSales(client2.getId(), null, null, 0, 2).getNumberOfElements());
//			assertEquals(1, saleService.findSales(gerent.getId(), client1.getId(), null, 0, 1).getNumberOfElements());
//			assertEquals(2, saleService.findSales(gerent.getId(), null, agent1.getId(), 0, 2).getNumberOfElements());
//		});
//	}

//	@Test
//	public void testPaySale() throws InstanceNotFoundException, PermissionException, ParseException {
//		User client1 = createUser("cliente1@gmail.com");
//		userDao.save(client1);
//
//		User client2 = createUser("cliente2@gmail.com");
//		userDao.save(client2);
//
//		User agent1 = createAgent("agente1@gmail.com");
//		userDao.save(agent1);
//
//		Sale sale1 = new Sale();
//		sale1.setState(SaleState.FREEZE);
//		sale1.setPrice((new BigDecimal(100)));
//		sale1.setCreatedAt(parseDate("2022-01-01"));
//		sale1.setAgent(agent1);
//		sale1.setClient(client1);
//		saleDao.save(sale1);
//
//		saleService.paySale(client1.getId(), sale1.getId());
//
//		assertAll(() -> {
//			assertEquals(SaleState.PAID, sale1.getState());
//		});
//
//	}
}
