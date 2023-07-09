package com.example.likejobs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String companyId;
    private String password;
    private Long registNum;
    private String companyName;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Company(String companyId, String password, Long registNum, String companyName, Authority authority) {
        this.companyId = companyId;
        this.password = password;
        this.registNum = registNum;
        this.companyName = companyName;
        this.authority = authority;
    }
    // 회사에서 올린 공고문 리스트
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recruit> recruitList = new ArrayList<>();
}