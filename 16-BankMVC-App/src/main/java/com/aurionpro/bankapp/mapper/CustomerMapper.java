package com.aurionpro.bankapp.mapper;

import com.aurionpro.bankapp.dto.RegistrationDto;
import com.aurionpro.bankapp.entity.User;

public class CustomerMapper {

    public static User toEntity(RegistrationDto registrationDto) {
        if (registrationDto == null) {
            return null;
        }

        User customer = new User();
        customer.setFirstname(registrationDto.getFirstname());
        customer.setLastname(registrationDto.getLastname());
        customer.setEmailId(registrationDto.getEmailId());
        customer.setPassword(registrationDto.getPassword());
//        customer.setRole(registrationDto.getRolename());
        
        return customer;
    }

    public static RegistrationDto toDto(User customer) {
        if (customer == null) {
            return null;
        }

        RegistrationDto customerDto = new RegistrationDto();
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setEmailId(customer.getEmailId());
        customerDto.setPassword(customer.getPassword());
        
        return customerDto;
    }
}
