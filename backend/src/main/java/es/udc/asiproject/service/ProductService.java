package es.udc.asiproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;

@Service
public interface ProductService {
	Accommodation createAccommodation(Accommodation accommodation);

	List<Accommodation> findAccommodations();

	Activity createActivity(Activity activity);

	List<Activity> findActivities();

	Transport createTransport(Transport transport);

	List<Transport> findTransports();

	Travel createTravel(Travel travel);

	List<Travel> findTravels();
}
