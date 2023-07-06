package com.example.likejobs.service;

import com.example.likejobs.domain.Member;
import com.example.likejobs.domain.Resume;
import com.example.likejobs.dto.resume.ResumeDto;
import com.example.likejobs.repository.MemberRepository;
import com.example.likejobs.repository.ResumeRepository;
import com.example.likejobs.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {
    ResumeRepository resumeRepository;
    MemberRepository memberRepository;
    public ResumeService(ResumeRepository resumeRepository, MemberRepository memberRepository){
        this.resumeRepository = resumeRepository;
        this.memberRepository = memberRepository;
    }

    public void updateResume(ResumeDto resumeDto){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        Optional<Resume> optionalResume= resumeRepository.findByMember(member);
        if(optionalResume.isPresent()){
            Resume resume = optionalResume.get();
            resume.setName(resumeDto.getName());
            resume.setGender(resumeDto.getGender());
            resume.setPhoneNumber(resumeDto.getPhoneNumber());
            resume.setEmail(resumeDto.getEmail());
            resume.setAddress(resumeDto.getAddress());
            resume.setEducation(resumeDto.getEducation());
            resume.setCareer(resumeDto.getCareer());
            resumeRepository.save(resume);
        }
        else{
            Resume resume = resumeDto.toResume(member);
            resumeRepository.save(resume);
        }

    }
    public ResumeDto getResume(){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        return ResumeDto.toResumeDto(resumeRepository.findByMember(member).get());
    }
}
