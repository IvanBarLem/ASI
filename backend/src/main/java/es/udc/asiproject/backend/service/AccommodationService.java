package es.udc.asiproject.backend.service;

import java.util.List;

import es.udc.asiproject.backend.persistence.model.Accommodation;

public interface AccommodationService {
	List<Accommodation> findAccommodations();
}
