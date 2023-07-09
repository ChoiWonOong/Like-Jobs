package com.example.likejobs.controller;

import com.example.likejobs.dto.company.CompanyResponseDto;
import com.example.likejobs.service.company.CompanyService;
import com.example.likejobs.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/me")
    public ResponseEntity<CompanyResponseDto> findMemberById() {
        return ResponseEntity.ok(companyService.findCompanyById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto> findMemberByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(companyService.findCompanyByCompanyId(companyId));
    }
}