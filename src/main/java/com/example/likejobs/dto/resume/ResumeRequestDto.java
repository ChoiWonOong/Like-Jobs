package com.example.likejobs.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeRequestDto {
    private String companyName;
    private List<CareerDto> careerList;
}
