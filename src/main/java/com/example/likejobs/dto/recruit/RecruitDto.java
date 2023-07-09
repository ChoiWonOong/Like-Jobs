package com.example.likejobs.dto.recruit;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Education;
import com.example.likejobs.domain.Job;
import com.example.likejobs.domain.Recruit;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
/**
 * 공고문 작성 시에 입력받는 폼
 */
public class RecruitDto {
    private String companyName; //회사 이름
    private String title; //공고문 제목
    private Job job; // 직종(enterprise/design/marketing/engineering/food/educate)
    private Education education; //학력(HIGH_SCHOOL/UNIVERSITY/IRRELEVANT)
    private String career; //경력

    public Recruit toRecruit(Company company){
        return Recruit.builder()
                .company(company)
                .build();
    }
    public static RecruitDto toRecruitDto(Recruit recruit){
        RecruitDto recruitDto =  new RecruitDto();
        recruitDto.setCompanyName(recruit.getCompany().getCompanyName());
        recruitDto.setTitle(recruit.getTitle());
        recruitDto.setJob(recruit.getJob());
        recruitDto.setEducation(recruit.getEducation());
        recruitDto.setCareer(recruit.getCareer());
        return recruitDto;
    }
}
