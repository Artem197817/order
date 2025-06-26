package com.artema.order.services;

import com.artema.order.model.Customer;
import com.artema.order.model.CustomerOrderDTOMini;
import com.artema.order.model.Order;

import java.util.List;

public interface CustomerService {

    Long saveOrGetId(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    String deleteById(Long id);
    List<Customer> findCustomersByOrderStatuses(List<String> statusNames);

}
