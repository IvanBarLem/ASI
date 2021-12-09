package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.enums.RoleType;
import es.udc.asiproject.service.UserService;
import es.udc.asiproject.service.exceptions.DuplicateInstanceException;
import es.udc.asiproject.service.exceptions.IncorrectLoginException;
import es.udc.asiproject.service.exceptions.IncorrectPasswordException;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
	@Autowired
	private UserService userService;

	/*
	 * Resuelve CU 1. Prueba para comprobar que un usuario puede darse de alta en la
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
	public void should_return_new_user() throws DuplicateInstanceException, InstanceNotFoundException {
		User user = User.builder().email("user@email.com").password("password").firstName("firstName")
				.lastName("lastName").build();

		userService.signUp(user);

		User loggedInUser = userService.loginFromId(user.getId());

		assertEquals(user, loggedInUser);
		assertEquals(RoleType.USER, user.getRole());
	}

	/*
	 * Resuelve CU 1. Prueba para comprobar que un usuario no puede darse de alta en
	 * la aplicaci�n si ya existe un usuario con el mismo correo electr�nico.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_create_new_user() {
		Assertions.assertThrows(DuplicateInstanceException.class, () -> {
			User user = User.builder().email("user@email.com").password("password").firstName("firstName")
					.lastName("lastName").build();

			userService.signUp(user);
			userService.signUp(user);
		});
	}

	/*
	 * Resuelve CU 1.2. Prueba para comprobar que un usuario puede iniciar sesi�n en
	 * la aplicaci�n.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * prositiva.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_return_same_user() throws DuplicateInstanceException, IncorrectLoginException {
		User user = User.builder().email("user@email.com").password("password").firstName("firstName")
				.lastName("lastName").build();
		userService.signUp(user);

		User loggedInUser = userService.login(user.getEmail(), "password");

		assertEquals(user, loggedInUser);
	}

	/*
	 * Resuelve CU 1.2. Prueba para comprobar que un usuario no puede iniciar sesi�n
	 * en la aplicaci�n si no se ha dado de alta previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_login_with_invalid_id() throws InstanceNotFoundException {
		Assertions.assertThrows(InstanceNotFoundException.class, () -> {
			userService.loginFromId(-1L);
		});
	}

	/*
	 * Resuelve CU 1.2. Prueba para comprobar que un usuario no puede iniciar sesi�n
	 * en la aplicaci�n si introduce mal el correo electr�nico.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_login_with_wrong_email() {
		Assertions.assertThrows(IncorrectLoginException.class, () -> {
			User user = User.builder().email("user@email.com").password("password").firstName("firstName")
					.lastName("lastName").build();
			userService.signUp(user);
			userService.login("X" + user.getEmail(), "password");
		});
	}

	/*
	 * Resuelve CU 1.2. Prueba para comprobar que un usuario no puede iniciar sesi�n
	 * en la aplicaci�n si introduce mal la contrase�a.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_login_with_wrong_password() {
		Assertions.assertThrows(IncorrectLoginException.class, () -> {
			User user = User.builder().email("user@email.com").password("password").firstName("firstName")
					.lastName("lastName").build();
			userService.signUp(user);
			userService.login(user.getEmail(), "X");
		});
	}

	/*
	 * Resuelve CU 1.1. Prueba para comprobar que un usuario puede modificar sus
	 * datos.
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
	public void should_return_updated_user() throws InstanceNotFoundException, DuplicateInstanceException {
		User user = User.builder().email("user@email.com").password("password").firstName("firstName")
				.lastName("lastName").build();
		userService.signUp(user);
		userService.updateProfile(user.getId(), 'X' + user.getFirstName(), 'X' + user.getLastName());

		user.setFirstName('X' + user.getFirstName());
		user.setLastName('X' + user.getLastName());

		User updatedUser = userService.loginFromId(user.getId());

		assertEquals(user, updatedUser);
	}

	/*
	 * Resuelve CU 1.1. Prueba para comprobar que un usuario no puede modificar sus
	 * datos si no se ha dado de alta previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_update_invalid_id() {
		Assertions.assertThrows(InstanceNotFoundException.class, () -> {
			userService.updateProfile(-1L, "X", "X");
		});
	}

	/*
	 * Resuelve CU 1.1. Prueba para comprobar que un usuario puede modificar su
	 * clave de acceso.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_change_user_password() throws DuplicateInstanceException, InstanceNotFoundException,
			IncorrectPasswordException, IncorrectLoginException {
		User user = User.builder().email("user@email.com").password("password").firstName("firstName")
				.lastName("lastName").build();
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;

		userService.signUp(user);
		userService.changePassword(user.getId(), oldPassword, newPassword);
		userService.login(user.getEmail(), newPassword);
	}

	/*
	 * Resuelve CU 1.1. Prueba para comprobar que un usuario no puede modificar su
	 * clave de acceso si no se ha dado de alta previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_update_password_with_invaled_id() {
		Assertions.assertThrows(InstanceNotFoundException.class, () -> {
			userService.changePassword(-1L, "X", "Y");
		});
	}

	/*
	 * Resuelve CU 1.1. Prueba para comprobar que un usuario no puede modificar su
	 * clave de acceso si no provee la que ten�a previamente.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categor�as a las que pertenece: prueba funcional din�mica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selecci�n de datos: prueba con generaci�n de datos de entrada
	 * est�tica.
	 */
	@Test
	public void should_fail_when_update_password_with_incorrect_password() {
		Assertions.assertThrows(IncorrectPasswordException.class, () -> {
			User user = User.builder().email("user@email.com").password("password").firstName("firstName")
					.lastName("lastName").build();
			String oldPassword = user.getPassword();
			String newPassword = 'X' + oldPassword;

			userService.signUp(user);
			userService.changePassword(user.getId(), 'Y' + oldPassword, newPassword);
		});
	}
}
