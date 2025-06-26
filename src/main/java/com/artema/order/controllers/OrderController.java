package com.artema.order.controllers;


import com.artema.order.model.CustomerOrderDTO;
import com.artema.order.model.CustomerOrderDTOMini;
import com.artema.order.model.OrderFile;
import com.artema.order.model.enumerated.Status;
import com.artema.order.services.CustomerOrderDTOService;
import com.artema.order.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
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

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        OrderFile fileData = orderService.findOrderFileById(id); // ваш сервис получения файла

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileData.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileData.getFileName() + "\"")
                .body(new ByteArrayResource(fileData.getData()));
    }

    @GetMapping("/orders_customer/{id}")
    public List<CustomerOrderDTOMini> findOrderByCustomerId(@PathVariable Long id) {
        return customerOrderDTOService.findOrderByCustomerId(id);
    }


}