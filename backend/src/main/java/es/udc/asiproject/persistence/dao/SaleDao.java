package es.udc.asiproject.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.Sale;

public interface SaleDao extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {

	Page<Sale> findByAgentId(Long userId, Pageable pageable);

	Page<Sale> findByClientId(Long userId, Pageable pageable);

	@Override
	Page<Sale> findAll(Pageable pageable);
}
