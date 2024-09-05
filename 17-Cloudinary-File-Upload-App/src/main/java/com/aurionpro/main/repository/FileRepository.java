package com.aurionpro.main.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.main.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {
    Optional<File> findById(int fileId);
}
