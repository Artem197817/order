package com.artema.order.services;

import com.artema.order.model.Customer;
import com.artema.order.model.OrderPayment;
import com.artema.order.repositopies.OrderPaymentRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderPaymentServiceImpl implements OrderPaymentService{

    private final OrderPaymentRepository orderPaymentRepository;

    @Override
    public void save(OrderPayment orderPayment) {
        orderPaymentRepository.save(orderPayment);
    }

    @Override
    public OrderPayment findByOrderId(Long orderId) {
        Optional<OrderPayment> orderPayment = orderPaymentRepository.findById(orderId);
        return orderPayment.orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderPaymentRepository.deleteById(id);
    }
}
