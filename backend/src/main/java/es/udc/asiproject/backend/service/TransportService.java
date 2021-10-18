package es.udc.asiproject.backend.service;

import java.util.List;

import es.udc.asiproject.backend.persistence.model.Transport;

public interface TransportService {
	List<Transport> findTransports();
}
