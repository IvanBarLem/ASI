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
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

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
	public Accommodation createAccommodation(Accommodation accommodation) {
		accommodation.setHidden(false);

		return accommodationDao.save(accommodation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accommodation> findAccommodations() {
		return accommodationDao.findAll();
	}

	@Override
	public void removeAccommodation(Long id) throws InstanceNotFoundException {
		Accommodation accommodation = accommodationDao.findById(id)
				.orElseThrow(() -> new InstanceNotFoundException(Accommodation.class.getSimpleName(), id));

		accommodationDao.delete(accommodation);
	}

	@Override
	public Activity createActivity(Activity activity) {
		activity.setHidden(false);

		return activityDao.save(activity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Activity> findActivities() {
		return activityDao.findAll();
	}

	@Override
	public void removeActivity(Long id) throws InstanceNotFoundException {
		Activity activity = activityDao.findById(id)
				.orElseThrow(() -> new InstanceNotFoundException(Activity.class.getSimpleName(), id));

		activityDao.delete(activity);
	}

	@Override
	public Transport createTransport(Transport transport) {
		transport.setHidden(false);

		return transportDao.save(transport);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transport> findTransports() {
		return transportDao.findAll();
	}

	@Override
	public void removeTransport(Long id) throws InstanceNotFoundException {
		Transport transport = transportDao.findById(id)
				.orElseThrow(() -> new InstanceNotFoundException(Transport.class.getSimpleName(), id));

		transportDao.delete(transport);
	}

	@Override
	public Travel createTravel(Travel travel) {
		travel.setHidden(false);

		return travelDao.save(travel);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Travel> findTravels() {
		return travelDao.findAll();
	}

	@Override
	public void removeTravel(Long id) throws InstanceNotFoundException {
		Travel travel = travelDao.findById(id)
				.orElseThrow(() -> new InstanceNotFoundException(Travel.class.getSimpleName(), id));

		travelDao.delete(travel);
	}
}
