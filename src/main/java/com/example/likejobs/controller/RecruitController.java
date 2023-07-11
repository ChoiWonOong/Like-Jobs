package com.example.likejobs.controller;

import com.example.likejobs.domain.Recruit;
import com.example.likejobs.dto.recruit.RecruitDto;
import com.example.likejobs.service.RecruitService;
import com.example.likejobs.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {
    private final RecruitService recruitService;
    private final CompanyService companyService;
    /**
     * 공고문 등록
     */
    @PostMapping("/new")
    public String addRecruit(@RequestBody RecruitDto dto){
        recruitService.addRecruit(dto);
        return "add recruit!";
    }
    /**
     * 모든 공고문 리스트
     */
    @GetMapping("/")
    public Page<Recruit> findAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Recruit> recruits = recruitService.findAll(pageable);
        return recruits;
    }

    /**
     * 직종(JOB)에 해당되는 공고문 상위 10개 리스트
     */
    @GetMapping("/{job}")
    public Page<Recruit> findByJob(@PathVariable("job") String job,
                                   @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Recruit> recruits = recruitService.findByJob(job, pageable);
        return recruits;
    }
    @GetMapping("/me")
    public List<Recruit> findByCompany(){
        return recruitService.findByCompany();
    }
}
