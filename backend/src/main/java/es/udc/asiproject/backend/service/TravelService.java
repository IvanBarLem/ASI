package es.udc.asiproject.backend.service;

import java.util.List;

import es.udc.asiproject.backend.persistence.model.Travel;

public interface TravelService {
	List<Travel> findTravels();
}