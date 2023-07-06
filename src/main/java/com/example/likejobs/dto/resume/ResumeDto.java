package com.example.likejobs.dto.resume;

import com.example.likejobs.domain.Member;
import com.example.likejobs.domain.Resume;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private String name;

    private String gender;

    private String phoneNumber;

    private String email;

    private String address;

    private String education;

    private String career;
    public Resume toResume(Member member){
        return Resume.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .education(education)
                .career(career)
                .build();
    }
    public static ResumeDto toResumeDto(Resume resume){
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setName(resume.getName());
        resumeDto.setGender(resume.getGender());
        resumeDto.setPhoneNumber(resume.getPhoneNumber());
        resumeDto.setEmail(resume.getEmail());
        resumeDto.setAddress(resume.getAddress());
        resumeDto.setEducation(resume.getEducation());
        resumeDto.setCareer(resume.getCareer());
        return resumeDto;
    }
}
