package com.artema.order.repositopies;

import com.artema.order.model.Customer;
import com.artema.order.model.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {

    Optional<OrderPayment> findByOrderId (Long orderId);

}
