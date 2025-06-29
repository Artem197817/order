package com.artema.order.controllers;

import com.artema.order.model.Customer;
import com.artema.order.model.CustomerOrderDTOMini;
import com.artema.order.services.CustomerService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Data
public class CustomersController {


    private final CustomerService customerService;

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @DeleteMapping("/delete_customer/{id}")
    public String deleteCustomer (@PathVariable long id) {
       return customerService.deleteById(id);
    }
    @PostMapping("/filter-by-statuses")
    public ResponseEntity<List<Customer>> findCustomersByOrderStatuses(@RequestBody List<String> statuses) {
        List<Customer> customers = customerService.findCustomersByOrderStatuses(statuses);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/without-orders")
    public List<Customer> findCustomersWithoutOrders() {
        return customerService.findCustomersWithoutOrders();
    }

    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam("q") String query) {
        return customerService.searchCustomersByKeyword(query);
    }
}
