package es.udc.asiproject.service.impl;

import java.util.Date;
import java.util.Optional;

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

	@Override
	@Transactional
	public Pack createPack(Pack pack) throws InstanceNotFoundException, InvalidOperationException {
		if (pack.getAccommodations().isEmpty() && pack.getActivities().isEmpty() && pack.getTransports().isEmpty()
				&& pack.getTravels().isEmpty()) {
			throw new InvalidOperationException("EmptyPack");
		}

		for (Accommodation accommodation : pack.getAccommodations()) {
			Optional<Accommodation> optional = accommodationDao.findById(accommodation.getId());

			if (optional.isPresent()) {
				accommodation.setName(optional.get().getName());
			} else {
				throw new InstanceNotFoundException(Accommodation.class.getSimpleName(), accommodation.getId());
			}
		}
		for (Activity activity : pack.getActivities()) {
			Optional<Activity> optional = activityDao.findById(activity.getId());

			if (optional.isPresent()) {
				activity.setName(optional.get().getName());
			} else {
				throw new InstanceNotFoundException(Activity.class.getSimpleName(), activity.getId());
			}
		}
		for (Transport transport : pack.getTransports()) {
			Optional<Transport> optional = transportDao.findById(transport.getId());

			if (optional.isPresent()) {
				transport.setName(optional.get().getName());
			} else {
				throw new InstanceNotFoundException(Transport.class.getSimpleName(), transport.getId());
			}
		}
		for (Travel travel : pack.getTravels()) {
			Optional<Travel> optional = travelDao.findById(travel.getId());

			if (optional.isPresent()) {
				travel.setName(optional.get().getName());
			} else {
				throw new InstanceNotFoundException(Travel.class.getSimpleName(), travel.getId());
			}
		}

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
}
