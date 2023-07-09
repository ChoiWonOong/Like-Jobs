package com.example.likejobs.controller;

import com.example.likejobs.dto.member.MemberResponseDto;
import com.example.likejobs.util.SecurityUtil;
import com.example.likejobs.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberById() {
        return ResponseEntity.ok(memberService.findMemberById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberResponseDto> findMemberByUsername(@PathVariable String username) {
        return ResponseEntity.ok(memberService.findMemberByUsername(username));
    }
}