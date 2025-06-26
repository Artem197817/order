package com.artema.order.services;

import com.artema.order.model.Customer;
import com.artema.order.model.Order;
import com.artema.order.model.enumerated.Status;
import com.artema.order.repositopies.CustomerRepository;
import com.artema.order.repositopies.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;


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

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public String deleteById(Long id) {
        customerRepository.deleteById(id);
        return "Пользователь удален";
    }

    @Override
    public List<Customer> findCustomersByOrderStatuses(List<String> statusNames) {
        List<Status> statuses = statusNames.stream()
                .map(Status::getType)
                .filter(s -> s != Status.NONE)
                .collect(Collectors.toList());

        List<Order> orders = orderRepository.findByOrderStatus_StatusIn(statuses);

        Set<Long> customerIds = orders.stream()
                .map(Order::getCustomerId)
                .collect(Collectors.toSet());

        return customerRepository.findAllById(customerIds);
    }

}
