package es.udc.asiproject.backend.model.services;

import es.udc.asiproject.backend.daos.entities.User;
import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;

public interface PermissionChecker {

	public void checkUserExists(Long userId) throws InstanceNotFoundException;

	public User checkUser(Long userId) throws InstanceNotFoundException;

}
