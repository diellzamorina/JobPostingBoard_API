package com.example.jobpostingboard_api.controller;


import com.example.jobpostingboard_api.dto.ApplicationDto;
import com.example.jobpostingboard_api.entity.Application;
import com.example.jobpostingboard_api.service.ApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;


    @PostMapping("/{id}")
    public ResponseEntity<ApplicationDto> apply(HttpServletRequest request, @RequestBody ApplicationDto applicationDto, @PathVariable() int id){
        System.out.println("Hello Application");
        return ResponseEntity.ok(applicationService.apply(request,applicationDto, id));

    }
}
