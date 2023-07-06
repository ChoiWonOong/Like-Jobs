package com.example.likejobs.dto.member;

import com.example.likejobs.domain.Authority;
import com.example.likejobs.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull
    private String name;
    @NotNull
    private String gender;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .email(email)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
