package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	public void testFindAccommodations() {
		Accommodation accommodation = new Accommodation("Hesperia marineda");
		accommodationDao.save(accommodation);

		assertEquals(1, productService.findAccommodations().size());
	}

	@Test
	public void testFindActivities() {
		Activity activity = new Activity("Motos de Agua");
		activityDao.save(activity);

		assertEquals(1, productService.findActivities().size());
	}

	@Test
	public void testFindTransports() {
		Transport t = new Transport("Patineta");
		transportDao.save(t);

		assertEquals(1, productService.findTransports().size());

		Transport t1 = new Transport("Patines");
		transportDao.save(t1);

		assertEquals(2, productService.findTransports().size());
	}

	@Test
	public void testFindTravelss() {
		Travel t = new Travel("Egipto Antiguo");
		travelDao.save(t);

		assertEquals(1, productService.findTravels().size());
	}
}
