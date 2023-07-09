package com.example.likejobs.repository;

import com.example.likejobs.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // 회사 이름으로 회사 객체 찾기
    Optional<Company> findByCompanyId(String companyId);
    boolean existsByCompanyId(String companyId);
}
