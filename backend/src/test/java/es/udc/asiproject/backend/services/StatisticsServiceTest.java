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
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

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
import es.udc.asiproject.persistence.model.unrelated.SalesAgent;
import es.udc.asiproject.persistence.model.unrelated.SalesCompany;
import es.udc.asiproject.persistence.model.unrelated.SalesProduct;
import es.udc.asiproject.service.StatisticsService;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class StatisticsServiceTest {
	@Autowired
	StatisticsService statisticsService;
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

	private void seedDatabase() throws ParseException {
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

	/**
	 * Resuelve CU 5. Prueba para comprobar el cálculo de las ventas totales de la
	 * compañía y su facturación.
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
	public void should_return_sales_company() throws ParseException {
		seedDatabase();

		SalesCompany sales = statisticsService.findSalesCompany(parseDate("2020-01-01"), parseDate("2024-01-01"));

		assertAll(() -> {
			assertEquals(25, sales.getCurrentSales());
			assertEquals(new BigDecimal(2000).stripTrailingZeros(), sales.getCurrentBilling().stripTrailingZeros());
			assertEquals(5, sales.getPreviousSales());
			assertEquals(new BigDecimal(400).stripTrailingZeros(), sales.getPreviousBilling().stripTrailingZeros());
			assertEquals(2100, sales.getNumberOfProductsSold());
		});
	}

	/**
	 * Resuelve CU 5. Prueba para comprobar el cálculo de las ventas de la compañía
	 * por cada agente y su facturación.
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
	public void should_return_sales_agents() throws ParseException {
		seedDatabase();

		Page<SalesAgent> sales = statisticsService.findSalesAgents("", parseDate("2020-01-01"), parseDate("2024-01-01"),
				0, 10);

		assertAll(() -> {
			assertEquals("Agente0", sales.getContent().get(0).getFirstName());
			assertEquals("Pita", sales.getContent().get(0).getLastName());
			assertEquals(5, sales.getContent().get(0).getCurrentSales());
			assertEquals(new BigDecimal(400).stripTrailingZeros(),
					sales.getContent().get(0).getCurrentBilling().stripTrailingZeros());
			assertEquals(1, sales.getContent().get(0).getPreviousSales());
			assertEquals(new BigDecimal(80).stripTrailingZeros(),
					sales.getContent().get(0).getPreviousBilling().stripTrailingZeros());
		});
	}

	/**
	 * Resuelve CU 5. Prueba para comprobar el cálculo de las ventas de la compañía
	 * por cada agente cuyo nombre contenga una cadena de caracteres dada y su
	 * facturación.
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
	public void should_return_sales_agents_by_name() throws ParseException {
		seedDatabase();

		Page<SalesAgent> sales = statisticsService.findSalesAgents("1", parseDate("2020-01-01"),
				parseDate("2024-01-01"), 0, 10);

		assertAll(() -> {
			assertEquals("Agente1", sales.getContent().get(0).getFirstName());
			assertEquals("Pita", sales.getContent().get(0).getLastName());
			assertEquals(5, sales.getContent().get(0).getCurrentSales());
			assertEquals(new BigDecimal(400).stripTrailingZeros(),
					sales.getContent().get(0).getCurrentBilling().stripTrailingZeros());
			assertEquals(1, sales.getContent().get(0).getPreviousSales());
			assertEquals(new BigDecimal(80).stripTrailingZeros(),
					sales.getContent().get(0).getPreviousBilling().stripTrailingZeros());
		});
	}

	/**
	 * Resuelve CU 5.1. Prueba para comprobar el cálculo de las ventas de la
	 * compañía por cada producto y su facturación.
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
	public void should_return_sales_products() throws ParseException {
		seedDatabase();

		Page<SalesProduct> sales = statisticsService.findSalesProducts("", "", parseDate("2020-01-01"),
				parseDate("2024-01-01"), 0, 10);

		assertAll(() -> {
			assertEquals(10, sales.getContent().size());
			assertEquals(100, sales.getContent().get(0).getCurrentSales());
			assertEquals(new BigDecimal(125).stripTrailingZeros(),
					sales.getContent().get(0).getCurrentBilling().stripTrailingZeros());
			assertEquals(20, sales.getContent().get(0).getPreviousSales());
			assertEquals(new BigDecimal(25).stripTrailingZeros(),
					sales.getContent().get(0).getPreviousBilling().stripTrailingZeros());
		});
	}

	/**
	 * Resuelve CU 5.1. Prueba para comprobar el cálculo de las ventas de la
	 * compañía por cada producto perteneciente a una categoría y su facturación.
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
	public void testFindSalesProductsByCategory() throws ParseException {
		seedDatabase();

		Page<SalesProduct> sales = statisticsService.findSalesProducts("accommodation", "", parseDate("2020-01-01"),
				parseDate("2024-01-01"), 0, 10);

		assertAll(() -> {
			assertEquals(4, sales.getContent().size());
			assertEquals("accommodation0", sales.getContent().get(0).getName());
			assertEquals("A Coruna", sales.getContent().get(0).getLocation());
			assertEquals(100, sales.getContent().get(0).getCurrentSales());
			assertEquals(new BigDecimal(125).stripTrailingZeros(),
					sales.getContent().get(0).getCurrentBilling().stripTrailingZeros());
			assertEquals(20, sales.getContent().get(0).getPreviousSales());
			assertEquals(new BigDecimal(25).stripTrailingZeros(),
					sales.getContent().get(0).getPreviousBilling().stripTrailingZeros());
		});
	}

	/**
	 * Resuelve CU 5.1. Prueba para comprobar el cálculo de las ventas de la
	 * compañía por cada producto que se encuentre en una localización concreta y su
	 * facturación.
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
	public void testFindSalesProductsByLocation() throws ParseException {
		seedDatabase();

		Page<SalesProduct> sales = statisticsService.findSalesProducts("", "A Coruna", parseDate("2020-01-01"),
				parseDate("2024-01-01"), 0, 10);

		assertAll(() -> {
			assertEquals(4, sales.getContent().size());
			assertEquals("accommodation0", sales.getContent().get(0).getName());
			assertEquals("A Coruna", sales.getContent().get(0).getLocation());
			assertEquals(100, sales.getContent().get(0).getCurrentSales());
			assertEquals(new BigDecimal(125).stripTrailingZeros(),
					sales.getContent().get(0).getCurrentBilling().stripTrailingZeros());
			assertEquals(20, sales.getContent().get(0).getPreviousSales());
			assertEquals(new BigDecimal(25).stripTrailingZeros(),
					sales.getContent().get(0).getPreviousBilling().stripTrailingZeros());
		});
	}
}
