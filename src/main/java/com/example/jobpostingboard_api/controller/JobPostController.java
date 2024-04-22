package com.example.jobpostingboard_api.controller;



import com.example.jobpostingboard_api.dto.JobPostDto;
import com.example.jobpostingboard_api.entity.JobPost;
import com.example.jobpostingboard_api.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/jobPost")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;



    @PostMapping("/register")
    public ResponseEntity<JobPostDto> createJobPost(@RequestBody JobPostDto jobPostDto){

        return ResponseEntity.ok(jobPostService.createJobPost(jobPostDto));

    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPost>> getAllJobPost(){

        var results = jobPostService.getAll();
        if(results.size()!=0 || results.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(results);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostDto> getJobPostById(@PathVariable("id") int id){
        var result = jobPostService.getPostById(id);
        if(result!=null){
            return  ResponseEntity.ok(result);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobPost(@PathVariable("id") int id){
        var result = jobPostService.deleteJobPost(id);
        if(result!=null){
            return ResponseEntity.ok(result);

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobPostDto> updateJobpost(@RequestBody() JobPostDto jobPostDto, @PathVariable("id") int id){
        var result = jobPostService.updateJobPost(jobPostDto, id);
        if(result!=null){
            return ResponseEntity.ok(result);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



}
