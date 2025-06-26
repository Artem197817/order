package com.artema.order.services;

import com.artema.order.model.CustomerOrderDTO;
import com.artema.order.model.CustomerOrderDTOMini;
import com.artema.order.model.Order;
import com.artema.order.model.enumerated.Status;

import java.util.List;

public interface CustomerOrderDTOService {

    List<CustomerOrderDTOMini> findByStatus(Status status);

    CustomerOrderDTO findOrderById(Long orderId);


    List<CustomerOrderDTOMini> findAllOrder();

    CustomerOrderDTOMini mapToCustomerOrderDTOMini(Order order);

    List<CustomerOrderDTOMini> findOrderByCustomerId(Long id);
}
