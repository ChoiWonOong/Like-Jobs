package com.example.likejobs.service;

import com.example.likejobs.domain.*;
import com.example.likejobs.dto.resume.CareerDto;
import com.example.likejobs.dto.resume.ResumeRequestDto;
import com.example.likejobs.dto.resume.ResumeResponseDto;
import com.example.likejobs.repository.*;
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
    RecruitRepository recruitRepository;
    CareerRepository careerRepository;
    public ResumeService(ResumeRepository resumeRepository, MemberRepository memberRepository, RecruitRepository recruitRepository, CareerRepository careerRepository){
        this.resumeRepository = resumeRepository;
        this.memberRepository = memberRepository;
        this.recruitRepository = recruitRepository;
        this.careerRepository = careerRepository;
    }
    @Transactional
    public String createResume(ResumeRequestDto requestDto){
        Optional<Member> optionalMember = memberRepository.findById(SecurityUtil.getCurrentMemberId());
        if(optionalMember.isEmpty()){
            return "일치하는 사용자가 존재하지 않습니다.";
        }
        Member member = optionalMember.get();
        Optional<Recruit> optionalRecruit = recruitRepository.findByTitle(requestDto.getRecruitTitle());
        if(optionalRecruit.isEmpty()){
            return "일치하는 회사가 존재하지 않습니다.";
        }
        Recruit recruit = optionalRecruit.get();
        Resume resume = Resume.toResume(member,recruit);

        resumeRepository.save(resume);

        List<CareerDto> careerDtos = requestDto.getCareerList();
        List<Career> careers = new ArrayList<>();

        Resume currentResume = resumeRepository.findByMemberAndRecruit(member, recruit).get();

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
        return member.getResumeList().stream()
                .map(resume -> ResumeResponseDto.toResumeResponseDto(resume))
                .collect(Collectors.toList());
    }

}
