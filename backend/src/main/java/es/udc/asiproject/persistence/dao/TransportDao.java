package es.udc.asiproject.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Transport;

public interface TransportDao extends JpaRepository<Transport, Long>, JpaSpecificationExecutor<Transport> {
	List<Transport> findByHiddenFalse();
}
