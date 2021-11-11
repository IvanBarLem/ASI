package es.udc.asiproject.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.udc.asiproject.persistence.model.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	Page<User> findAll(Pageable pageable);
}
