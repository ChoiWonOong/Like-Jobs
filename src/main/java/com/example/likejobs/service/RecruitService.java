package com.example.likejobs.service;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Recruit;
import com.example.likejobs.dto.recruit.RecruitDto;
import com.example.likejobs.repository.CompanyRepository;
import com.example.likejobs.repository.RecruitRepository;
import com.example.likejobs.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    public void createRecruit(RecruitDto recruitDto){
        Company company = companyRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        Optional<Recruit> optionalRecruit = recruitRepository.findByCompany(company);
        if(optionalRecruit.isPresent()){
            Recruit recruit = optionalRecruit.get();
            recruit.setTitle(recruitDto.getTitle());
            recruit.setJob(recruitDto.getJob());
            recruit.setEducation(recruitDto.getEducation());
            recruit.setCareer(recruitDto.getCareer());
            recruitRepository.save(recruit);
        }
        else{
            Recruit recruit = recruitDto.toRecruit(company);
            recruitRepository.save(recruit);
        }
    }
}
