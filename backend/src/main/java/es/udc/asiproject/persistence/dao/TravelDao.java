package es.udc.asiproject.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Travel;

public interface TravelDao extends JpaRepository<Travel, Long>, JpaSpecificationExecutor<Travel> {
	List<Travel> findByHiddenFalse();
}
