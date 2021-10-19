package es.udc.asiproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.backend.persistence.dao.TravelDao;
import es.udc.asiproject.backend.persistence.model.Travel;
import es.udc.asiproject.backend.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService {
	@Autowired
	private TravelDao travelDao;

	@Override
	@Transactional(readOnly = true)
	public List<Travel> findTravels() {
		return travelDao.findAll();
	}
}
