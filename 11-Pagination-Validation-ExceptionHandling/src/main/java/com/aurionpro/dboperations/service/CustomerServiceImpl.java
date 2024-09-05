package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Customer;
import com.aurionpro.dboperations.exception.CustomerDoesNotExistException;
import com.aurionpro.dboperations.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponsePageDto<Customer> getAllCustomers(int pageNumber, int PageSize) {
        Pageable pageable = PageRequest.of(pageNumber, PageSize);
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        ResponsePageDto<Customer> customerPageDto = new ResponsePageDto<>();
        customerPageDto.setTotalPages(customerPage.getTotalPages());
        customerPageDto.setTotalElements(customerPage.getTotalElements());
        customerPageDto.setSize(customerPage.getSize());
        customerPageDto.setContents(customerPage.getContent());
        customerPageDto.setLastPage(customerPage.isLast());

        return customerPageDto;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    @Override
    public Page<Customer> findByFirstName(String firstName, Pageable pageable) {
        Page<Customer> customers = customerRepository.findByFirstName(firstName, pageable);
        if (!customers.isEmpty()) {
            throw new CustomerDoesNotExistException("No customers found with the first name: " + firstName);
        }
        return customers;
    }


    @Override
    public Optional<Customer> findCustomerById(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerDoesNotExistException("Customer with ID " + customerId + " does not exist.");
        }
        return customer;
    }
}
