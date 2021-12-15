package es.udc.asiproject.persistence.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.asiproject.persistence.model.Sale;

public interface SaleDao extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
	@Query("SELECT s FROM Sale s WHERE s.agent.firstName LIKE %:name% OR s.agent.lastName LIKE %:name%")
	Page<Sale> findByAgentName(@Param("name") String name, Pageable pageable);

	@Query("SELECT s FROM Sale s WHERE s.client.firstName LIKE %:name% OR s.client.lastName LIKE %:name%")
	Page<Sale> findByClientName(@Param("name") String name, Pageable pageable);

	@Query("SELECT s FROM Sale s WHERE s.agent.firstName LIKE %:agentName% OR s.agent.lastName LIKE %:agentName% AND "
			+ "s.client.firstName LIKE %:clientName% OR s.client.lastName LIKE %:clientName%")
	Page<Sale> findByAgentNameAndClientName(@Param("agentName") String agentName,
			@Param("clientName") String clientName, Pageable pageable);

	@Override
	Page<Sale> findAll(Pageable pageable);

	@Query("SELECT COUNT(s.id) FROM Sale s WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID "
			+ "AND s.createdAt BETWEEN :startDate AND :endDate")
	Long countSalesByCreatedAt(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT COALESCE(SUM(p.quantity * p.product.price), 0) FROM Sale s JOIN s.products p "
			+ "WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID AND "
			+ "s.createdAt BETWEEN :startDate AND :endDate")
	BigDecimal findBillingByCreatedAt(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT COALESCE(SUM(p.quantity), 0) FROM Sale s JOIN s.products p "
			+ "WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID AND "
			+ "s.createdAt BETWEEN :startDate AND :endDate")
	Long countProductsByCreatedAt(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT COUNT(s.id) FROM Sale s WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID "
			+ "AND s.agent.id = :id AND s.createdAt BETWEEN :startDate AND :endDate")
	Long countSalesByAgentAndCreatedAt(@Param("id") Long id, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT COALESCE(SUM(p.quantity * p.product.price), 0) FROM Sale s JOIN s.products p "
			+ "WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID AND "
			+ "s.agent.id = :id AND s.createdAt BETWEEN :startDate AND :endDate")
	BigDecimal findBillingByAgentAndCreatedAt(@Param("id") Long id, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT COALESCE(SUM(p.quantity), 0) FROM Sale s JOIN s.products p "
			+ "WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID AND "
			+ "p.product.id = :id AND s.createdAt BETWEEN :startDate AND :endDate")
	Long countSalesByProductAndCreatedAt(@Param("id") Long id, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT COALESCE(SUM(p.quantity * p.product.price), 0) FROM Sale s JOIN s.products p "
			+ "WHERE s.state = es.udc.asiproject.persistence.model.enums.SaleState.PAID AND "
			+ "p.product.id = :id AND s.createdAt BETWEEN :startDate AND :endDate")
	BigDecimal findBillingByProductAndCreatedAt(@Param("id") Long id, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}
