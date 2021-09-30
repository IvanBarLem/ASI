package es.udc.asiproject.backend.model.services;

import java.util.List;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.Transport;
import es.udc.asiproject.backend.model.entities.TransportType;

public interface TransportService {
    Transport addTransport(Long transportTypeId, String date, String origin, String destination)
	    throws InstanceNotFoundException, DateBeforeTodayException, BadDateFormatException;

    Block<Transport> findTransports(Long type, String startDate, String endDate, String origin, String destination,
	    int page, int size) throws BadDateFormatException;

    List<TransportType> getAllTransportTypes();
}
