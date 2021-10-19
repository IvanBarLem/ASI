package es.udc.asiproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.backend.persistence.dao.ActivityDao;
import es.udc.asiproject.backend.persistence.model.Activity;
import es.udc.asiproject.backend.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDao activityDao;

	@Override
	@Transactional(readOnly = true)
	public List<Activity> findActivities() {
		return activityDao.findAll();
	}
}
