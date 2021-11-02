package es.udc.asiproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;

@Service
public interface ProductService {
	List<Accommodation> findAccommodations();

	List<Activity> findActivities();

	List<Transport> findTransports();

	List<Travel> findTravels();
}
