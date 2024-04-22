package com.example.jobpostingboard_api.controller;


import com.example.jobpostingboard_api.dto.CompanyRequestDto;
import com.example.jobpostingboard_api.entity.Company;
import com.example.jobpostingboard_api.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyRequestDto companyRequestDto){
        return ResponseEntity.ok(companyService.register(companyRequestDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyByID(@PathVariable("id") int id){

        if(companyService.getCompanyById(id) !=null){
            return ResponseEntity.ok(companyService.getCompanyById(id));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyRequestDto> updateCompanyById(@PathVariable("id") int id, @RequestBody CompanyRequestDto companyRequestDto){
        return ResponseEntity.ok(companyService.updateCompanyById(id, companyRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") int id){
        return ResponseEntity.ok(companyService.deleteCompanyById(id));
    }
}
