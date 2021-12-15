package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.persistence.dao.UserDao;
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
	@Autowired
	private UserDao userDao;

	private User createUser(String email) {
		return User.builder().email(email).password("password").firstName("firstName").lastName("lastName").build();
	}

	@Test
	public void testSignUpAndLoginFromId() throws DuplicateInstanceException, InstanceNotFoundException {
		User user = createUser("user@email.com");

		userService.signUp(user);

		User loggedInUser = userService.loginFromId(user.getId());

		assertEquals(user, loggedInUser);
		assertEquals(RoleType.USER, user.getRole());
	}

	@Test
	public void testSignUpDuplicatedUserName() {
		Assertions.assertThrows(DuplicateInstanceException.class, () -> {
			User user = createUser("user@email.com");

			userService.signUp(user);
			userService.signUp(user);
		});
	}

	@Test
	public void testloginFromNonExistentId() throws InstanceNotFoundException {
		Assertions.assertThrows(InstanceNotFoundException.class, () -> {
			userService.loginFromId(-1L);
		});
	}

	@Test
	public void testLogin() throws DuplicateInstanceException, IncorrectLoginException {
		User user = createUser("user@email.com");
		String clearPassword = user.getPassword();

		userService.signUp(user);

		User loggedInUser = userService.login(user.getEmail(), clearPassword);

		assertEquals(user, loggedInUser);
	}

	@Test
	public void testLoginWithIncorrectPassword() {
		Assertions.assertThrows(IncorrectLoginException.class, () -> {
			User user = createUser("user@email.com");
			String clearPassword = user.getPassword();

			userService.signUp(user);
			userService.login(user.getEmail(), 'X' + clearPassword);
		});
	}

	@Test
	public void testLoginWithNonExistentUserName() {
		Assertions.assertThrows(IncorrectLoginException.class, () -> {
			userService.login("X", "Y");
		});
	}

	@Test
	public void testUpdateProfile() throws InstanceNotFoundException, DuplicateInstanceException {
		User user = createUser("user@email.com");

		userService.signUp(user);
		userService.updateProfile(user.getId(), 'X' + user.getFirstName(), 'X' + user.getLastName());

		user.setFirstName('X' + user.getFirstName());
		user.setLastName('X' + user.getLastName());

		User updatedUser = userService.loginFromId(user.getId());

		assertEquals(user, updatedUser);
	}

	@Test
	public void testUpdateProfileWithNonExistentId() {
		Assertions.assertThrows(InstanceNotFoundException.class, () -> {
			userService.updateProfile(-1L, "X", "X");
		});
	}

	@Test
	public void testChangePassword() throws DuplicateInstanceException, InstanceNotFoundException,
			IncorrectPasswordException, IncorrectLoginException {
		User user = createUser("user@email.com");
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;

		userService.signUp(user);
		userService.changePassword(user.getId(), oldPassword, newPassword);
		userService.login(user.getEmail(), newPassword);
	}

	@Test
	public void testChangePasswordWithNonExistentId() {
		Assertions.assertThrows(InstanceNotFoundException.class, () -> {
			userService.changePassword(-1L, "X", "Y");
		});
	}

	@Test
	public void testChangePasswordWithIncorrectPassword() {
		Assertions.assertThrows(IncorrectPasswordException.class, () -> {
			User user = createUser("user@email.com");
			String oldPassword = user.getPassword();
			String newPassword = 'X' + oldPassword;

			userService.signUp(user);
			userService.changePassword(user.getId(), 'Y' + oldPassword, newPassword);
		});
	}

	/*
	 * Resuelve CU 2. Prueba para comprobar que se puede obtener la lista de
	 * clientes registrados en la aplicación.
	 * 
	 * Nivel de prueba: unidad.
	 * 
	 * Categorías a las que pertenece: prueba funcional dinámica de caja negra
	 * negativa.
	 * 
	 * Mecanismo de selección de datos: prueba con generación de datos de entrada
	 * estática.
	 */
	@Test
	public void should_return_list_with_clients() throws InstanceNotFoundException {
		User user1 = User.builder().email("user1@gmail.com").password("password").firstName("firstName")
				.lastName("lastName").role(RoleType.USER).build();
		userDao.save(user1);
		User user2 = User.builder().email("user2@gmail.com").password("password").firstName("firstName")
				.lastName("lastName").role(RoleType.USER).build();
		userDao.save(user2);
		User user3 = User.builder().email("user3@gmail.com").password("password").firstName("firstName")
				.lastName("lastName").role(RoleType.AGENTE).build();
		userDao.save(user3);

		Page<User> page = userService.findClients(user1.getId(), "firstName", 0, 10);

		assertAll(() -> {
			assertEquals(1, page.getTotalPages());
			assertEquals(2, page.getNumberOfElements());
			assertEquals(user1, page.getContent().get(0));
			assertEquals(user2, page.getContent().get(1));
			assertFalse(page.hasNext());
			assertFalse(page.hasPrevious());
		});
	}
}
