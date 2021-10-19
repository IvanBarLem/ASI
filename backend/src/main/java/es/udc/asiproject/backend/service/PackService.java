package es.udc.asiproject.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.udc.asiproject.backend.persistence.model.Pack;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.service.exceptions.InvalidOperationException;

@Service
public interface PackService {
	Pack createPack(Pack pack) throws InstanceNotFoundException, InvalidOperationException;

	Page<Pack> findPacks(Integer pageNumber, Integer pageSize);
}
