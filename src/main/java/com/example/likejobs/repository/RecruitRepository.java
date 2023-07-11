package com.example.likejobs.repository;

import com.example.likejobs.domain.Job;
import com.example.likejobs.domain.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    Optional<Recruit> findByTitle(String title);
    // 직종으로 공고문 찾기
    Page<Recruit> findByJob(Job job, Pageable pageable);
}
