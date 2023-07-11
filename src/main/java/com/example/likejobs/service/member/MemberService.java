package com.example.likejobs.service.member;

import com.example.likejobs.dto.member.MemberResponseDto;
import com.example.likejobs.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto findMemberById(Long memberId) {    //find Entity by id
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public MemberResponseDto findMemberByUsername(String username) {   //find Entity by username
        return memberRepository.findByUsername(username)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }
    /**
    public MemberResponseDto updateMember(MemberUpdateRequestDto updateRequestDto){
        Optional<Member> optionalMember = memberRepository.findById(SecurityUtil.getCurrentMemberId());
        if(optionalMember.isEmpty()){
            Member member = optionalMember.get();
            member.
        }
    }**/
}