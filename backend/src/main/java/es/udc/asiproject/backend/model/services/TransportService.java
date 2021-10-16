package es.udc.asiproject.backend.model.services;

import java.util.List;

import es.udc.asiproject.backend.daos.entities.Transport;

public interface TransportService {
	List<Transport> findTransports();
}
