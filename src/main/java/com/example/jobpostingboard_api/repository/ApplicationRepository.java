package com.example.jobpostingboard_api.repository;

import com.example.jobpostingboard_api.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
