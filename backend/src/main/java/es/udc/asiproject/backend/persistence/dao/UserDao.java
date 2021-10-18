package es.udc.asiproject.backend.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.backend.persistence.model.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
}
