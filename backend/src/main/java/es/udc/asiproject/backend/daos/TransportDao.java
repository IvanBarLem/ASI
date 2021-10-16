package es.udc.asiproject.backend.daos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.udc.asiproject.backend.daos.entities.Transport;

public interface TransportDao extends PagingAndSortingRepository<Transport, Long>, JpaSpecificationExecutor<Transport> {
}
