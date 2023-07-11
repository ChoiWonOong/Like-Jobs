package com.example.likejobs.dto.resume;

import com.example.likejobs.domain.Resume;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponseDto {
    private Long resumeId;
    private String recruitTitle;
    private String name;

    private String gender;

    private String phoneNumber;

    private String email;

    private String education;

    private String university;

    private String major;
    // 여기까지 자동
    private List<CareerDto> careerList;

    public static ResumeResponseDto toResumeResponseDto(Resume resume){
        ResumeResponseDto responseDto = new ResumeResponseDto();
        responseDto.resumeId = resume.getId();
        responseDto.recruitTitle = resume.getRecruit().getTitle();
        responseDto.name = resume.getName();
        responseDto.gender = resume.getGender();
        responseDto.phoneNumber = resume.getPhoneNumber();
        responseDto.email = resume.getEmail();
        responseDto.education = resume.getEducation();
        responseDto.university = resume.getUniversity();
        responseDto.major = resume.getMajor();
        responseDto.careerList = CareerDto.toCareerDtoList(resume.getCareerList());
        return responseDto;
    }




}
