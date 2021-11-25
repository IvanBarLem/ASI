package es.udc.asiproject.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Sale;

public interface SaleDao extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
}
