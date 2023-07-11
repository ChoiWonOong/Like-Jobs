package com.example.likejobs.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String education;

    @Column
    private String university;

    @Column
    private String major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company; //공고문을 작성한 회사

    @OneToMany(mappedBy = "resume", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Career> careerList = new ArrayList<>();

    @Builder
    public Resume( String education, String university, String major, Member member, Company company) {
        this.name = member.getName();
        this.gender = member.getGender();
        this.phoneNumber = member.getPhoneNumber();
        this.email = member.getEmail();
        this.education = education;
        this.university = university;
        this.major = major;
        this.member = member;
    }
    public static Resume toResume(Member member, Company company){
        Resume resume = new Resume();
        resume.name = member.getName();
        resume.gender = member.getGender();
        resume.phoneNumber = member.getPhoneNumber();
        resume.email = member.getEmail();
        resume.member = member;
        resume.company = company;
        resume.education = member.getEducation();
        resume.university = member.getUniversity();
        resume.major = member.getMajor();
        return resume;
    }
}
