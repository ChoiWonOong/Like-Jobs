package com.example.likejobs.dto.member;

import com.example.likejobs.domain.Member;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String username;
    private String name;
    private String authority;
    private String gender;
    private String phoneNumber;
    private String email;
    private String education;
    private String university;
    private String major;
    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUsername(), member.getName(), member.getAuthority().toString(), member.getGender(), member.getPhoneNumber(), member.getEmail(), member.getEducation(), member.getUniversity(), member.getMajor());
    }
}