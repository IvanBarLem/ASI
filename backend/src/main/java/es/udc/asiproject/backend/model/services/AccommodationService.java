package es.udc.asiproject.backend.model.services;

import java.util.List;

import es.udc.asiproject.backend.model.entities.Accommodation;

public interface AccommodationService {

	List<Accommodation> findAccommodations();

}
