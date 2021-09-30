package es.udc.asiproject.backend.model.services;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.TransportType;

public interface EntityChecker {
    public void checkTransportTypeExists(Long transportTypeId) throws InstanceNotFoundException;

    public TransportType checkTransportType(Long transportTypeId) throws InstanceNotFoundException;

}
