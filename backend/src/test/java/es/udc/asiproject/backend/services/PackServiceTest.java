package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import es.udc.asiproject.persistence.model.Product;
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

	@SuppressWarnings("serial")
	private Pack createPack() {
		Pack pack = Pack.builder().title("title").description("description").image(new Byte[] { 1, 2, 3 })
				.price(new BigDecimal(1.23)).duration(5).persons("persons").outstanding(false).hidden(false)
				.createdAt(new Date()).build();
		pack.setProducts(new HashSet<Product>() {
			{
				add(seedAccommodationDatabase());
				add(seedActivityDatabase());
				add(seedTransportDatabase());
				add(seedTravelDatabase());
			}
		});

		return pack;
	}

	/**
	 * Resuelve CU 3. Prueba para comprobar la correcta creacion de un paquete.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_new_pack() throws InstanceNotFoundException, InvalidOperationException {
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
			assertEquals(inputPack.getProducts(), outputPack.getProducts());
		});
	}

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que los paquetes no ocultos se
	 * devuelven correctamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_not_hidden_packs()
			throws InstanceNotFoundException, InvalidOperationException, ParseException {
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

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que los paquetes no ocultos se
	 * devuelven correctamente, siendo los primeros los que hayan sido destacados.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_not_hidden_packs_order_by_outstanding()
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

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que si no hay paquetes no ocultos no
	 * se devuelve nada.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_empty_list_with_not_hidden_packs() {
		Pack pack = createPack();
		pack.setHidden(true);
		packDao.save(pack);

		Page<Pack> page = packService.findPacks(0, 1);

		assertAll(() -> {
			assertEquals(0, page.getTotalPages());
			assertEquals(0, page.getNumberOfElements());
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que todos los paquetes se devuelven
	 * correctamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_all_packs() throws InstanceNotFoundException, InvalidOperationException, ParseException {
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

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que todos los paquetes se devuelven
	 * correctamente, siendo los primeros los que hayan sido destacados.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_all_packs_order_by_outstanding()
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

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que si no hay paquetes no se devuelve
	 * nada.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_return_empty_list_with_packs() {
		Page<Pack> page = packService.findAllPacks(0, 1);

		assertAll(() -> {
			assertEquals(0, page.getTotalPages());
			assertEquals(0, page.getNumberOfElements());
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}

	/**
	 * Resuelve CU 3.6. Prueba para comprobar que un paquete se actualiza
	 * correctamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@SuppressWarnings("serial")
	@Test
	public void should_update_pack() throws InstanceNotFoundException, InvalidOperationException {
		Pack inputPack = packService.createPack(createPack());
		inputPack.setTitle(inputPack.getTitle() + "X");
		inputPack.setDescription(inputPack.getDescription() + "X");
		inputPack.setImage(new Byte[] { 3, 2, 1 });
		inputPack.setPrice(new BigDecimal(3.21));
		inputPack.setDuration(8);
		inputPack.setOutstanding(true);
		inputPack.setHidden(true);
		inputPack.setProducts(new HashSet<Product>() {
			{
				add(seedAccommodationDatabase());
				add(seedActivityDatabase());
				add(seedActivityDatabase());
				add(seedTransportDatabase());
				add(seedTransportDatabase());
				add(seedTransportDatabase());
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
			assertEquals(inputPack.getProducts(), outputPack.getProducts());
		});
	}

	/**
	 * Resuelve CU 3.1. Prueba para comprobar que un paquete se destaca
	 * correctamente correctamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_change_outstanding_pack() throws InstanceNotFoundException, InvalidOperationException {
		Pack inputPack = packService.createPack(createPack());
		packService.toggleOutstandingPack(inputPack.getId());
		Pack outputPack = packDao.findById(inputPack.getId())
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), inputPack.getId()));

		assertTrue(outputPack.getOutstanding());

		packService.toggleOutstandingPack(inputPack.getId());
		outputPack = packDao.findById(inputPack.getId())
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), inputPack.getId()));
		assertFalse(outputPack.getOutstanding());
	}

	/**
	 * Resuelve CU 3.7. Prueba para comprobar que un paquete se oculta correctamente
	 * correctamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * positiva.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_change_hidden_pack() throws InstanceNotFoundException, InvalidOperationException {
		Pack inputPack = packService.createPack(createPack());
		packService.toggleHiddenPack(inputPack.getId());
		Pack outputPack = packDao.findById(inputPack.getId())
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), inputPack.getId()));

		assertTrue(outputPack.getHidden());

		packService.toggleHiddenPack(inputPack.getId());
		outputPack = packDao.findById(inputPack.getId())
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), inputPack.getId()));
		assertFalse(outputPack.getHidden());
	}
}
