package com.example.likejobs.service;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Member;
import com.example.likejobs.domain.Recruit;
import com.example.likejobs.domain.Resume;
import com.example.likejobs.dto.company.CompanyRequestDto;
import com.example.likejobs.dto.company.CompanyResponseDto;
import com.example.likejobs.dto.member.MemberRequestDto;
import com.example.likejobs.dto.member.MemberResponseDto;
import com.example.likejobs.dto.token.TokenDto;
import com.example.likejobs.dto.token.TokenRequestDto;
import com.example.likejobs.jwt.RefreshToken;
import com.example.likejobs.jwt.TokenProvider;
import com.example.likejobs.repository.*;
import com.example.likejobs.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final ResumeRepository resumeRepository;
    private final RecruitRepository recruitRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;

    @Transactional
    public MemberResponseDto memberSignup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByUsername(memberRequestDto.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        resumeRepository.save(Resume.toResume(member));
        return MemberResponseDto.of(memberRepository.save(member));
    }
    @Transactional
    public CompanyResponseDto companySignup(CompanyRequestDto companyRequestDto) {
        if (companyRepository.existsByCompanyId(companyRequestDto.getCompanyId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Company company = companyRequestDto.toCompany(passwordEncoder);
        recruitRepository.save(Recruit.toRecruit(company));
        return CompanyResponseDto.of(companyRepository.save(company));
    }

    @Transactional
    public TokenDto memberLogin(MemberRequestDto memberRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        return getToken(authenticationToken);
    }

    @Transactional
    public TokenDto companyLogin(CompanyRequestDto companyRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken = companyRequestDto.toAuthentication();
        return getToken(authenticationToken);
    }
    public TokenDto getToken(UsernamePasswordAuthenticationToken authenticationToken){
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }
    @Transactional
    public String logout(String accessToken, User user){
        String username = user.getUsername();
        refreshTokenRepository.deleteRefreshTokenByKey(username);

        // 레디스에 accessToken 사용못하도록 등록
        redisUtil.setBlackList(accessToken, "accessToken", 30);
        return "logged out";
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
        System.out.println("id : " + authentication.getName());
        System.out.println("authority : " + authentication.getAuthorities());

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}