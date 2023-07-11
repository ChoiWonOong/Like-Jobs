package com.example.likejobs.service;

import com.example.likejobs.domain.Career;
import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Member;
import com.example.likejobs.domain.Resume;
import com.example.likejobs.dto.resume.CareerDto;
import com.example.likejobs.dto.resume.ResumeRequestDto;
import com.example.likejobs.dto.resume.ResumeResponseDto;
import com.example.likejobs.repository.CareerRepository;
import com.example.likejobs.repository.CompanyRepository;
import com.example.likejobs.repository.MemberRepository;
import com.example.likejobs.repository.ResumeRepository;
import com.example.likejobs.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    ResumeRepository resumeRepository;
    MemberRepository memberRepository;
    CompanyRepository companyRepository;
    CareerRepository careerRepository;
    public ResumeService(ResumeRepository resumeRepository, MemberRepository memberRepository, CompanyRepository companyRepository, CareerRepository careerRepository){
        this.resumeRepository = resumeRepository;
        this.memberRepository = memberRepository;
        this.companyRepository = companyRepository;
        this.careerRepository = careerRepository;
    }
    @Transactional
    public String createResume(ResumeRequestDto requestDto){
        Optional<Member> optionalMember = memberRepository.findById(SecurityUtil.getCurrentMemberId());
        if(optionalMember.isEmpty()){
            return "일치하는 사용자가 존재하지 않습니다.";
        }
        Member member = optionalMember.get();
        Optional<Company> optionalCompany = companyRepository.findByCompanyName(requestDto.getCompanyName());
        if(optionalCompany.isEmpty()){
            return "일치하는 회사가 존재하지 않습니다.";
        }
        Company company = optionalCompany.get();
        Resume resume = Resume.toResume(member, company);

        resumeRepository.save(resume);

        List<CareerDto> careerDtos = requestDto.getCareerList();
        List<Career> careers = new ArrayList<>();

        Resume currentResume = resumeRepository.findByMemberAndCompany(member, company).get();

        for(CareerDto careerDto : careerDtos ){
            Career career = Career.toCareer(careerDto, currentResume);
            careerRepository.save(career);
            careers.add(career);
        }
        currentResume.setCareerList(careers);
        resumeRepository.save(currentResume);

        return "지원서 작성 완료";
    }
    /**
    public void updateResume(ResumeDto resumeDto){
        Optional<Resume> optionalResume = resumeRepository.findById(resumeDto.getId());
        if(optionalResume.isPresent()){
            Resume resume = optionalResume.get();
            resume.setName(resumeDto.getName());
            resume.setGender(resumeDto.getGender());
            resume.setPhoneNumber(resumeDto.getPhoneNumber());
            resume.setEmail(resumeDto.getEmail());
            resume.setEducation(resumeDto.getEducation());
            resumeRepository.save(resume);
        }
        else{
            Resume resume = resumeDto.toResume(member);
            resumeRepository.save(resume);
        }
    }
     ResumeResponseDto.toResumeResponseDto(
     **/
    public List<ResumeResponseDto> getResume(){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        return resumeRepository.findAllByMember(member).stream()
                .map(resume -> ResumeResponseDto.toResumeResponseDto(resume))
                .collect(Collectors.toList());
    }

}
