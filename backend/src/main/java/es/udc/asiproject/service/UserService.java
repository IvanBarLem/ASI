package es.udc.asiproject.service;

import org.springframework.data.domain.Page;

import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.service.exceptions.DuplicateInstanceException;
import es.udc.asiproject.service.exceptions.IncorrectLoginException;
import es.udc.asiproject.service.exceptions.IncorrectPasswordException;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

public interface UserService {
	void signUp(User user) throws DuplicateInstanceException;

	User login(String email, String password) throws IncorrectLoginException;

	User loginFromId(Long id) throws InstanceNotFoundException;

	User updateProfile(Long id, String firstName, String lastName) throws InstanceNotFoundException;

	void changePassword(Long id, String oldPassword, String newPassword)
			throws InstanceNotFoundException, IncorrectPasswordException;
	
	Page<User> findClients(Long id, String keywords, Integer pageNumber, Integer pageSize) throws InstanceNotFoundException;

	Page<User> findAllUsers(Integer pageNumber, Integer pageSize);

}
