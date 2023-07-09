package com.example.likejobs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
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

    @Builder
    public Recruit(String title, Job job, Education education, String career, Company company){
        this.title = title;
        this.job = job;
        this.education = education;
        this.career = career;
        this.company = company;
    }
    public static Recruit toRecruit(Company company){
        Recruit recruit = new Recruit();
        recruit.company = company;
        return recruit;
    }
}