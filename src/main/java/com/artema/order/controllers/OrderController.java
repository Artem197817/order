package com.artema.order.controllers;


import com.artema.order.model.CustomerOrderDTO;
import com.artema.order.model.CustomerOrderDTOMini;
import com.artema.order.model.enumerated.Status;
import com.artema.order.services.CustomerOrderDTOService;
import com.artema.order.services.OrderService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Data
public class OrderController {

    private final CustomerOrderDTOService customerOrderDTOService;
    private final OrderService orderService;

    @GetMapping("/status/{status}")
    public List<CustomerOrderDTOMini> findByStatus(@PathVariable Status status) {
        return customerOrderDTOService.findByStatus(status);
    }

    @GetMapping("/{orderId}")
    public CustomerOrderDTO findOrderById(@PathVariable Long orderId) {
        return customerOrderDTOService.findOrderById(orderId);
    }

    @GetMapping("/")
    public List<CustomerOrderDTOMini> findAllOrder() {
        return customerOrderDTOService.findAllOrder();
    }

    @DeleteMapping("/delete_order/{id}")
    public void deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
    }


}