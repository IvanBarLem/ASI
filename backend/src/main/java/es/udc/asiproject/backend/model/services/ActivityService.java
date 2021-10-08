package es.udc.asiproject.backend.model.services;

import java.util.List;

import es.udc.asiproject.backend.model.entities.Activity;

public interface ActivityService {

	List<Activity> findActivities();

}
