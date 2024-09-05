package com.aurionpro.dboperations.service;

import java.util.List;
import com.aurionpro.dboperations.entity.Customer;

public interface CustomerService {

    public List<Customer> getAllCustomers();

    public void addCustomer(Customer customer);

    public void updateCustomer(Customer customer);
}
