package com.artema.order.services;

import com.artema.order.model.OrderPayment;

import java.util.Optional;

public interface OrderPaymentService {

    void save(OrderPayment orderPayment);

    OrderPayment findByOrderId (Long orderId);

    void delete(Long id);
}
