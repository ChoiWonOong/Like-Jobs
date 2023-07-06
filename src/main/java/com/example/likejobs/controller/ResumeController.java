package com.example.likejobs.controller;

import com.example.likejobs.dto.resume.ResumeDto;
import com.example.likejobs.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/resume")
public class ResumeController {
    private final ResumeService resumeService;
    @PutMapping("/update")
    public void updateResume(@RequestBody ResumeDto requestDto){
        resumeService.updateResume(requestDto);
    }
    @GetMapping("/")
    public ResumeDto getResume(){
        return resumeService.getResume();
    }

}
