package com.example.likejobs.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recruit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="recruit_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company; //공고문을 작성한 회사

    private String title;
    private Job job;
    private Education education;
    private String career;

}