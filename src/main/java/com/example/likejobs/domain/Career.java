package com.example.likejobs.domain;

import com.example.likejobs.dto.resume.CareerDto;
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
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "quit_date")
    private String quitDate;
    @Column(name = "company_name")
    private String companyName;
    @ManyToOne
    @JoinColumn(name="resume_id")
    private Resume resume; // 지원서 id

    public static Career toCareer(CareerDto careerDto, Resume resume){
        Career career = new Career();
        career.setCompanyName(careerDto.getCompanyname());
        career.setStartDate(careerDto.getStartdate());
        career.setQuitDate(careerDto.getQuitdate());
        career.setResume(resume);
        return career;
    }

}
