package es.udc.asiproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.backend.persistence.dao.TransportDao;
import es.udc.asiproject.backend.persistence.model.Transport;
import es.udc.asiproject.backend.service.TransportService;

@Service
public class TransportServiceImpl implements TransportService {
	@Autowired
	private TransportDao transportDao;

	@Override
	@Transactional(readOnly = true)
	public List<Transport> findTransports() {
		return transportDao.findAll();
	}
}
