package com.example.jobpostingboard_api.service;


import com.example.jobpostingboard_api.dto.JobPostDto;
import com.example.jobpostingboard_api.entity.JobPost;
import com.example.jobpostingboard_api.enums.JobStatus;
import com.example.jobpostingboard_api.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final CompanyService companyService;
    private final CategoryService categoryService;


    public JobPostDto createJobPost(JobPostDto jobPostDto){
        JobPost jobPost = new JobPost();
            var company =  companyService.getCompanyByUserName(jobPostDto.getCompanyName());
            var category = categoryService.getCategoryByName(jobPostDto.getCategory());
        jobPost.setDescription(jobPostDto.getDescription());
        jobPost.setLocation(jobPostDto.getLocation());
        jobPost.setTitle(jobPostDto.getTitle());
        jobPost.setSalary(jobPostDto.getSalary());
        jobPost.setStatus(jobPostDto.getStatus());
        jobPost.setEndDate(jobPostDto.getEndDate());
        jobPost.setCompany(company);
        jobPost.setCompanyName(company.getName());
        jobPost.setCategory(category);
        jobPostRepository.save(jobPost);
        return jobPostDto;


    }


    public List<JobPost>    getAll() {
       return jobPostRepository.findAll();
    }


    public JobPost getJobpost(int id){
        return jobPostRepository.findById(id).orElse(null);}


    @Scheduled(cron = "0 0 */48 * * ?")
    public void deleteJobPost(){
       var jobPost = jobPostRepository.findJobPostsByStatus(JobStatus.EXPIRED).orElse(null);
       jobPostRepository.delete(jobPost);

    }

    public JobPostDto getPostById(int id) {
        var jobPost =  jobPostRepository.findById(id).orElse(null);
        JobPostDto jobPostDto = new JobPostDto();
        jobPostDto.setCompanyName(jobPost.getCompanyName());
        jobPostDto.setTitle(jobPost.getTitle());
        jobPostDto.setSalary(jobPost.getSalary());
        jobPostDto.setCategory(jobPost.getCategory().getName());
        jobPostDto.setDescription(jobPost.getDescription());
        jobPostDto.setEndDate(jobPost.getEndDate());
        jobPostDto.setStatus(jobPost.getStatus());
        jobPostDto.setLocation(jobPost.getLocation());
        return jobPostDto;
    }


    public String deleteJobPost(int id){
        var result = getJobpost(id);

       if(result !=null){
           jobPostRepository.deleteById(id);
           return  "Succesfully Deleted";
       }
       else {
           return null;
       }



    }

    public JobPostDto updateJobPost(JobPostDto jobPostDto, int id) {

        JobPost jobPost = getJobpost(id);
        if(jobPost!=null){
            if(jobPostDto.getLocation()!=null){
                jobPost.setLocation(jobPostDto.getLocation());
            }
            if(jobPostDto.getSalary()!=null){
                jobPost.setSalary(jobPostDto.getSalary());
            }
            if(jobPostDto.getTitle()!=null){
                jobPost.setTitle(jobPostDto.getTitle());
            }
            if(jobPostDto.getLocation()!=null){
                jobPost.setLocation(jobPostDto.getLocation());
            }
            if(jobPostDto.getEndDate()!=null){
                jobPost.setEndDate(jobPostDto.getEndDate());
            }
            if(jobPostDto.getStatus()!=null){
                jobPost.setStatus(jobPostDto.getStatus());
            }
            if(jobPostDto.getCompanyName()!=null){
                jobPost.setCompanyName(jobPostDto.getCompanyName());
            }
            jobPostRepository.save(jobPost);
            return jobPostDto;
        }
        else{
            return null;
        }
    }
}
