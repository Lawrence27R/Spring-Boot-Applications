package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Customer;

public interface CustomerRepository {

    public List<Customer> getAllCustomers();

    public void addCustomer(Customer customer);

    public void updateCustomer(Customer customer);
}
