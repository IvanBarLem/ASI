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

    // RF4
    @Override
    @Transactional
    public Accommodation createAccommodation(Accommodation accommodation) {
	accommodation.setHidden(false);
	accommodation.setLocation(accommodation.getName());
	return accommodationDao.save(accommodation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Accommodation> findAccommodations() {
	return accommodationDao.findByHiddenFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Accommodation> findAllAccommodations() {
	return accommodationDao.findAll();
    }

    // RF4.1
    @Override
    @Transactional
    public Accommodation updateAccommodation(Accommodation accommodation) throws InstanceNotFoundException {
	Accommodation oldAccommodation = accommodationDao.findById(accommodation.getId()).orElseThrow(
		() -> new InstanceNotFoundException(Accommodation.class.getSimpleName(), accommodation.getId()));

	oldAccommodation.setName(accommodation.getName());
	oldAccommodation.setPrice(accommodation.getPrice());
	oldAccommodation.setHidden(accommodation.getHidden());

	return oldAccommodation;
    }

    // RF4.2
    @Override
    @Transactional
    public void removeAccommodation(Long id) throws InstanceNotFoundException {
	Accommodation accommodation = accommodationDao.findById(id)
		.orElseThrow(() -> new InstanceNotFoundException(Accommodation.class.getSimpleName(), id));

	accommodationDao.delete(accommodation);
    }

    // RF4
    @Override
    @Transactional
    public Activity createActivity(Activity activity) {
	activity.setHidden(false);

	activity.setLocation(activity.getName());
	return activityDao.save(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activity> findActivities() {
	return activityDao.findByHiddenFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activity> findAllActivities() {
	return activityDao.findAll();
    }

    // RF4.1
    @Override
    @Transactional
    public Activity updateActivity(Activity activity) throws InstanceNotFoundException {
	Activity oldActivity = activityDao.findById(activity.getId())
		.orElseThrow(() -> new InstanceNotFoundException(Activity.class.getSimpleName(), activity.getId()));

	oldActivity.setName(activity.getName());
	oldActivity.setPrice(activity.getPrice());
	oldActivity.setHidden(activity.getHidden());

	return oldActivity;
    }

    // RF4.2
    @Override
    @Transactional
    public void removeActivity(Long id) throws InstanceNotFoundException {
	Activity activity = activityDao.findById(id)
		.orElseThrow(() -> new InstanceNotFoundException(Activity.class.getSimpleName(), id));

	activityDao.delete(activity);
    }

    // RF4
    @Override
    @Transactional
    public Transport createTransport(Transport transport) {
	transport.setHidden(false);

	transport.setLocation(transport.getName());
	return transportDao.save(transport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transport> findTransports() {
	return transportDao.findByHiddenFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transport> findAllTransports() {
	return transportDao.findAll();
    }

    // RF4.1
    @Override
    @Transactional
    public Transport updateTransport(Transport transport) throws InstanceNotFoundException {
	Transport oldTransport = transportDao.findById(transport.getId())
		.orElseThrow(() -> new InstanceNotFoundException(Transport.class.getSimpleName(), transport.getId()));

	oldTransport.setName(transport.getName());
	oldTransport.setPrice(transport.getPrice());
	oldTransport.setHidden(transport.getHidden());

	return oldTransport;
    }

    // RF4.2
    @Override
    @Transactional
    public void removeTransport(Long id) throws InstanceNotFoundException {
	Transport transport = transportDao.findById(id)
		.orElseThrow(() -> new InstanceNotFoundException(Transport.class.getSimpleName(), id));

	transportDao.delete(transport);
    }

    // RF4
    @Override
    @Transactional
    public Travel createTravel(Travel travel) {
	travel.setHidden(false);

	travel.setLocation(travel.getName());
	return travelDao.save(travel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Travel> findTravels() {
	return travelDao.findByHiddenFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Travel> findAllTravels() {
	return travelDao.findAll();
    }

    // RF4.1
    @Override
    @Transactional
    public Travel updateTravel(Travel travel) throws InstanceNotFoundException {
	Travel oldTravel = travelDao.findById(travel.getId())
		.orElseThrow(() -> new InstanceNotFoundException(Travel.class.getSimpleName(), travel.getId()));

	oldTravel.setName(travel.getName());
	oldTravel.setPrice(travel.getPrice());
	oldTravel.setHidden(travel.getHidden());

	return oldTravel;
    }

    // RF4.2
    @Override
    @Transactional
    public void removeTravel(Long id) throws InstanceNotFoundException {
	Travel travel = travelDao.findById(id)
		.orElseThrow(() -> new InstanceNotFoundException(Travel.class.getSimpleName(), id));

	travelDao.delete(travel);
    }
}
