package com.example.jobpostingboard_api.repository;

import com.example.jobpostingboard_api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository  extends JpaRepository<Address, Integer> {
    Address findById(int id);
}
