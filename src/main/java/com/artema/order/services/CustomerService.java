package com.artema.order.services;

import com.artema.order.model.Customer;

public interface CustomerService {

    Long saveOrGetId(Customer customer);

    Customer findById(Long id);
}
