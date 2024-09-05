package com.aurionpro.bankapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aurionpro.bankapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmailIdAndPassword(String emailId, String password);

	Optional<User> findByEmailId(String emailId);

	boolean existsByEmailId(String emailId);

	Optional<User> findByUserId(Integer userId);

	@Query("SELECT u FROM User u JOIN u.role r WHERE r.rolename = 'ROLE_CUSTOMER' AND u.firstname LIKE %:firstname%")
	Page<User> findByFirstnameIgnoreCase(String firstname, Pageable pageable);
	
	@Query("SELECT u FROM User u JOIN u.role r WHERE r.rolename = 'ROLE_CUSTOMER' AND u.lastname LIKE %:lastname%")
	Page<User> findByLastnameIgnoreCase(String lastname, Pageable pageable);

	@Query("SELECT u FROM User u JOIN u.role r WHERE r.rolename = 'ROLE_CUSTOMER' AND u.firstname LIKE %:firstname% AND u.lastname LIKE %:lastname%")
	Page<User> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.role r WHERE r.rolename = 'ROLE_CUSTOMER'")
	Page<User> findAll(Pageable pageable);
}
