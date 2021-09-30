package es.udc.asiproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.Transport;
import es.udc.asiproject.backend.model.entities.TransportDao;
import es.udc.asiproject.backend.model.entities.TransportType;
import es.udc.asiproject.backend.model.entities.TransportTypeDao;

@Service
@Transactional
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportDao transportDao;

    @Autowired
    private TransportTypeDao transportTypeDao;

    @Autowired
    private EntityChecker entityChecker;

    @Override
    public Transport addTransport(Long transportTypeId, String date, String origin, String destination)
	    throws InstanceNotFoundException, DateBeforeTodayException, BadDateFormatException {
	LocalDateTime dateTime = myDateFormat(date);
	if (dateTime.isBefore(LocalDateTime.now())) {
	    throw new DateBeforeTodayException();
	}
	TransportType transportType = entityChecker.checkTransportType(transportTypeId);
	Transport transport = new Transport(transportType, dateTime, origin, destination);
	return transportDao.save(transport);
    }

    @Override
    public Block<Transport> findTransports(Long type, String startDate, String endDate, String origin,
	    String destination, int page, int size) throws BadDateFormatException {
	LocalDateTime startDateTime = myDateFormat(startDate);
	LocalDateTime endDateTime = myDateFormat(endDate);
	Long transportTypeId;
	try {
	    TransportType transportType = entityChecker.checkTransportType(type);
	    transportTypeId = transportType.getId();
	} catch (Exception e) {
	    transportTypeId = null;
	}
	Slice<Transport> slice = transportDao.find(transportTypeId, startDateTime, endDateTime, origin, destination,
		page, size);
	return new Block<>(slice.getContent(), slice.hasNext());
    }

    public LocalDateTime myDateFormat(String date) throws BadDateFormatException {
	LocalDateTime dateTime = null;
	try {
	    if (!date.equals("")) {
		dateTime = LocalDate.from(DateTimeFormatter.ISO_DATE_TIME.parse(date)).atStartOfDay();
	    }
	    return dateTime;
	} catch (Exception e) {
	    throw new BadDateFormatException();
	}
    }

    @Override
    public List<TransportType> getAllTransportTypes() {
	Iterable<TransportType> transports = transportTypeDao.findAll();
	List<TransportType> transportList = new ArrayList<TransportType>();

	for (TransportType transport : transports) {
	    transportList.add(transport);
	}

	return transportList;
    }

}
