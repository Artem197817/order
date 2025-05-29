package com.artema.order.services;

import com.artema.order.model.OrderPayment;
import com.artema.order.model.Payment;

public interface PaymentService {

    OrderPayment findByOrderId(Long id);

    void saveOrderPayment(Payment payment);
}
