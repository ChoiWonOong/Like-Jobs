package com.example.likejobs.service;

import com.example.likejobs.domain.Company;
import com.example.likejobs.domain.Member;
import com.example.likejobs.repository.CompanyRepository;
import com.example.likejobs.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        Optional<Company> optionalCompany = companyRepository.findByCompanyId(username);
        if(optionalCompany.isPresent())
            return optionalCompany.map(this::createUserDetails)
                .orElseThrow();
        else if(optionalMember.isPresent()){
            return optionalMember.map(this::createUserDetails)
                    .orElseThrow(()->new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

        }
            return null;
    }



    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    private UserDetails createUserDetails(Company company) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(company.getAuthority().toString());

        return new User(
                String.valueOf(company.getId()),
                company.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

}