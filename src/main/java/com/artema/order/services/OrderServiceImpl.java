package com.artema.order.services;

import com.artema.order.model.Order;
import com.artema.order.model.OrderFile;
import com.artema.order.model.OrderPayment;
import com.artema.order.model.enumerated.Status;
import com.artema.order.repositopies.OrderFileRepository;
import com.artema.order.repositopies.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderPaymentService orderPaymentService;
    private final OrderFileRepository orderFileRepository;

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Long saveAndGetId(Order order) {
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return orderRepository.findByOrderStatus_Status(status);
    }

    @Override
    public Order findOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public void deleteOrder(long id) {
        OrderPayment orderPayment = orderPaymentService.findByOrderId(id);
        if(orderPayment != null){
            orderPaymentService.delete(orderPayment.getId());
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderFile findOrderFileById(Long id) {
        return orderFileRepository.findOrderFileById(id);
    }

    @Override
    public String deleteById(long id) {
        Order order = findOrderById(id);
        if(order != null){
            orderRepository.deleteById(id);
        }
        return "Заказ удален";
    }

    @Override
    public List<Order> findOrderByCustomerId(Long id) {
        List<Order> orders = new ArrayList<>();
        orders = orderRepository.findOrderByCustomerId(id);
        return orders;
    }


}
