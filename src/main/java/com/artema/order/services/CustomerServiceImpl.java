package com.artema.order.services;

import com.artema.order.model.Customer;
import com.artema.order.repositopies.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;


    @Override
    public Long saveOrGetId(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByPhoneAndEmail(customer.getPhone(), customer.getEmail());

        if (existingCustomer.isPresent()) {
            // User exists — return existing ID
            return existingCustomer.get().getId();
        } else {
            // User not found — save new user and return new ID
            Customer savedCustomer = customerRepository.save(customer);
            return savedCustomer.getId();
        }

    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }
}
