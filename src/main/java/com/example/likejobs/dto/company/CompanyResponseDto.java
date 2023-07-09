package com.example.likejobs.dto.company;

import com.example.likejobs.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDto {
    private String companyId;
    private String companyName;
    private String authority;
    public static CompanyResponseDto of(Company company) {
        return new CompanyResponseDto(company.getCompanyId(), company.getCompanyName(), company.getAuthority().toString());
    }
}