package com.example.likejobs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column
    private String address;

    @Column
    private String education;

    @Column
    private String career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Resume(String name, String gender, String phoneNumber, String email, String address, String education, String career, Member member) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.education = education;
        this.career = career;
        this.member = member;
    }
    public static Resume toResume(Member member){
        Resume resume = new Resume();
        resume.name = member.getName();
        resume.gender = member.getGender();
        resume.phoneNumber = member.getPhoneNumber();
        resume.email = member.getEmail();
        resume.member = member;
        return resume;
    }
}
