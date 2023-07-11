package com.example.likejobs.repository;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Member;
import com.example.likejobs.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByMemberAndCompany(Member member, Company company);
    Optional<Resume> findAllByMember(Member member);
}
