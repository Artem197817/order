package com.artema.order.controllers;


import com.artema.order.model.CustomerOrderDTO;
import com.artema.order.model.CustomerOrderDTOMini;
import com.artema.order.model.enumerated.Status;
import com.artema.order.services.CustomerOrderDTOService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@Data
public class OrderController {

    private final CustomerOrderDTOService customerOrderDTOService;

    @GetMapping("/status/{status}")
    public List<CustomerOrderDTOMini> findByStatus(@PathVariable Status status) {
        return  customerOrderDTOService.findByStatus(status);
    }

    @GetMapping("/{orderId}")
    public CustomerOrderDTO findOrderById(@PathVariable Long orderId) {
        return  customerOrderDTOService.findOrderById(orderId);
    }

    @GetMapping("/")
    public List<CustomerOrderDTOMini> findAllOrder() {
        return  customerOrderDTOService.findAllOrder();
    }
}
