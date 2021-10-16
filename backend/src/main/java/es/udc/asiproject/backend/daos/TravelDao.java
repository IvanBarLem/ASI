package es.udc.asiproject.backend.daos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.udc.asiproject.backend.daos.entities.Travel;

public interface TravelDao extends PagingAndSortingRepository<Travel, Long>, JpaSpecificationExecutor<Travel> {
}
