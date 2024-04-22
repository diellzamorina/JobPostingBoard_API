package com.example.jobpostingboard_api.repository;

import com.example.jobpostingboard_api.entity.JobPost;
import com.example.jobpostingboard_api.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

    Optional<JobPost> findJobPostsByStatus(JobStatus status);
}
