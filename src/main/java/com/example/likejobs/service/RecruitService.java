package com.example.likejobs.service;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Education;
import com.example.likejobs.domain.Job;
import com.example.likejobs.domain.Recruit;
import com.example.likejobs.dto.recruit.RecruitDto;
import com.example.likejobs.repository.CompanyRepository;
import com.example.likejobs.repository.RecruitRepository;
import com.example.likejobs.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    /**
     * 공고문 등록
     */
    public void addRecruit(RecruitDto dto) {
        Recruit recruit = new Recruit();
        recruit.setTitle(dto.getTitle());
        recruit.setJob(Job.valueOf(dto.getJob()));
        recruit.setEducation(Education.valueOf(dto.getEducation()));
        recruit.setCareer(dto.getCareer());

        // 공고문을 등록한 회사 객체 찾아오기
        Company company = companyRepository.findByCompanyName(dto.getCompanyName()).get();
        recruit.setCompany(company);

        // 회사 객체 저장
        recruitRepository.save(recruit);
    }

    /**
     * 모든 공고문 리스트
     */
    public Page<Recruit> findAll(Pageable pageable) {
        return recruitRepository.findAll(pageable);
    }

    /**
     * 직종(JOB)에 해당하는 공고문 상위 10개 리스트
     */
    public Page<Recruit> findByJob(String job, Pageable pageable) {
        Job strToJob = Job.valueOf(job.toUpperCase());
        Page<Recruit> recruits = recruitRepository.findByJob(strToJob, pageable);
        return recruits;
    }
    public List<Recruit> findByCompany(){
        Company company = companyRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        List<Recruit> recruits = company.getRecruitList();
        return recruits;
    }
    /**
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
     **/
}
