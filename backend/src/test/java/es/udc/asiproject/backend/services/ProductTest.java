package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.ProductService;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class ProductTest {
	@Autowired
	ProductService productService;
	@Autowired
	AccommodationDao accommodationDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	TransportDao transportDao;
	@Autowired
	TravelDao travelDao;

	@Test
	public void testCreateAccommodation() {
		Accommodation accommodation = productService.createAccommodation(new Accommodation("Hesperia marineda"));
		List<Accommodation> accommodations = productService.findAccommodations();

		assertEquals(accommodation, accommodations.get(0));
	}

	@Test
	public void testFindAccommodations() {
		Accommodation accommodation = new Accommodation("Hesperia marineda");
		accommodationDao.save(accommodation);

		assertEquals(1, productService.findAccommodations().size());
	}

	@Test
	public void testCreateActivity() {
		Activity activity = productService.createActivity(new Activity("Motos de Agua"));
		List<Activity> activities = productService.findActivities();

		assertEquals(activity, activities.get(0));
	}

	@Test
	public void testFindActivities() {
		Activity activity = new Activity("Motos de Agua");
		activityDao.save(activity);

		assertEquals(1, productService.findActivities().size());
	}

	@Test
	public void testCreateTransport() {
		Transport transport = productService.createTransport(new Transport("Patineta"));
		List<Transport> transports = productService.findTransports();

		assertEquals(transport, transports.get(0));
	}

	@Test
	public void testFindTransports() {
		Transport transport = new Transport("Patineta");
		transportDao.save(transport);

		assertEquals(1, productService.findTransports().size());

		transport = new Transport("Patines");
		transportDao.save(transport);

		assertEquals(2, productService.findTransports().size());
	}

	@Test
	public void testCreateTravel() {
		Travel travel = productService.createTravel(new Travel("Egipto Antiguo"));
		List<Travel> travels = productService.findTravels();

		assertEquals(travel, travels.get(0));
	}

	@Test
	public void testFindTravelss() {
		Travel travel = new Travel("Egipto Antiguo");
		travelDao.save(travel);

		assertEquals(1, productService.findTravels().size());
	}
}
