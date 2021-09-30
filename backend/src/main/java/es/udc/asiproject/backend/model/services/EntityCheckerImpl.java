package es.udc.asiproject.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.TransportType;
import es.udc.asiproject.backend.model.entities.TransportTypeDao;

@Service
public class EntityCheckerImpl implements EntityChecker {

    @Autowired
    TransportTypeDao transportTypeDao;

    @Override
    public void checkTransportTypeExists(Long transportTypeId) throws InstanceNotFoundException {
	if (!transportTypeDao.existsById(transportTypeId)) {
	    throw new InstanceNotFoundException("project.entities.transportType", transportTypeId);
	}
    }

    @Override
    public TransportType checkTransportType(Long transportTypeId) throws InstanceNotFoundException {
	Optional<TransportType> transportType = transportTypeDao.findById(transportTypeId);

	if (!transportType.isPresent()) {
	    throw new InstanceNotFoundException("project.entities.transportType", transportTypeId);
	}
	return transportType.get();
    }

}
