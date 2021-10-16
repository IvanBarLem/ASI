package es.udc.asiproject.backend.daos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.udc.asiproject.backend.daos.entities.Pack;

public interface PackDao extends PagingAndSortingRepository<Pack, Long>, JpaSpecificationExecutor<Pack> {
}
