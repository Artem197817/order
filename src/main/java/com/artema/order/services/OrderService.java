package com.artema.order.services;

import com.artema.order.model.Order;
import com.artema.order.model.enumerated.Status;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrder();

    Long saveAndGetId(Order order);

    List<Order> findByStatus(Status status);

    Order findOrderById(Long orderId);

    void deleteOrder(long id);
}
