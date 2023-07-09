package com.example.likejobs.dto.company;

import com.example.likejobs.domain.Authority;
import com.example.likejobs.domain.Company;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {
    @NotNull
    private String companyId;
    @NotNull
    private String password;
    @NotNull
    private String companyName;
    @NotNull
    private Long registNum;
    public Company toCompany(PasswordEncoder passwordEncoder) {
        return Company.builder()
                .companyId(companyId)
                .password(passwordEncoder.encode(password))
                .companyName(companyName)
                .registNum(registNum)
                .authority(Authority.ROLE_COMPANY)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(companyId, password);
    }
}