package com.example.likejobs.repository;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    Optional<Recruit> findByCompany(Company company);
}
