package com.example.jobpostingboard_api.service;


import com.example.jobpostingboard_api.dto.CompanyRequestDto;
import com.example.jobpostingboard_api.entity.Address;
import com.example.jobpostingboard_api.entity.Company;
import com.example.jobpostingboard_api.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressService addressService;


    public Company register(CompanyRequestDto companyRequestDto) {

            Company company = new Company();
            company.setEmail(companyRequestDto.getEmail());
            company.setName(companyRequestDto.getName());
            company.setUserName(companyRequestDto.getUserName());
            company.setIndustry(companyRequestDto.getIndustry());
            company.setWebsite(companyRequestDto.getWebsite());
            company.setContactNumber(companyRequestDto.getContactNumber());
            company.setLogo(companyRequestDto.getLogo());

            Address address = addressService.findAddressById(companyRequestDto.getAddressID());

            company.setAddress(address);

            companyRepository.save(company);


        return company;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();

    }

    public Company getCompanyById(int id) {

        return  companyRepository.findById(id).orElse(null);

    }

    public CompanyRequestDto updateCompanyById(int id, CompanyRequestDto companyRequestDto) {
        var company = getCompanyById(id);

        company.setName(companyRequestDto.getName());
        company.setUserName(companyRequestDto.getUserName());
        company.setWebsite(companyRequestDto.getWebsite());
        company.setContactNumber(companyRequestDto.getContactNumber());
        company.setIndustry(companyRequestDto.getIndustry());
        company.setLogo(companyRequestDto.getLogo());

        companyRepository.save(company);

        return  companyRequestDto;

    }

    public String deleteCompanyById(int id) {
        var company = getCompanyById(id);
        company.setAddress(null);
        companyRepository.delete(company);
        return "Company deleted succesfully";

    }


    public Company getCompanyByUserName(String username){
        return companyRepository.findByUserName(username);
    }
}
