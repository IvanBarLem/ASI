package es.udc.asiproject.backend.service;

import es.udc.asiproject.backend.persistence.model.User;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;

public interface PermissionCheckerService {
	public void checkUserExists(Long userId) throws InstanceNotFoundException;

	public User checkUser(Long userId) throws InstanceNotFoundException;
}
