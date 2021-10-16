package es.udc.asiproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.daos.TransportDao;
import es.udc.asiproject.backend.daos.entities.Transport;

@Service
public class TransportServiceImpl implements TransportService {

	@Autowired
	private TransportDao transportDao;

	@Override
	public List<Transport> findTransports() {
		Iterable<Transport> transports = transportDao.findAll();
		List<Transport> transportsList = new ArrayList<Transport>();

		for (Transport t : transports)
			transportsList.add(t);

		return transportsList;
	}
}
