package es.udc.asiproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

@Service
public interface ProductService {
	Accommodation createAccommodation(Accommodation accommodation);

	List<Accommodation> findAccommodations();

	void removeAccommodation(Long id) throws InstanceNotFoundException;

	Activity createActivity(Activity activity);

	List<Activity> findActivities();

	void removeActivity(Long id) throws InstanceNotFoundException;

	Transport createTransport(Transport transport);

	List<Transport> findTransports();

	void removeTransport(Long id) throws InstanceNotFoundException;

	Travel createTravel(Travel travel);

	List<Travel> findTravels();

	void removeTravel(Long id) throws InstanceNotFoundException;
}
