package es.udc.asiproject.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Activity;

public interface ActivityDao extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
	List<Activity> findByHiddenFalse();
}
