package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.data.entities.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save((customer));
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
