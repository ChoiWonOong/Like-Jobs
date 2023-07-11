package com.example.likejobs.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeRequestDto {
    private String recruitTitle;
    private List<CareerDto> careerList;
}
