package es.udc.asiproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransportDao extends PagingAndSortingRepository<Transport, Long>, CustomizedTransportDao {

}
