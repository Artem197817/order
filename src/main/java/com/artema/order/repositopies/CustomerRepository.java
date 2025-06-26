package com.artema.order.repositopies;

import com.artema.order.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByPhoneAndEmail(String phone, String email);

}

