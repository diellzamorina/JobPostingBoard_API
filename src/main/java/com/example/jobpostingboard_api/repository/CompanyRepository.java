package com.example.jobpostingboard_api.repository;

import com.example.jobpostingboard_api.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository  extends JpaRepository<Company, Integer> {

    Company findByUserName(String userName);


}
