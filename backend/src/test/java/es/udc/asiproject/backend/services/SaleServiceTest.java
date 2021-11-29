package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.PackDao;
import es.udc.asiproject.persistence.dao.SaleDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.persistence.model.Sale.SaleState;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.User.RoleType;
import es.udc.asiproject.service.PackService;
import es.udc.asiproject.service.SaleService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.PermissionException;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class SaleServiceTest {

	@Autowired
	PackService packService;
	@Autowired
	SaleService saleService;
	@Autowired
	UserDao userDao;
	@Autowired
	PackDao packDao;
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

	private User createUser(String email) {
		return new User("password", "firstName", "lastName", email, RoleType.USER);
	}

	private User createAgent(String email) {
		return new User("password", "firstName", "lastName", email, RoleType.AGENTE);
	}

	private User createGerent(String email) {
		return new User("password", "firstName", "lastName", email, RoleType.GERENTE);
	}

	private Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

	private Accommodation seedAccommodationDatabase() {
		Accommodation accommodation = new Accommodation("Hesperia marineda", new BigDecimal(1.23));
		accommodationDao.save(accommodation);

		return accommodation;
	}

	private Activity seedActivityDatabase() {
		Activity activity = new Activity("Motos de Agua", new BigDecimal(1.23));
		activityDao.save(activity);

		return activity;
	}

	private Transport seedTransportDatabase() {
		Transport transport = new Transport("Patineta", new BigDecimal(1.23));
		transportDao.save(transport);

		return transport;
	}

	private Travel seedTravelDatabase() {
		Travel travel = new Travel("Egipto Antiguo", new BigDecimal(1.23));
		travelDao.save(travel);

		return travel;
	}

	@SuppressWarnings("serial")
	private Sale createSale() {
		Sale sale = new Sale();
		sale.setPrice(new BigDecimal(1.23));
		sale.setState(Sale.SaleState.NORMAL);
		sale.setAgent(createAgent("manolo@gmail.com"));
		sale.setClient(createUser("cliente@gmail.com"));
		sale.setCreatedAt(new Date());
		sale.setAccommodations(new HashSet<Accommodation>() {
			{
				add(seedAccommodationDatabase());
			}
		});
		sale.setActivities(new HashSet<Activity>() {
			{
				add(seedActivityDatabase());
			}
		});
		sale.setTransports(new HashSet<Transport>() {
			{
				add(seedTransportDatabase());
			}
		});
		sale.setTravels(new HashSet<Travel>() {
			{
				add(seedTravelDatabase());
			}
		});

		return sale;
	}

	@Test
	public void testFindAllSales() throws ParseException {
    /*
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		pack1 = packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setCreatedAt(parseDate("2018-01-01"));
		pack2 = packDao.save(pack2);
    */

		User client1 = createUser("cliente1€gmail.com");
		userDao.save(client1);

		User client2 = createUser("cliente2€gmail.com");
		userDao.save(client2);

		User agent1 = createAgent("agente1€gmail.com");
		userDao.save(agent1);

		User agent2 = createAgent("agente2€gmail.com");
		userDao.save(agent2);

		User gerent = createGerent("gerente€gmail.com");
		userDao.save(gerent);

		Sale sale1 = new Sale();
		sale1.setState(SaleState.NORMAL);
		sale1.setPrice((new BigDecimal(100)));
		sale1.setCreatedAt(parseDate("2022-01-01"));
		sale1.setAgent(agent1);
		sale1.setClient(client1);
		saleDao.save(sale1);

		Sale sale2 = new Sale();
		sale2.setState(SaleState.NORMAL);
		sale2.setPrice((new BigDecimal(100)));
		sale2.setCreatedAt(parseDate("2022-01-10"));
		sale2.setAgent(agent1);
		sale2.setClient(client2);
		saleDao.save(sale2);

		Sale sale3 = new Sale();
		sale3.setState(SaleState.NORMAL);
		sale3.setPrice((new BigDecimal(100)));
		sale3.setCreatedAt(parseDate("2022-01-10"));
		sale3.setAgent(agent2);
		sale3.setClient(client2);
		saleDao.save(sale3);

		assertAll(() -> {
			assertEquals(3, saleService.findSales(gerent.getId(), null, null, 0, 3).getNumberOfElements());
			assertEquals(2, saleService.findSales(agent1.getId(), null, null, 0, 2).getNumberOfElements());
			assertEquals(1, saleService.findSales(agent2.getId(), null, null, 0, 1).getNumberOfElements());
			assertEquals(2, saleService.findSales(client2.getId(), null, null, 0, 2).getNumberOfElements());
			assertEquals(1, saleService.findSales(gerent.getId(), client1.getId(), null, 0, 1).getNumberOfElements());
			assertEquals(2, saleService.findSales(gerent.getId(), null, agent1.getId(), 0, 2).getNumberOfElements());
		});
	}


	@Test
	public void testPaySale() throws InstanceNotFoundException, PermissionException, ParseException {
    /*
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		pack1 = packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setCreatedAt(parseDate("2018-01-01"));
		pack2 = packDao.save(pack2);
    */
		User client1 = createUser("cliente1€gmail.com");
		userDao.save(client1);

		User client2 = createUser("cliente2€gmail.com");
		userDao.save(client2);

		User agent1 = createAgent("agente1€gmail.com");
		userDao.save(agent1);

		Sale sale1 = new Sale();
		sale1.setState(SaleState.NORMAL);
		sale1.setPrice((new BigDecimal(100)));
		sale1.setCreatedAt(parseDate("2022-01-01"));
		sale1.setAgent(agent1);
		sale1.setClient(client1);
		saleDao.save(sale1);

		saleService.paySale(client1.getId(), sale1.getId());

		assertAll(() -> {
			assertEquals(SaleState.PAID, sale1.getState());
		});

	}

	@Test
	public void deleteSale() throws InstanceNotFoundException, PermissionException, ParseException {
		/*
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		pack1 = packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setCreatedAt(parseDate("2018-01-01"));
		pack2 = packDao.save(pack2);
		*/
		User client1 = createUser("cliente1@gmail.com");
		userDao.save(client1);

		User client2 = createUser("cliente2@gmail.com");
		userDao.save(client2);

		User agent1 = createAgent("agente1@gmail.com");
		userDao.save(agent1);

		Sale sale1 = new Sale();
		sale1.setState(SaleState.NORMAL);
		sale1.setPrice((new BigDecimal(100)));
		sale1.setCreatedAt(parseDate("2022-01-01"));
		sale1.setAgent(agent1);
		sale1.setClient(client1);
		saleDao.save(sale1);

		saleService.deleteSale(agent1.getId(), sale1);

		assertAll(() -> {
			assertEquals(0, saleDao.findAll().size());
		});

	}

	@Test
	public void testCreateSale() throws InstanceNotFoundException, InvalidOperationException {
		Sale inputSale = createSale();
		Sale outputSale = saleService.createSale(inputSale);

		assertAll(() -> {
			assertEquals(inputSale.getState(), outputSale.getState());
			assertEquals(inputSale.getAgent(), outputSale.getAgent());
			assertEquals(inputSale.getClient(), outputSale.getClient());
			assertEquals(inputSale.getPrice(), outputSale.getPrice());
			assertEquals(inputSale.getCreatedAt(), outputSale.getCreatedAt());
			assertEquals(inputSale.getAccommodations(), outputSale.getAccommodations());
			assertEquals(inputSale.getActivities(), outputSale.getActivities());
			assertEquals(inputSale.getTransports(), outputSale.getTransports());
			assertEquals(inputSale.getTravels(), outputSale.getTravels());
		});
	}


	@SuppressWarnings("serial")
	@Test
	public void testUpdateSale() throws InstanceNotFoundException, InvalidOperationException {
		Sale inputSale = saleService.createSale(createSale());
		inputSale.setPrice(new BigDecimal(3.21));
		inputSale.setState(Sale.SaleState.NORMAL);
		inputSale.setAgent(createAgent("manolo1@gmail.com"));
		inputSale.setClient(createUser("cliente1@gmail.com"));
		inputSale.setCreatedAt(new Date());
		inputSale.setAccommodations(new HashSet<Accommodation>() {
			{
				add(seedAccommodationDatabase());
			}
		});
		inputSale.setActivities(new HashSet<Activity>() {
			{
				add(seedActivityDatabase());
				add(seedActivityDatabase());
			}
		});
		inputSale.setTransports(new HashSet<Transport>() {
			{
				add(seedTransportDatabase());
				add(seedTransportDatabase());
				add(seedTransportDatabase());
			}
		});
		inputSale.setTravels(new HashSet<Travel>() {
			{
				add(seedTravelDatabase());
				add(seedTravelDatabase());
				add(seedTravelDatabase());
				add(seedTravelDatabase());
			}
		});

		Sale outputSale = saleService.updateSale(inputSale);

		assertAll(() -> {
			assertEquals(inputSale.getState(), outputSale.getState());
			assertEquals(inputSale.getAgent(), outputSale.getAgent());
			assertEquals(inputSale.getClient(), outputSale.getClient());
			assertEquals(inputSale.getPrice(), outputSale.getPrice());
			assertEquals(inputSale.getCreatedAt(), outputSale.getCreatedAt());
			assertEquals(inputSale.getAccommodations(), outputSale.getAccommodations());
			assertEquals(inputSale.getActivities(), outputSale.getActivities());
			assertEquals(inputSale.getTransports(), outputSale.getTransports());
			assertEquals(inputSale.getTravels(), outputSale.getTravels());
		});
	}

}
