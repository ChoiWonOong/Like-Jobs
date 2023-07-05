package com.example.likejobs.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RecruitDto {
    private String companyName; //회사 이름
    private String title; //공고문 제목
    private String job; // 직종(enterprise/design/marketing/engineering/food/educate)
    private String education; //학력(HIGH_SCHOOL/UNIVERSITY/IRRELEVANT)
    private String career; //경력
}
