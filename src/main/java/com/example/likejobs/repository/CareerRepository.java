package com.example.likejobs.repository;

import com.example.likejobs.domain.Career;
import com.example.likejobs.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {
    Optional<Career> findByResume(Resume resume);
}
