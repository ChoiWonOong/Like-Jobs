package com.example.likejobs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name="company_id")
    private Long id;

    private String companyId;
    private String password;
    private Long registNum;
    private String companyName;

    // 회사에서 올린 공고문 리스트
    @JsonIgnore
    @OneToMany(mappedBy = "Recruit")
    private List<Recruit> recruitList = new ArrayList<>();
}