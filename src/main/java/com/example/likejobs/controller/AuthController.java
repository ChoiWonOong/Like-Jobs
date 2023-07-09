package com.example.likejobs.controller;

import com.example.likejobs.dto.company.CompanyRequestDto;
import com.example.likejobs.dto.company.CompanyResponseDto;
import com.example.likejobs.dto.member.MemberRequestDto;
import com.example.likejobs.dto.member.MemberResponseDto;
import com.example.likejobs.dto.token.TokenDto;
import com.example.likejobs.dto.token.TokenRequestDto;
import com.example.likejobs.jwt.TokenProvider;
import com.example.likejobs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthService authService;

    @PostMapping("/member/signup")
    public ResponseEntity<MemberResponseDto> memberSignup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.memberSignup(memberRequestDto));
    }

    @PostMapping("/member/login")
    public ResponseEntity<TokenDto> memberLogin(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.memberLogin(memberRequestDto));
    }

    @PostMapping("/company/signup")
    public ResponseEntity<CompanyResponseDto> companySignup(@RequestBody CompanyRequestDto companyRequestDto) {
        return ResponseEntity.ok(authService.companySignup(companyRequestDto));
    }

    @PostMapping("/company/login")
    public ResponseEntity<TokenDto> companyLogin(@RequestBody CompanyRequestDto companyRequestDto) {
        return ResponseEntity.ok(authService.companyLogin(companyRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}