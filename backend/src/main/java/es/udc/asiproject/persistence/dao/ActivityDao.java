package es.udc.asiproject.persistence.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.asiproject.persistence.model.Activity;

public interface ActivityDao extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
	List<Activity> findByHiddenFalse();

	@Query("SELECT p FROM Activity p WHERE p.location LIKE %:location%")
	Page<Activity> findByLocation(@Param("location") String location, Pageable pageable);
}
