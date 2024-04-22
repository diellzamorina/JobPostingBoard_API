package com.example.jobpostingboard_api.dto;

import com.example.jobpostingboard_api.entity.Address;
import com.example.jobpostingboard_api.entity.Application;
import com.example.jobpostingboard_api.enums.UserRoles;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;
    private Address address;
    private Set<Application> applications;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
