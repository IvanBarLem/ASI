package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

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
		List<Accommodation> accommodations = productService.findAllAccommodations();

		assertEquals(accommodation, accommodations.get(0));
	}

	@Test
	public void testFindAccommodations() throws InstanceNotFoundException {
		Accommodation accommodation = productService.createAccommodation(new Accommodation("Hesperia marineda"));
		accommodation.setHidden(true);
		productService.updateAccommodation(accommodation);

		assertEquals(0, productService.findAccommodations().size());
	}

	@Test
	public void testFindAllAccommodations() {
		Accommodation accommodation = new Accommodation("Hesperia marineda");
		accommodationDao.save(accommodation);

		assertEquals(1, productService.findAllAccommodations().size());
	}

	@Test
	public void testUpdateAccommodationWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class,
				() -> productService.updateAccommodation(new Accommodation(-1L, "Hesperia marineda")));
	}

	@Test
	public void testUpdateAccommodation() throws InstanceNotFoundException {
		Accommodation accommodation = productService.createAccommodation(new Accommodation("Hesperia marineda"));
		accommodation.setName(accommodation.getName() + "X");
		productService.updateAccommodation(accommodation);
		List<Accommodation> accommodations = productService.findAllAccommodations();

		assertEquals(accommodation, accommodations.get(0));
	}

	@Test
	public void testRemoveAccommodationWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeAccommodation(-1L));
	}

	@Test
	public void testRemoveAccommodation() throws InstanceNotFoundException {
		Accommodation accommodation = productService.createAccommodation(new Accommodation("Hesperia marineda"));
		productService.removeAccommodation(accommodation.getId());
		List<Accommodation> accommodations = productService.findAllAccommodations();

		assertEquals(0, accommodations.size());
	}

	@Test
	public void testCreateActivity() {
		Activity activity = productService.createActivity(new Activity("Motos de Agua"));
		List<Activity> activities = productService.findAllActivities();

		assertEquals(activity, activities.get(0));
	}

	@Test
	public void testFindActivities() throws InstanceNotFoundException {
		Activity activity = productService.createActivity(new Activity("Motos de Agua"));
		activity.setHidden(true);
		productService.updateActivity(activity);

		assertEquals(0, productService.findActivities().size());
	}

	@Test
	public void testFindAllActivities() {
		Activity activity = new Activity("Motos de Agua");
		activityDao.save(activity);

		assertEquals(1, productService.findAllActivities().size());
	}

	@Test
	public void testUpdateActivityWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class,
				() -> productService.updateActivity(new Activity(-1L, "Motos de Agua")));
	}

	@Test
	public void testUpdateActivity() throws InstanceNotFoundException {
		Activity activity = productService.createActivity(new Activity("Motos de Agua"));
		activity.setName(activity.getName() + "X");
		productService.updateActivity(activity);
		List<Activity> activities = productService.findAllActivities();

		assertEquals(activity, activities.get(0));
	}

	@Test
	public void testRemoveActivityWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeActivity(-1L));
	}

	@Test
	public void testRemoveActivity() throws InstanceNotFoundException {
		Activity activity = productService.createActivity(new Activity("Motos de Agua"));
		productService.removeActivity(activity.getId());
		List<Activity> activities = productService.findAllActivities();

		assertEquals(0, activities.size());
	}

	@Test
	public void testCreateTransport() {
		Transport transport = productService.createTransport(new Transport("Patineta"));
		List<Transport> transports = productService.findAllTransports();

		assertEquals(transport, transports.get(0));
	}

	@Test
	public void testFindTransports() throws InstanceNotFoundException {
		Transport transport = productService.createTransport(new Transport("Patineta"));
		transport.setHidden(true);
		productService.updateTransport(transport);

		assertEquals(0, productService.findTransports().size());
	}

	@Test
	public void testFindAllTransports() {
		Transport transport = new Transport("Patineta");
		transportDao.save(transport);

		assertEquals(1, productService.findAllTransports().size());

		transport = new Transport("Patines");
		transportDao.save(transport);

		assertEquals(2, productService.findAllTransports().size());
	}

	@Test
	public void testUpdateTransportWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class,
				() -> productService.updateTransport(new Transport(-1L, "Patineta")));
	}

	@Test
	public void testUpdateTransport() throws InstanceNotFoundException {
		Transport transport = productService.createTransport(new Transport("Patineta"));
		transport.setName(transport.getName() + "X");
		productService.updateTransport(transport);
		List<Transport> transports = productService.findAllTransports();

		assertEquals(transport, transports.get(0));
	}

	@Test
	public void testRemoveTransportWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeTransport(-1L));
	}

	@Test
	public void testRemoveTransport() throws InstanceNotFoundException {
		Transport transport = productService.createTransport(new Transport("Patineta"));
		productService.removeTransport(transport.getId());
		List<Transport> transports = productService.findAllTransports();

		assertEquals(0, transports.size());
	}

	@Test
	public void testCreateTravel() {
		Travel travel = productService.createTravel(new Travel("Egipto Antiguo"));
		List<Travel> travels = productService.findAllTravels();

		assertEquals(travel, travels.get(0));
	}

	@Test
	public void testFindTravels() throws InstanceNotFoundException {
		Travel travel = productService.createTravel(new Travel("Egipto Antiguo"));
		travel.setHidden(true);
		productService.updateTravel(travel);

		assertEquals(0, productService.findTravels().size());
	}

	@Test
	public void testFindAllTravels() {
		Travel travel = new Travel("Egipto Antiguo");
		travelDao.save(travel);

		assertEquals(1, productService.findAllTravels().size());
	}

	@Test
	public void testUpdateTravelWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class,
				() -> productService.updateTravel(new Travel(-1L, "Egipto Antiguo")));
	}

	@Test
	public void testUpdateTravel() throws InstanceNotFoundException {
		Travel travel = productService.createTravel(new Travel("Egipto Antiguo"));
		travel.setName(travel.getName() + "X");
		productService.updateTravel(travel);
		List<Travel> travels = productService.findAllTravels();

		assertEquals(travel, travels.get(0));
	}

	@Test
	public void testRemoveTravelWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeTravel(-1L));
	}

	@Test
	public void testRemoveTravel() throws InstanceNotFoundException {
		Travel travel = productService.createTravel(new Travel("Egipto Antiguo"));
		productService.removeTravel(travel.getId());
		List<Travel> travels = productService.findAllTravels();

		assertEquals(0, travels.size());
	}
}
