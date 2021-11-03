package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import es.udc.asiproject.persistence.dao.PackDao;
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
	PackDao packDao;
	@Autowired
	AccommodationDao accommodationDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	TransportDao transportDao;
	@Autowired
	TravelDao travelDao;

	private Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

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

	@SuppressWarnings("serial")
	private Pack createPack() {
		Pack pack = new Pack();
		pack.setTitle("title");
		pack.setDescription("description");
		pack.setImage(new Byte[] { 1, 2, 3 });
		pack.setPrice(new BigDecimal(1.23));
		pack.setDuration((short) 5);
		pack.setPersons("persons");
		pack.setOutstanding(false);
		pack.setHidden(false);
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
	public void testCreatePack() throws InstanceNotFoundException, InvalidOperationException {
		Pack inputPack = createPack();
		Pack outputPack = packService.createPack(inputPack);

		assertAll(() -> {
			assertEquals(inputPack.getTitle(), outputPack.getTitle());
			assertEquals(inputPack.getDescription(), outputPack.getDescription());
			assertEquals(inputPack.getImage(), outputPack.getImage());
			assertEquals(inputPack.getPrice(), outputPack.getPrice());
			assertEquals(inputPack.getDuration(), outputPack.getDuration());
			assertEquals(inputPack.getPersons(), outputPack.getPersons());
			assertEquals(false, outputPack.getOutstanding());
			assertEquals(false, outputPack.getHidden());
			assertEquals(inputPack.getCreatedAt(), outputPack.getCreatedAt());
			assertEquals(inputPack.getAccommodations(), outputPack.getAccommodations());
			assertEquals(inputPack.getActivities(), outputPack.getActivities());
			assertEquals(inputPack.getTransports(), outputPack.getTransports());
			assertEquals(inputPack.getTravels(), outputPack.getTravels());
		});
	}

	@Test
	public void testFindPacks() throws InstanceNotFoundException, InvalidOperationException, ParseException {
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setCreatedAt(parseDate("2014-01-01"));
		packDao.save(pack2);
		Pack pack3 = createPack();
		pack3.setHidden(true);
		pack3.setCreatedAt(parseDate("2018-01-01"));
		packDao.save(pack3);

		Page<Pack> page = packService.findPacks(0, 2);

		assertAll(() -> {
			assertEquals(1, page.getTotalPages());
			assertEquals(2, page.getNumberOfElements());
			assertEquals(pack1, page.getContent().get(0));
			assertEquals(pack2, page.getContent().get(1));
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	@Test
	public void testFindOutstandingPacks() throws InstanceNotFoundException, InvalidOperationException, ParseException {
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setOutstanding(true);
		pack2.setCreatedAt(parseDate("2014-01-01"));
		packDao.save(pack2);
		Pack pack3 = createPack();
		pack3.setHidden(true);
		pack3.setCreatedAt(parseDate("2018-01-01"));
		packDao.save(pack3);

		Page<Pack> page = packService.findPacks(0, 2);

		assertAll(() -> {
			assertEquals(1, page.getTotalPages());
			assertEquals(2, page.getNumberOfElements());
			assertEquals(pack2, page.getContent().get(0));
			assertEquals(pack1, page.getContent().get(1));
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	@Test
	public void testFindPacksEmpty() {
		Page<Pack> page = packService.findPacks(0, 1);

		assertAll(() -> {
			assertEquals(0, page.getTotalPages());
			assertEquals(0, page.getNumberOfElements());
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	@Test
	public void testFindAllPacks() throws InstanceNotFoundException, InvalidOperationException, ParseException {
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setCreatedAt(parseDate("2018-01-01"));
		packDao.save(pack2);
		Pack pack3 = createPack();
		pack3.setHidden(true);
		pack3.setCreatedAt(parseDate("2014-01-01"));
		packDao.save(pack3);

		Page<Pack> page = packService.findAllPacks(1, 1);

		assertAll(() -> {
			assertEquals(3, page.getTotalPages());
			assertEquals(1, page.getNumberOfElements());
			assertEquals(pack2, page.getContent().get(0));
			assertTrue(page.hasNext());
			assertTrue(page.hasPrevious());
		});
	}

	@Test
	public void testFindAllOutstandingPacks()
			throws InstanceNotFoundException, InvalidOperationException, ParseException {
		Pack pack1 = createPack();
		pack1.setCreatedAt(parseDate("2021-01-01"));
		packDao.save(pack1);
		Pack pack2 = createPack();
		pack2.setOutstanding(true);
		pack2.setCreatedAt(parseDate("2014-01-01"));
		packDao.save(pack2);
		Pack pack3 = createPack();
		pack3.setHidden(true);
		pack3.setCreatedAt(parseDate("2018-01-01"));
		packDao.save(pack3);

		Page<Pack> page = packService.findAllPacks(0, 3);

		assertAll(() -> {
			assertEquals(1, page.getTotalPages());
			assertEquals(3, page.getNumberOfElements());
			assertEquals(pack2, page.getContent().get(0));
			assertEquals(pack1, page.getContent().get(1));
			assertEquals(pack3, page.getContent().get(2));
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	@Test
	public void testFindAllPacksEmpty() {
		Page<Pack> page = packService.findAllPacks(0, 1);

		assertAll(() -> {
			assertEquals(0, page.getTotalPages());
			assertEquals(0, page.getNumberOfElements());
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	@SuppressWarnings("serial")
	@Test
	public void testUpdatePack() throws InstanceNotFoundException, InvalidOperationException {
		Pack inputPack = packService.createPack(createPack());
		inputPack.setTitle(inputPack.getTitle() + "X");
		inputPack.setDescription(inputPack.getDescription() + "X");
		inputPack.setImage(new Byte[] { 3, 2, 1 });
		inputPack.setPrice(new BigDecimal(3.21));
		inputPack.setDuration((short) 8);
		inputPack.setOutstanding(true);
		inputPack.setHidden(true);
		inputPack.setAccommodations(new HashSet<Accommodation>() {
			{
				add(seedAccommodationDatabase());
			}
		});
		inputPack.setActivities(new HashSet<Activity>() {
			{
				add(seedActivityDatabase());
				add(seedActivityDatabase());
			}
		});
		inputPack.setTransports(new HashSet<Transport>() {
			{
				add(seedTransportDatabase());
				add(seedTransportDatabase());
				add(seedTransportDatabase());
			}
		});
		inputPack.setTravels(new HashSet<Travel>() {
			{
				add(seedTravelDatabase());
				add(seedTravelDatabase());
				add(seedTravelDatabase());
				add(seedTravelDatabase());
			}
		});

		Pack outputPack = packService.updatePack(inputPack);

		assertAll(() -> {
			assertEquals(inputPack.getId(), outputPack.getId());
			assertEquals(inputPack.getTitle(), outputPack.getTitle());
			assertEquals(inputPack.getDescription(), outputPack.getDescription());
			assertEquals(inputPack.getImage(), outputPack.getImage());
			assertEquals(inputPack.getPrice(), outputPack.getPrice());
			assertEquals(inputPack.getDuration(), outputPack.getDuration());
			assertEquals(inputPack.getPersons(), outputPack.getPersons());
			assertEquals(inputPack.getOutstanding(), outputPack.getOutstanding());
			assertEquals(inputPack.getHidden(), outputPack.getHidden());
			assertEquals(inputPack.getCreatedAt(), outputPack.getCreatedAt());
			assertEquals(inputPack.getAccommodations(), outputPack.getAccommodations());
			assertEquals(inputPack.getActivities(), outputPack.getActivities());
			assertEquals(inputPack.getTransports(), outputPack.getTransports());
			assertEquals(inputPack.getTravels(), outputPack.getTravels());
			assertEquals(4, outputPack.getTravels().size());
		});
	}

	@Test
	public void testRemovePackWithInstanceNotFoundException() {
		assertThrows(InstanceNotFoundException.class, () -> packService.removePack(-1L));
	}

	@Test
	public void testRemovePack() throws InstanceNotFoundException, InvalidOperationException {
		Pack pack = packService.createPack(createPack());
		packService.removePack(pack.getId());
		Page<Pack> packs = packService.findAllPacks(0, 1);

		assertEquals(0, packs.getNumberOfElements());
	}
}
