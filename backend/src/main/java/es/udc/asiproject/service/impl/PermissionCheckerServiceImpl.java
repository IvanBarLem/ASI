package es.udc.asiproject.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.service.PermissionCheckerService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

@Service
public class PermissionCheckerServiceImpl implements PermissionCheckerService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public void checkUserExists(Long userId) throws InstanceNotFoundException {
		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException(User.class.getSimpleName(), userId);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User checkUser(Long userId) throws InstanceNotFoundException {
		Optional<User> user = userDao.findById(userId);

		if (!user.isPresent()) {
			throw new InstanceNotFoundException(User.class.getSimpleName(), userId);
		}

		return user.get();
	}
}
