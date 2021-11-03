package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.PackService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class PackServiceTest {
	@Autowired
	PackService packService;
	@Autowired
	AccommodationDao accommodationDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	TransportDao transportDao;
	@Autowired
	TravelDao travelDao;

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
	private Pack createPack() {
		Pack pack = new Pack();
		pack.setTitle("title");
		pack.setDescription("description");
		pack.setImage(new Byte[] { 1, 2, 3 });
		pack.setPrice(new BigDecimal(1.23));
		pack.setDuration((short) 5);
		pack.setPersons("persons");
		pack.setCreatedAt(new Date());
		pack.setAccommodations(new HashSet<Accommodation>() {
			{
				add(seedAccommodationDatabase());
			}
		});
		pack.setActivities(new HashSet<Activity>() {
			{
				add(seedActivityDatabase());
			}
		});
		pack.setTransports(new HashSet<Transport>() {
			{
				add(seedTransportDatabase());
			}
		});
		pack.setTravels(new HashSet<Travel>() {
			{
				add(seedTravelDatabase());
			}
		});

		return pack;
	}

	@Test
	public void shouldCreatePack() throws InstanceNotFoundException, InvalidOperationException {
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
	public void shouldReturnPage() throws InstanceNotFoundException, InvalidOperationException {
		packService.createPack(createPack());
		packService.createPack(createPack());
		packService.createPack(createPack());

		Page<Pack> page = packService.findPacks(1, 1);

		assertAll(() -> {
			assertEquals(3, page.getTotalPages());
			assertEquals(1, page.getNumberOfElements());
			assertTrue(page.hasNext());
			assertTrue(page.hasPrevious());
		});
	}

	@Test
	public void shouldReturnEmptyPage() {
		Page<Pack> page = packService.findPacks(0, 1);

		assertAll(() -> {
			assertEquals(0, page.getTotalPages());
			assertEquals(0, page.getNumberOfElements());
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}
}
