package es.udc.asiproject.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.PackDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.PackService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@Service
public class PackServiceImpl implements PackService {
    @Autowired
    AccommodationDao accommodationDao;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    TransportDao transportDao;
    @Autowired
    TravelDao travelDao;
    @Autowired
    PackDao packDao;

    private void validateProducts(Pack pack) throws InvalidOperationException, InstanceNotFoundException {
	if (pack.getAccommodations().isEmpty() && pack.getActivities().isEmpty() && pack.getTransports().isEmpty()
		&& pack.getTravels().isEmpty()) {
	    throw new InvalidOperationException("EmptyPack");
	}

	Set<Accommodation> accommodations = new HashSet<Accommodation>();
	for (Accommodation accommodation : pack.getAccommodations()) {
	    Optional<Accommodation> optional = accommodationDao.findById(accommodation.getId());

	    if (optional.isPresent()) {
		accommodations.add(optional.get());
	    } else {
		throw new InstanceNotFoundException(Accommodation.class.getSimpleName(), accommodation.getId());
	    }
	}
	pack.setAccommodations(accommodations);

	Set<Activity> activities = new HashSet<Activity>();
	for (Activity activity : pack.getActivities()) {
	    Optional<Activity> optional = activityDao.findById(activity.getId());

	    if (optional.isPresent()) {
		activities.add(optional.get());
	    } else {
		throw new InstanceNotFoundException(Activity.class.getSimpleName(), activity.getId());
	    }
	}
	pack.setActivities(activities);

	Set<Transport> transports = new HashSet<Transport>();
	for (Transport transport : pack.getTransports()) {
	    Optional<Transport> optional = transportDao.findById(transport.getId());

	    if (optional.isPresent()) {
		transports.add(optional.get());
	    } else {
		throw new InstanceNotFoundException(Transport.class.getSimpleName(), transport.getId());
	    }
	}
	pack.setTransports(transports);

	Set<Travel> travels = new HashSet<Travel>();
	for (Travel travel : pack.getTravels()) {
	    Optional<Travel> optional = travelDao.findById(travel.getId());

	    if (optional.isPresent()) {
		travels.add(optional.get());
	    } else {
		throw new InstanceNotFoundException(Travel.class.getSimpleName(), travel.getId());
	    }
	}
	pack.setTravels(travels);
    }

    @Override
    @Transactional
    public Pack createPack(Pack pack) throws InstanceNotFoundException, InvalidOperationException {
	validateProducts(pack);

	pack.setOutstanding(false);
	pack.setHidden(false);
	pack.setCreatedAt(new Date());

	return packDao.save(pack);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pack> findPacks(Integer pageNumber, Integer pageSize) {
	Pageable pageable = PageRequest.of(pageNumber, pageSize, Direction.DESC, "outstanding", "createdAt");

	return packDao.findByHiddenFalse(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pack> findAllPacks(Integer pageNumber, Integer pageSize) {
	Pageable pageable = PageRequest.of(pageNumber, pageSize, Direction.DESC, "outstanding", "createdAt");

	return packDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Pack updatePack(Pack pack) throws InstanceNotFoundException, InvalidOperationException {
	Pack oldPack = packDao.findById(pack.getId())
		.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), pack.getId()));

	validateProducts(pack);

	oldPack.setTitle(pack.getTitle());
	oldPack.setDescription(pack.getDescription());
	oldPack.setImage(pack.getImage());
	oldPack.setPrice(pack.getPrice());
	oldPack.setDuration(pack.getDuration());
	oldPack.setPersons(pack.getPersons());
	oldPack.setOutstanding(pack.getOutstanding());
	oldPack.setHidden(pack.getHidden());
	oldPack.setAccommodations(pack.getAccommodations());
	oldPack.setActivities(pack.getActivities());
	oldPack.setTransports(pack.getTransports());
	oldPack.setTravels(pack.getTravels());

	return oldPack;
    }

    @Override
    public void toggleHighlightPack(Long packId) throws InstanceNotFoundException {
	Pack pack = packDao.findById(packId)
		.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), packId));
	pack.setOutstanding(!pack.getOutstanding());
	packDao.save(pack);

    }

    @Override
    public void toggleHidePack(Long packId) throws InstanceNotFoundException {
	Pack pack = packDao.findById(packId)
		.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), packId));
	pack.setHidden(!pack.getHidden());
	packDao.save(pack);

    }

    @Override
    @Transactional
    public void removePack(Long id) throws InstanceNotFoundException {
	Pack pack = packDao.findById(id)
		.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), id));

	packDao.delete(pack);
    }
}
