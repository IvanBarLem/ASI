package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import es.udc.asiproject.service.exceptions.PermissionException;

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

    private User seedGerenteDatabase(String email) {
	User user = User.builder().email(email).password("password").firstName("firstName").lastName("lastName")
		.role(RoleType.GERENTE).build();
	userDao.save(user);

	return user;
    }

    private User seedAgentDatabase(String email) {
	User user = User.builder().email(email).password("password").firstName("firstName").lastName("lastName")
		.role(RoleType.AGENTE).build();
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
	User client = seedClientDatabase("test_user@gmail.com");

	List<Accommodation> accommodations = new ArrayList<Accommodation>();
	for (Long accommodationId = 0L; accommodationId < 4; accommodationId++) {
	    Accommodation accommodation = seedAccommodationDatabase();
	    accommodations.add(accommodation);
	}

	List<Activity> activities = new ArrayList<Activity>();
	for (Long activityId = 0L; activityId < 5; activityId++) {
	    Activity activity = seedActivityDatabase();
	    activityDao.save(activity);
	    activities.add(activity);
	}

	List<Transport> transports = new ArrayList<Transport>();
	for (Long transportId = 0L; transportId < 10; transportId++) {
	    Transport transport = seedTransportDatabase();
	    transports.add(transport);
	}

	List<Travel> travels = new ArrayList<Travel>();
	for (Long travelId = 0L; travelId < 2; travelId++) {
	    Travel travel = seedTravelDatabase();
	    travels.add(travel);
	}

	for (Long agentId = 0L; agentId < 5; agentId++) {
	    User agent = User.builder().email("test_agente" + agentId + "@gmail.com").password("pass")
		    .firstName("Agente" + agentId).lastName("Pita").role(RoleType.AGENTE).build();
	    userDao.save(agent);

	    for (Long saleId = 0L; saleId < 5; saleId++) {
		Sale sale;
		if (saleId < 3) {
		    sale = Sale.builder().state(SaleState.NORMAL).price(new BigDecimal(10)).agent(agent).client(client)
			    .createdAt(parseDate("202" + saleId + "-01-01")).build();
		} else if (saleId < 4) {
		    sale = Sale.builder().state(SaleState.FREEZE).price(new BigDecimal(10)).agent(agent).client(client)
			    .createdAt(parseDate("202" + saleId + "-01-01")).build();
		} else {
		    sale = Sale.builder().state(SaleState.PAID).price(new BigDecimal(10)).agent(agent).client(client)
			    .createdAt(parseDate("202" + saleId + "-01-01")).build();
		}
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
     * aplicaci�n.
     * 
     * Nivel de prueba: unidad.
     * 
     * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
     * positiva.
     * 
     * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
     * est�tica.
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
     * Resuelve CU 2.5. Prueba para comprobar que el listado de ventas puede ser
     * consultado por un cliente.
     * 
     * Nivel de prueba: unidad.
     * 
     * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
     * positiva.
     * 
     * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
     * est�tica.
     */
    @Test
    public void should_return_list_with_sales_for_client() throws ParseException, InstanceNotFoundException {
	seedSaleDatabase();
	User agent = seedAgentDatabase("agent@gmail.com");
	User client = seedClientDatabase("client@gmail.com");
	saleDao.save(Sale.builder().state(SaleState.NORMAL).price(new BigDecimal(10)).agent(agent).client(client)
		.createdAt(parseDate("2019-01-01")).build());
	saleDao.save(Sale.builder().state(SaleState.FREEZE).price(new BigDecimal(10)).agent(agent).client(client)
		.createdAt(parseDate("2020-01-01")).build());
	saleDao.save(Sale.builder().state(SaleState.PAID).price(new BigDecimal(10)).agent(agent).client(client)
		.createdAt(parseDate("2021-01-01")).build());

	Page<Sale> page = saleService.findSales(client.getId(), "", "firstName", 0, 10);

	assertAll(() -> {
	    assertEquals(1, page.getTotalPages());
	    assertEquals(2, page.getNumberOfElements());
	    assertFalse(page.hasNext());
	    assertFalse(page.hasPrevious());
	});
    }

    /*
     * Resuelve CU 2.5. Prueba para comprobar que el listado de ventas puede ser
     * consultado por un gerente.
     * 
     * Nivel de prueba: unidad.
     * 
     * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
     * positiva.
     * 
     * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
     * est�tica.
     */
    @Test
    public void should_return_list_with_sales_for_gerente() throws ParseException, InstanceNotFoundException {
	User gerente = seedGerenteDatabase("gerente@gmail.com");
	seedSaleDatabase();

	Page<Sale> page = saleService.findSales(gerente.getId(), "Agente1", "firstName", 0, 10);

	assertAll(() -> {
	    assertEquals(1, page.getTotalPages());
	    assertEquals(5, page.getNumberOfElements());
	    assertEquals(5, page.getTotalElements());
	    assertFalse(page.hasNext());
	    assertFalse(page.hasPrevious());
	});
    }

    /*
     * Resuelve CU 2.5. Prueba para comprobar que el listado de ventas puede ser
     * consultado por un agente.
     * 
     * Nivel de prueba: unidad.
     * 
     * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
     * positiva.
     * 
     * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
     * est�tica.
     */
    @Test
    public void should_return_list_with_sales_for_agent() throws ParseException, InstanceNotFoundException {
	seedSaleDatabase();
	User agent = seedAgentDatabase("agent@gmail.com");
	User client = seedClientDatabase("client@gmail.com");
	saleDao.save(Sale.builder().state(SaleState.NORMAL).price(new BigDecimal(10)).agent(agent).client(client)
		.createdAt(parseDate("2021-01-01")).build());
	saleDao.save(Sale.builder().state(SaleState.FREEZE).price(new BigDecimal(10)).agent(agent).client(client)
		.createdAt(parseDate("2021-01-01")).build());
	saleDao.save(Sale.builder().state(SaleState.PAID).price(new BigDecimal(10)).agent(agent).client(client)
		.createdAt(parseDate("2021-01-01")).build());

	Page<Sale> page = saleService.findSales(agent.getId(), "Agente1", "", 0, 10);

	assertAll(() -> {
	    assertEquals(1, page.getTotalPages());
	    assertEquals(3, page.getNumberOfElements());
	    assertFalse(page.hasNext());
	    assertFalse(page.hasPrevious());
	});
    }

    /*
     * Resuelve CU 2.4. Prueba para comprobar que una venta puede marcarse como
     * congelada.
     * 
     * Nivel de prueba: unidad.
     * 
     * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
     * positiva.
     * 
     * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
     * est�tica.
     */
    @Test
    public void should_change_sale_state_to_freeze()
	    throws InstanceNotFoundException, PermissionException, ParseException {
	User agent = seedAgentDatabase("agent@gmail.com");
	User client = seedClientDatabase("client@gmail.com");
	Sale sale = Sale.builder().state(SaleState.NORMAL).price(new BigDecimal(10)).createdAt(new Date()).agent(agent)
		.client(client).build();
	saleDao.save(sale);

	saleService.freezeSale(agent.getId(), sale.getId());

	assertEquals(SaleState.FREEZE, sale.getState());
    }

    /*
     * Resuelve CU 2.4. Prueba para comprobar que una venta puede marcarse como
     * pagada.
     * 
     * Nivel de prueba: unidad.
     * 
     * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
     * positiva.
     * 
     * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
     * est�tica.
     */
    @Test
    public void should_change_sale_state_to_paid()
	    throws InstanceNotFoundException, PermissionException, ParseException {
	User agent = seedAgentDatabase("agent@gmail.com");
	User client = seedClientDatabase("client@gmail.com");
	Sale sale = Sale.builder().state(SaleState.NORMAL).price(new BigDecimal(10)).createdAt(new Date()).agent(agent)
		.client(client).build();
	saleDao.save(sale);

	saleService.paySale(client.getId(), sale.getId());

	assertEquals(SaleState.PAID, sale.getState());
    }
}
