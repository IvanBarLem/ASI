package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.daos.entities.User;
import es.udc.asiproject.backend.daos.entities.User.RoleType;
import es.udc.asiproject.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.services.IncorrectLoginException;
import es.udc.asiproject.backend.model.services.IncorrectPasswordException;
import es.udc.asiproject.backend.model.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
	private final Long NON_EXISTENT_ID = (long) -1;

	@Autowired
	private UserService userService;

	private User createUser(String email) {
		return new User("password", "firstName", "lastName", email);
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
			userService.loginFromId(NON_EXISTENT_ID);
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
			userService.updateProfile(NON_EXISTENT_ID, "X", "X");
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
			userService.changePassword(NON_EXISTENT_ID, "X", "Y");
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
}
