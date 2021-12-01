package es.udc.asiproject.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.asiproject.persistence.model.Product;

public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	@Query("SELECT p FROM Product p WHERE p.location LIKE %:location%")
	Page<Product> findByLocation(@Param("location") String location, Pageable pageable);
}
