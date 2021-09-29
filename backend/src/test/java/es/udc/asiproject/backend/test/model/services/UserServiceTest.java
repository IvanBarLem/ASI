package es.udc.asiproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.asiproject.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.User;
import es.udc.asiproject.backend.model.services.IncorrectLoginException;
import es.udc.asiproject.backend.model.services.IncorrectPasswordException;
import es.udc.asiproject.backend.model.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);

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
		assertEquals(User.RoleType.USER, user.getRole());

	}

	@Test(expected = DuplicateInstanceException.class)
	public void testSignUpDuplicatedUserName() throws DuplicateInstanceException {

		User user = createUser("user@email.com");

		userService.signUp(user);
		userService.signUp(user);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testloginFromNonExistentId() throws InstanceNotFoundException {
		userService.loginFromId(NON_EXISTENT_ID);
	}

	@Test
	public void testLogin() throws DuplicateInstanceException, IncorrectLoginException {

		User user = createUser("user@email.com");
		String clearPassword = user.getPassword();

		userService.signUp(user);

		User loggedInUser = userService.login(user.getEmail(), clearPassword);

		assertEquals(user, loggedInUser);

	}

	@Test(expected = IncorrectLoginException.class)
	public void testLoginWithIncorrectPassword() throws DuplicateInstanceException, IncorrectLoginException {

		User user = createUser("user@email.com");
		String clearPassword = user.getPassword();

		userService.signUp(user);
		userService.login(user.getEmail(), 'X' + clearPassword);

	}

	@Test(expected = IncorrectLoginException.class)
	public void testLoginWithNonExistentUserName() throws IncorrectLoginException {
		userService.login("X", "Y");
	}

	@Test
	public void testUpdateProfile() throws InstanceNotFoundException, DuplicateInstanceException {

		User user = createUser("user@email.com");

		userService.signUp(user);

		user.setFirstName('X' + user.getFirstName());
		user.setLastName('X' + user.getLastName());

		userService.updateProfile(user.getId(), 'X' + user.getFirstName(), 'X' + user.getLastName());

		User updatedUser = userService.loginFromId(user.getId());

		assertEquals(user, updatedUser);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testUpdateProfileWithNonExistentId() throws InstanceNotFoundException {
		userService.updateProfile(NON_EXISTENT_ID, "X", "X");
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

	@Test(expected = InstanceNotFoundException.class)
	public void testChangePasswordWithNonExistentId() throws InstanceNotFoundException, IncorrectPasswordException {
		userService.changePassword(NON_EXISTENT_ID, "X", "Y");
	}

	@Test(expected = IncorrectPasswordException.class)
	public void testChangePasswordWithIncorrectPassword()
			throws DuplicateInstanceException, InstanceNotFoundException, IncorrectPasswordException {

		User user = createUser("user@email.com");
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;

		userService.signUp(user);
		userService.changePassword(user.getId(), 'Y' + oldPassword, newPassword);

	}

}
