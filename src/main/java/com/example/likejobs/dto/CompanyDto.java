package com.example.likejobs.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
/**
 * 회사 계정으로 회원가입 시에 입력받는 폼
 */
public class CompanyDto {
    private String id;
    private String password;
    private Long registNum;
    private String companyName;
}
