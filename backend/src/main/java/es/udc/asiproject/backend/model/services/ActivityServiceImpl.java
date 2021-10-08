package es.udc.asiproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.model.entities.Activity;
import es.udc.asiproject.backend.model.entities.ActivityDao;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;

	@Override
	public List<Activity> findActivities() {

		Iterable<Activity> activities = activityDao.findAll();
		List<Activity> activitiesList = new ArrayList<Activity>();

		for (Activity activity : activities) {
			activitiesList.add(activity);
		}
		return activitiesList;
	}

}
