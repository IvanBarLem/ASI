package es.udc.asiproject.backend.model.services;

import java.util.List;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.Transport;

public interface TransportService {

    List <Transport> findTransports();
    
}
