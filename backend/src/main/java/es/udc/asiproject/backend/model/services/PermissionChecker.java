package es.udc.asiproject.backend.model.services;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.User;

public interface PermissionChecker {

	public void checkUserExists(Long userId) throws InstanceNotFoundException;

	public User checkUser(Long userId) throws InstanceNotFoundException;

}
