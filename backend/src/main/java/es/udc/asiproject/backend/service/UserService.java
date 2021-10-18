package es.udc.asiproject.backend.service;

import es.udc.asiproject.backend.persistence.model.User;
import es.udc.asiproject.backend.service.exceptions.DuplicateInstanceException;
import es.udc.asiproject.backend.service.exceptions.IncorrectLoginException;
import es.udc.asiproject.backend.service.exceptions.IncorrectPasswordException;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;

public interface UserService {
	void signUp(User user) throws DuplicateInstanceException;

	User login(String email, String password) throws IncorrectLoginException;

	User loginFromId(Long id) throws InstanceNotFoundException;

	User updateProfile(Long id, String firstName, String lastName) throws InstanceNotFoundException;

	void changePassword(Long id, String oldPassword, String newPassword)
			throws InstanceNotFoundException, IncorrectPasswordException;
}
