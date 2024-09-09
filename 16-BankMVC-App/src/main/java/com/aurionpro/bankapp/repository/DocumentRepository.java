package com.aurionpro.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bankapp.entity.Document;
import com.aurionpro.bankapp.entity.User;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

	List<Document> findByUser(User user);
}
