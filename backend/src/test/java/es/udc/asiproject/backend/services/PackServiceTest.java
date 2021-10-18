package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.persistence.dao.AccommodationDao;
import es.udc.asiproject.backend.persistence.dao.ActivityDao;
import es.udc.asiproject.backend.persistence.dao.TransportDao;
import es.udc.asiproject.backend.persistence.dao.TravelDao;
import es.udc.asiproject.backend.persistence.model.Accommodation;
import es.udc.asiproject.backend.persistence.model.Activity;
import es.udc.asiproject.backend.persistence.model.Pack;
import es.udc.asiproject.backend.persistence.model.Transport;
import es.udc.asiproject.backend.persistence.model.Travel;
import es.udc.asiproject.backend.service.PackService;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PackServiceTest {
	@Autowired
	AccommodationDao accommodationDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	TransportDao transportDao;
	@Autowired
	TravelDao travelDao;
	@Autowired
	PackService packService;

	private Accommodation seedAccommodationDatabase() {
		Accommodation accommodation = new Accommodation("Hesperia marineda");
		accommodationDao.save(accommodation);

		return accommodation;
	}

	private Activity seedActivityDatabase() {
		Activity activity = new Activity("Motos de Agua");
		activityDao.save(activity);

		return activity;
	}

	private Transport seedTransportDatabase() {
		Transport transport = new Transport("Patineta");
		transportDao.save(transport);

		return transport;
	}

	private Travel seedTravelDatabase() {
		Travel travel = new Travel("Egipto Antiguo");
		travelDao.save(travel);

		return travel;
	}

	private Pack createPack() {
		Pack pack = new Pack();
		pack.setTitle("title");
		pack.setDescription("description");
		pack.setImage(new Byte[] { 1, 2, 3 });
		pack.setPrice(new BigDecimal(1.23));
		pack.setDuration((short) 5);
		pack.setPersons("persons");
		pack.setCreatedAt(new Date());
		pack.setAccommodations(Set.of(seedAccommodationDatabase()));
		pack.setActivities(Set.of(seedActivityDatabase()));
		pack.setTransports(Set.of(seedTransportDatabase()));
		pack.setTravels(Set.of(seedTravelDatabase()));

		return pack;
	}

	@Test
	public void shouldCreatePack() throws InstanceNotFoundException {
		Pack inputPack = createPack();
		Pack outputPack = packService.createPack(inputPack);

		assertAll(() -> {
			assertEquals(inputPack.getTitle(), outputPack.getTitle());
			assertEquals(inputPack.getDescription(), outputPack.getDescription());
			assertEquals(inputPack.getImage(), outputPack.getImage());
			assertEquals(inputPack.getPrice(), outputPack.getPrice());
			assertEquals(inputPack.getDuration(), outputPack.getDuration());
			assertEquals(inputPack.getPersons(), outputPack.getPersons());
			assertEquals(inputPack.getCreatedAt(), outputPack.getCreatedAt());
			assertEquals(inputPack.getAccommodations(), outputPack.getAccommodations());
			assertEquals(inputPack.getActivities(), outputPack.getActivities());
			assertEquals(inputPack.getTransports(), outputPack.getTransports());
			assertEquals(inputPack.getTravels(), outputPack.getTravels());
		});
	}

	@Test
	public void shouldReturnPage() throws InstanceNotFoundException {
		packService.createPack(createPack());
		packService.createPack(createPack());
		packService.createPack(createPack());

		Page<Pack> page = packService.getPacks(1, 1);

		assertAll(() -> {
			assertEquals(3, page.getTotalPages());
			assertEquals(1, page.getNumberOfElements());
			assertTrue(page.hasNext());
			assertTrue(page.hasPrevious());
		});
	}

	@Test
	public void shouldReturnEmptyPage() {
		Page<Pack> page = packService.getPacks(0, 1);

		assertAll(() -> {
			assertEquals(0, page.getTotalPages());
			assertEquals(0, page.getNumberOfElements());
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}
}
