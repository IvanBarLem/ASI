package es.udc.asiproject.backend.model.services;

import java.util.List;

import es.udc.asiproject.backend.model.entities.Travel;

public interface TravelService {

	List<Travel> findTravels();

}