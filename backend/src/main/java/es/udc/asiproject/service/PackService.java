package es.udc.asiproject.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@Service
public interface PackService {
    Pack createPack(Pack pack) throws InstanceNotFoundException, InvalidOperationException;

    Page<Pack> findPacks(Integer pageNumber, Integer pageSize);

    Page<Pack> findAllPacks(Integer pageNumber, Integer pageSize);

    Pack updatePack(Pack pack) throws InstanceNotFoundException, InvalidOperationException;

    void toogleHighlightPack(Long packId) throws InstanceNotFoundException;

    void toogleHidePack(Long packId) throws InstanceNotFoundException;

    void removePack(Long id) throws InstanceNotFoundException;
}
