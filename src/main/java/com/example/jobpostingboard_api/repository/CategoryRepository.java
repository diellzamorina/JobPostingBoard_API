package com.example.jobpostingboard_api.repository;

import com.example.jobpostingboard_api.entity.Category;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByname(String name);
}
