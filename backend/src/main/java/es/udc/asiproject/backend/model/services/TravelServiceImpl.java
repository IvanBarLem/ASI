package es.udc.asiproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.daos.TravelDao;
import es.udc.asiproject.backend.daos.entities.Travel;

@Service
public class TravelServiceImpl implements TravelService {

	@Autowired
	private TravelDao travelDao;

	@Override
	public List<Travel> findTravels() {

		Iterable<Travel> travels = travelDao.findAll();
		List<Travel> travelsList = new ArrayList<Travel>();

		for (Travel t : travels)
			travelsList.add(t);

		return travelsList;
	}

}
