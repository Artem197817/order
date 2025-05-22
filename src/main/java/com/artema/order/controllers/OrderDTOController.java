package com.artema.order.controllers;

import com.artema.order.model.*;
import com.artema.order.model.enumerated.Status;
import com.artema.order.repositopies.CustomerRepository;
import com.artema.order.repositopies.OrderRepository;
import com.artema.order.repositopies.OrderFileRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order/dto")
@Data
@Transactional(readOnly = true)
public class OrderDTOController {

   private final OrderRepository orderRepository;
   private final CustomerRepository customerRepository;
   private final OrderFileRepository orderFileRepository;

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitOrder(
            @RequestPart("data") OrderRequestDTO data,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {

        // Создаём объект User
        Customer customer = new Customer();
        customer.setName(data.getName());
        customer.setPhone(data.getPhone());
        customer.setEmail(data.getEmail());


        // Создаём объект Order
        Order order = new Order();
        order.setOrderDescription(data.getOrderDescription());
        order.setCustomerId(customer.getId());
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatus(Status.NEW);
        orderStatus.setDateOfChange(LocalDate.now());
        order.setOrderStatus(orderStatus);
        List<OrderStatus> orderStatusList = new ArrayList<>();
        orderStatusList.add(orderStatus);
        order.setOrderHistoryList(orderStatusList);


        // Сохраняем пользователя и заказ (через сервис или репозитории)
        customerRepository.save(customer);
        orderRepository.save(order);

        // Обрабатываем файлы, если они есть
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        OrderFile orderFile = new OrderFile();
                        orderFile.setFileName(file.getOriginalFilename());
                        orderFile.setContentType(file.getContentType());
                        orderFile.setData(file.getBytes());
                        orderFile.setOrder(order);

                        orderFileRepository.save(orderFile);
                    } catch (IOException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Ошибка при сохранении файла: " + file.getOriginalFilename());
                    }
                }
            }
        }

        return ResponseEntity.ok("Заказ успешно принят");
    }


}
