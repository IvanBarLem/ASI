package es.udc.asiproject.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.enums.RoleType;
import es.udc.asiproject.service.PermissionCheckerService;
import es.udc.asiproject.service.UserService;
import es.udc.asiproject.service.exceptions.DuplicateInstanceException;
import es.udc.asiproject.service.exceptions.IncorrectLoginException;
import es.udc.asiproject.service.exceptions.IncorrectPasswordException;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PermissionCheckerService permissionCheckerService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void signUp(User user) throws DuplicateInstanceException {
		if (userDao.existsByEmail(user.getEmail())) {
			throw new DuplicateInstanceException(User.class.getSimpleName(), user.getEmail());
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(RoleType.USER);

		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User login(String email, String password) throws IncorrectLoginException {
		Optional<User> user = userDao.findByEmail(email);

		if (!user.isPresent()) {
			throw new IncorrectLoginException(email, password);
		}

		if (!passwordEncoder.matches(password, user.get().getPassword())) {
			throw new IncorrectLoginException(email, password);
		}

		return user.get();
	}

	@Override
	@Transactional(readOnly = true)
	public User loginFromId(Long id) throws InstanceNotFoundException {
		return permissionCheckerService.checkUser(id);
	}

	@Override
	@Transactional
	public User updateProfile(Long id, String firstName, String lastName) throws InstanceNotFoundException {
		User user = permissionCheckerService.checkUser(id);

		user.setFirstName(firstName);
		user.setLastName(lastName);

		return user;
	}

	@Override
	@Transactional
	public void changePassword(Long id, String oldPassword, String newPassword)
			throws InstanceNotFoundException, IncorrectPasswordException {
		User user = permissionCheckerService.checkUser(id);

		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new IncorrectPasswordException();
		} else {
			user.setPassword(passwordEncoder.encode(newPassword));
		}
	}
}
