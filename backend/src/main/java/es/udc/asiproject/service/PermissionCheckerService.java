package es.udc.asiproject.service;

import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

public interface PermissionCheckerService {
	public void checkUserExists(Long userId) throws InstanceNotFoundException;

	public User checkUser(Long userId) throws InstanceNotFoundException;
}
