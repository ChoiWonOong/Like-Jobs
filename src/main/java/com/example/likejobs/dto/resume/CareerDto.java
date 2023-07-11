package com.example.likejobs.dto.resume;

import com.example.likejobs.domain.Career;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CareerDto {
    private String startdate;
    private String quitdate;
    private String companyname;

    public static CareerDto toCareerDto(Career career){
        CareerDto careerDto = new CareerDto();
        careerDto.companyname = career.getCompanyName();
        careerDto.startdate = career.getStartDate();
        careerDto.quitdate = career.getQuitDate();
        return careerDto;
    }
    public static List<CareerDto> toCareerDtoList(List<Career> careers){
        List<CareerDto> careerDtos = new ArrayList<>();
        for(Career career : careers){
            careerDtos.add(CareerDto.toCareerDto(career));
        }

        return careerDtos;
    }

}
