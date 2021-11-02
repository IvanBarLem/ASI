package es.udc.asiproject.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Accommodation;

public interface AccommodationDao extends JpaRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {
	List<Accommodation> findByHiddenFalse();
}
