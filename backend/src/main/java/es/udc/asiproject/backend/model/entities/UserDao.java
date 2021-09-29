package es.udc.asiproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

}
