package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
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

	/**
	 * Resuelve CU 4. Prueba para comprobar la correcta creacion de un producto de
	 * tipo alojamiento.
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
	public void should_return_new_accommodation() {
		Accommodation accommodation = productService.createAccommodation(Accommodation.builder()
				.name("Hesperia marineda").location("Madrid").price(new BigDecimal(1.23)).build());
		List<Accommodation> accommodations = productService.findAllAccommodations();

		assertEquals(accommodation, accommodations.get(0));
	}

	/**
	 * Resuelve CU 3.3. Prueba para obtener la lista de alojamientos que no estan
	 * ocultos.
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
	public void should_return_not_hidden_accommodations() throws InstanceNotFoundException {
		Accommodation accommodation = productService.createAccommodation(Accommodation.builder()
				.name("Hesperia marineda").location("Madrid").price(new BigDecimal(1.23)).hidden(false).build());
		accommodation.setHidden(true);
		productService.updateAccommodation(accommodation);

		assertEquals(0, productService.findAccommodations().size());
	}

	/**
	 * Resuelve CU 3.3. Prueba para obtener la lista completa de alojamientos.
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
	public void should_return_all_accommodations() {
		Accommodation accommodation = Accommodation.builder().name("Hesperia marineda").location("Madrid")
				.price(new BigDecimal(1.23)).hidden(true).build();
		accommodationDao.save(accommodation);

		assertEquals(1, productService.findAllAccommodations().size());
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar la correcta modificacion de
	 * un producto de tipo alojamiento.
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
	public void slould_return_updated_accommodation() throws InstanceNotFoundException {
		Accommodation accommodation = productService.createAccommodation(Accommodation.builder()
				.name("Hesperia marineda").location("Madrid").price(new BigDecimal(1.23)).build());
		accommodation.setPrice(new BigDecimal(3.21));
		accommodation.setName(accommodation.getName() + "X");
		productService.updateAccommodation(accommodation);
		List<Accommodation> accommodations = productService.findAllAccommodations();

		assertEquals(accommodation, accommodations.get(0));
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar que no se puede modificar un
	 * producto de tipo alojamiento que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_update_accommodation_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.updateAccommodation(Accommodation.builder()
				.id(-1L).name("Hesperia marineda").price(new BigDecimal(1.23)).hidden(false).build()));
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar la correcta eliminacion de un producto
	 * de tipo alojamiento.
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
	public void should_remove_accommodation() throws InstanceNotFoundException {
		Accommodation accommodation = productService.createAccommodation(Accommodation.builder()
				.name("Hesperia marineda").location("Madrid").price(new BigDecimal(1.23)).build());
		productService.removeAccommodation(accommodation.getId());
		List<Accommodation> accommodations = productService.findAllAccommodations();

		assertEquals(0, accommodations.size());
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar que no se puede eliminar un producto
	 * de tipo alojamiento que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_remove_accommodation_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeAccommodation(-1L));
	}

	/**
	 * Resuelve CU 4. Prueba para comprobar la correcta creacion de un producto de
	 * tipo actividad.
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
	public void should_return_new_activity() {
		Activity activity = productService.createActivity(
				Activity.builder().name("Motos de Agua").location("Madrid").price(new BigDecimal(1.23)).build());
		List<Activity> activities = productService.findAllActivities();

		assertEquals(activity, activities.get(0));
	}

	/**
	 * Resuelve CU 3.5. Prueba para obtener la lista de actividades que no estan
	 * ocultos.
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
	public void should_return_not_hidden_activities() throws InstanceNotFoundException {
		Activity activity = productService.createActivity(Activity.builder().name("Motos de Agua").location("Madrid")
				.price(new BigDecimal(1.23)).hidden(false).build());
		activity.setHidden(true);
		productService.updateActivity(activity);

		assertEquals(0, productService.findActivities().size());
	}

	/**
	 * Resuelve CU 3.5. Prueba para obtener la lista completa de actividades.
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
	public void should_return_all_activities() {
		Activity activity = Activity.builder().name("Motos de Agua").location("Madrid").price(new BigDecimal(1.23))
				.hidden(true).build();
		activityDao.save(activity);

		assertEquals(1, productService.findAllActivities().size());
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar la correcta modificacion de
	 * un producto de tipo actividad.
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
	public void slould_return_updated_activity() throws InstanceNotFoundException {
		Activity activity = productService.createActivity(
				Activity.builder().name("Motos de Agua").location("Madrid").price(new BigDecimal(1.23)).build());
		activity.setPrice(new BigDecimal(3.21));
		activity.setName(activity.getName() + "X");
		productService.updateActivity(activity);
		List<Activity> activities = productService.findAllActivities();

		assertEquals(activity, activities.get(0));
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar que no se puede modificar un
	 * producto de tipo actividad que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_update_activity_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.updateActivity(Activity.builder().id(-1L)
				.name("Motos de Agua").location("Madrid").price(new BigDecimal(1.23)).hidden(false).build()));
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar la correcta eliminacion de un producto
	 * de tipo actividad.
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
	public void should_remove_activity() throws InstanceNotFoundException {
		Activity activity = productService.createActivity(
				Activity.builder().name("Motos de Agua").location("Madrid").price(new BigDecimal(1.23)).build());
		productService.removeActivity(activity.getId());
		List<Activity> activities = productService.findAllActivities();

		assertEquals(0, activities.size());
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar que no se puede eliminar un producto
	 * de tipo actividad que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_remove_activity_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeActivity(-1L));
	}

	/**
	 * Resuelve CU 4. Prueba para comprobar la correcta creacion de un producto de
	 * tipo transporte.
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
	public void should_return_new_transport() {
		Transport transport = productService.createTransport(
				Transport.builder().name("Patineta").location("Madrid").price(new BigDecimal(1.23)).build());
		List<Transport> transports = productService.findAllTransports();

		assertEquals(transport, transports.get(0));
	}

	/**
	 * Resuelve CU 3.4. Prueba para obtener la lista de transportes que no estan
	 * ocultos.
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
	public void should_return_not_hidden_transports() throws InstanceNotFoundException {
		Transport transport = productService.createTransport(Transport.builder().name("Patineta").location("Madrid")
				.price(new BigDecimal(1.23)).hidden(false).build());
		transport.setHidden(true);
		productService.updateTransport(transport);

		assertEquals(0, productService.findTransports().size());
	}

	/**
	 * Resuelve CU 3.4. Prueba para obtener la lista completa de transportes.
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
	public void should_return_all_transports() {
		transportDao.save(Transport.builder().name("Patineta").location("Madrid").price(new BigDecimal(1.23))
				.hidden(false).build());
		assertEquals(1, productService.findAllTransports().size());

		transportDao.save(Transport.builder().name("Patines").location("Madrid").price(new BigDecimal(1.23))
				.hidden(true).build());
		assertEquals(2, productService.findAllTransports().size());
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar la correcta modificacion de
	 * un producto de tipo transporte.
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
	public void slould_return_updated_transport() throws InstanceNotFoundException {
		Transport transport = productService.createTransport(
				Transport.builder().name("Patineta").location("Madrid").price(new BigDecimal(1.23)).build());
		transport.setPrice(new BigDecimal(3.21));
		transport.setName(transport.getName() + "X");
		productService.updateTransport(transport);
		List<Transport> transports = productService.findAllTransports();

		assertEquals(transport, transports.get(0));
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar que no se puede modificar un
	 * producto de tipo transporte que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_update_transport_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.updateTransport(Transport.builder().id(-1L)
				.name("Patineta").location("Madrid").price(new BigDecimal(1.23)).hidden(false).build()));
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar la correcta eliminacion de un producto
	 * de tipo transporte.
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
	public void should_remove_transport() throws InstanceNotFoundException {
		Transport transport = productService.createTransport(
				Transport.builder().name("Patineta").location("Madrid").price(new BigDecimal(1.23)).build());
		productService.removeTransport(transport.getId());
		List<Transport> transports = productService.findAllTransports();

		assertEquals(0, transports.size());
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar que no se puede eliminar un producto
	 * de tipo transporte que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_remove_transport_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeTransport(-1L));
	}

	/**
	 * Resuelve CU 4. Prueba para comprobar la correcta creacion de un producto de
	 * tipo viaje.
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
	public void should_return_new_travel() {
		Travel travel = productService.createTravel(
				Travel.builder().name("Egipto Antiguo").location("Madrid").price(new BigDecimal(1.23)).build());
		List<Travel> travels = productService.findAllTravels();

		assertEquals(travel, travels.get(0));
	}

	/**
	 * Resuelve CU 3.2. Prueba para obtener la lista de viajes que no estan ocultos.
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
	public void should_return_not_hidden_travels() throws InstanceNotFoundException {
		Travel travel = productService.createTravel(Travel.builder().name("Egipto Antiguo").location("Madrid")
				.price(new BigDecimal(1.23)).hidden(false).build());
		travel.setHidden(true);
		productService.updateTravel(travel);

		assertEquals(0, productService.findTravels().size());
	}

	/**
	 * Resuelve CU 3.2. Prueba para obtener la lista completa de viajes.
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
	public void should_return_all_travels() {
		Travel travel = Travel.builder().name("Egipto Antiguo").location("Madrid").price(new BigDecimal(1.23))
				.hidden(true).build();
		travelDao.save(travel);

		assertEquals(1, productService.findAllTravels().size());
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar la correcta modificacion de
	 * un producto de tipo viaje.
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
	public void slould_return_updated_travel() throws InstanceNotFoundException {
		Travel travel = productService.createTravel(
				Travel.builder().name("Egipto Antiguo").location("Madrid").price(new BigDecimal(1.23)).build());
		travel.setPrice(new BigDecimal(3.21));
		travel.setName(travel.getName() + "X");
		productService.updateTravel(travel);
		List<Travel> travels = productService.findAllTravels();

		assertEquals(travel, travels.get(0));
	}

	/**
	 * Resuelve CU 4.1 y CU 4.3. Prueba para comprobar que no se puede modificar un
	 * producto de tipo viaje que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_update_travel_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.updateTravel(Travel.builder().id(-1L)
				.name("Egipto Antiguo").location("Madrid").price(new BigDecimal(1.23)).hidden(false).build()));
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar la correcta eliminacion de un producto
	 * de tipo viaje.
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
	public void should_remove_travel() throws InstanceNotFoundException {
		Travel travel = productService.createTravel(
				Travel.builder().name("Egipto Antiguo").location("Madrid").price(new BigDecimal(1.23)).build());
		productService.removeTravel(travel.getId());
		List<Travel> travels = productService.findAllTravels();

		assertEquals(0, travels.size());
	}

	/**
	 * Resuelve CU 4.2. Prueba para comprobar que no se puede eliminar un producto
	 * de tipo viaje que no se haya creado previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorias a las que pertenece: prueba funcional dinamica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de seleccion de datos: prueba con generacion de datos de entrada
	 * estatica.
	 */
	@Test
	public void should_fail_when_remove_travel_with_invalid_id() {
		assertThrows(InstanceNotFoundException.class, () -> productService.removeTravel(-1L));
	}
}
