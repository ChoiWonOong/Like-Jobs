package com.example.likejobs.service.company;

import com.example.likejobs.domain.Company;
import com.example.likejobs.dto.company.CompanyResponseDto;
import com.example.likejobs.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyResponseDto findCompanyById(Long memberId) {    //find Entity by id
        return companyRepository.findById(memberId)
                .map(CompanyResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."))
                ;
    }

    public CompanyResponseDto findCompanyByCompanyId(String companyId) {   //find Entity by username
        return companyRepository.findByCompanyId(companyId)
                .map(CompanyResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    public Company findCompanyByCompanyName(String companyName){
        return companyRepository.findByCompanyName(companyName).get();
    }


}