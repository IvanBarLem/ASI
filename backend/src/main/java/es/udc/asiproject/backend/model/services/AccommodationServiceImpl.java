package es.udc.asiproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.model.entities.Accommodation;
import es.udc.asiproject.backend.model.entities.AccommodationDao;

@Service
public class AccommodationServiceImpl implements AccommodationService {

	@Autowired
	private AccommodationDao accommodationDao;

	@Override
	public List<Accommodation> findAccommodations() {

		Iterable<Accommodation> accommodations = accommodationDao.findAll();
		List<Accommodation> accommodationList = new ArrayList<Accommodation>();

		for (Accommodation accommodation : accommodations) {
			accommodationList.add(accommodation);
		}
		return accommodationList;
	}

}
