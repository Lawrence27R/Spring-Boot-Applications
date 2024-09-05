package com.aurionpro.dboperations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.dboperations.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Page<Client> findByCompanyNameContaining(String companyName, Pageable pageable);
}
