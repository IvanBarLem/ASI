package es.udc.asiproject.backend.model.services;

import es.udc.asiproject.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.User;

public interface UserService {

	void signUp(User user) throws DuplicateInstanceException;

	User login(String userName, String password) throws IncorrectLoginException;

	User loginFromId(Long id) throws InstanceNotFoundException;

	User updateProfile(Long id, String firstName, String lastName, String email) throws InstanceNotFoundException;

	void changePassword(Long id, String oldPassword, String newPassword)
			throws InstanceNotFoundException, IncorrectPasswordException;

}
