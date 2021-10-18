package es.udc.asiproject.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.backend.persistence.model.Travel;

public interface TravelDao extends JpaRepository<Travel, Long>, JpaSpecificationExecutor<Travel> {
}
