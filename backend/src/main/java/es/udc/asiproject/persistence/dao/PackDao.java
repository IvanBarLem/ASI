package es.udc.asiproject.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Pack;

public interface PackDao extends JpaRepository<Pack, Long>, JpaSpecificationExecutor<Pack> {
}
