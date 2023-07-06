package com.example.likejobs.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {


    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String username, String password, String name, String gender, String phoneNumber, String email, Authority authority) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.authority = authority;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Resume> resumeList = new ArrayList<>();
}
