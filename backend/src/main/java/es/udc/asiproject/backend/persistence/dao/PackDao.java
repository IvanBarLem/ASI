package es.udc.asiproject.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.backend.persistence.model.Pack;

public interface PackDao extends JpaRepository<Pack, Long>, JpaSpecificationExecutor<Pack> {
}
