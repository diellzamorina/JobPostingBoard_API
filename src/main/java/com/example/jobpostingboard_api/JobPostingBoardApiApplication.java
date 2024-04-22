package com.example.jobpostingboard_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobPostingBoardApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobPostingBoardApiApplication.class, args);
    }

}
