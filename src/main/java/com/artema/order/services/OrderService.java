package com.artema.order.services;

import com.artema.order.model.Order;
import com.artema.order.model.OrderFile;
import com.artema.order.model.enumerated.Status;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrder();

    Long saveAndGetId(Order order);

    List<Order> findByStatus(Status status);

    Order findOrderById(Long orderId);

    void deleteOrder(long id);

    OrderFile findOrderFileById(Long id);

    String deleteById(long id);

    List<Order> findOrderByCustomerId(Long id);
}
