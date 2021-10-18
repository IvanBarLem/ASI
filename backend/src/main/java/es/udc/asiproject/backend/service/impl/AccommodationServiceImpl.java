package es.udc.asiproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.backend.persistence.dao.AccommodationDao;
import es.udc.asiproject.backend.persistence.model.Accommodation;
import es.udc.asiproject.backend.service.AccommodationService;

@Service
public class AccommodationServiceImpl implements AccommodationService {
	@Autowired
	private AccommodationDao accommodationDao;

	@Override
	@Transactional(readOnly = true)
	public List<Accommodation> findAccommodations() {
		return accommodationDao.findAll();
	}
}
