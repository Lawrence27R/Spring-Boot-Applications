package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Customer;

public interface CustomerService {

    ResponsePageDto<Customer> getAllCustomers(int pageNumber, int pageSize);

    void addCustomer(Customer customer);

    Page<Customer> findByFirstName(String firstName, Pageable pageable);

    Optional<Customer> findCustomerById(int customerId);
}
