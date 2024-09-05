package com.aurionpro.mappings.repository;

import com.aurionpro.mappings.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
