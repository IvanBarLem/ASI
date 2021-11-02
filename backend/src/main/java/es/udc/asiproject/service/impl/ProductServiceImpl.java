package es.udc.asiproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private AccommodationDao accommodationDao;

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private TransportDao transportDao;

	@Autowired
	private TravelDao travelDao;

	@Override
	@Transactional(readOnly = true)
	public List<Accommodation> findAccommodations() {
		return accommodationDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Activity> findActivities() {
		return activityDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transport> findTransports() {
		return transportDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Travel> findTravels() {
		return travelDao.findAll();
	}
}
