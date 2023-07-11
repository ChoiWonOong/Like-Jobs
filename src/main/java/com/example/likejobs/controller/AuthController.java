package com.example.likejobs.controller;

import com.example.likejobs.dto.company.CompanyRequestDto;
import com.example.likejobs.dto.company.CompanyResponseDto;
import com.example.likejobs.dto.member.MemberCreateRequestDto;
import com.example.likejobs.dto.member.MemberResponseDto;
import com.example.likejobs.dto.token.TokenDto;
import com.example.likejobs.dto.token.TokenRequestDto;
import com.example.likejobs.jwt.TokenProvider;
import com.example.likejobs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthService authService;
    private final TokenProvider jwtTokenProvider;

    @PostMapping("/member/signup")
    public ResponseEntity<MemberResponseDto> memberSignup(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        return ResponseEntity.ok(authService.memberSignup(memberCreateRequestDto));
    }

    @PostMapping("/member/login")
    public ResponseEntity<TokenDto> memberLogin(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        return ResponseEntity.ok(authService.memberLogin(memberCreateRequestDto));
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

    @PatchMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user, @RequestBody TokenDto tokenDto) {
        System.out.println(user.getAuthorities());
        return ResponseEntity.ok(authService.logout(tokenDto.getAccessToken(), user));
    }
}