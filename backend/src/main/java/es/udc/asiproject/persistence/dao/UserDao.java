package es.udc.asiproject.persistence.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.asiproject.persistence.model.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	@Override
	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.role = es.udc.asiproject.persistence.model.enums.RoleType.AGENTE AND "
			+ "(u.firstName LIKE %:name% OR u.lastName LIKE %:name%)")
	Page<User> findAgentsByName(@Param("name") String name, Pageable pageable);
}
