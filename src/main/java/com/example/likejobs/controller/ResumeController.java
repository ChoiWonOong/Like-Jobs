package com.example.likejobs.controller;

import com.example.likejobs.dto.resume.ResumeRequestDto;
import com.example.likejobs.dto.resume.ResumeResponseDto;
import com.example.likejobs.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("create")
    public String createResume(@RequestBody ResumeRequestDto requestDto){
        return resumeService.createResume(requestDto);
    }
    /**
    @PutMapping("/update")
    public void updateResume(@RequestBody ResumeResponseDto responseDto){
        resumeService;
    }
     **/
    @GetMapping("/")
    public List<ResumeResponseDto> getResume(){
        return resumeService.getResume();
    }

}
