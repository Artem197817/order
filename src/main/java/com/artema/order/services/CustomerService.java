package com.artema.order.services;

import com.artema.order.model.Customer;

import java.util.List;

public interface CustomerService {

    Long saveOrGetId(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();
}
