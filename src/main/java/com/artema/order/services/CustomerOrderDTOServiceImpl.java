package com.artema.order.services;

import com.artema.order.model.*;
import com.artema.order.model.enumerated.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerOrderDTOServiceImpl implements CustomerOrderDTOService{

    private final CustomerService customerService;
    private final OrderPaymentService orderPaymentService;
    private final OrderService orderService;

    @Transactional(readOnly = true)
    public CustomerOrderDTO mapToCustomerOrderDTO(Order order) {
        CustomerOrderDTO dto = new CustomerOrderDTO();
        Customer customer = new Customer();
        if(order.getCustomerId() != null){
            customer = customerService.findById(order.getCustomerId());
        }


        if(customer != null) {
            dto.setCustomerId(customer.getId());
            dto.setCustomerName(customer.getName());
            dto.setCustomerLastName(customer.getLastName());
            dto.setCustomerFatherName(customer.getFatherName());
            dto.setCustomerEmail(customer.getEmail());
            dto.setCustomerPhone(customer.getPhone());
            dto.setCustomerComment(customer.getComment());
        }
        // Order info
        dto.setOrderId(order.getId());
        dto.setOrderName(order.getOrderName());
        dto.setOrderPrice(order.getOrderPrice());
        dto.setOrderDescription(order.getOrderDescription());
        dto.setOrderStatus(order.getOrderStatus());

        // Order status history
        dto.setOrderStatusHistory(order.getOrderHistoryList().stream()
                .map(s -> new CustomerOrderDTO.OrderStatusInfo(s.getStatus(), s.getDateOfChange(), s.getComment()))
                .toList());

        // Order files
        dto.setOrderFiles(order.getOrderFiles().stream()
                .map(f -> new CustomerOrderDTO.OrderFileInfo(f.getId(), f.getFileName(), f.getContentType()))
                .toList());

        // Payments
        OrderPayment orderPayment = orderPaymentService.findByOrderId(order.getId());
        if (orderPayment != null){
            CustomerOrderDTO.OrderPaymentInfo orderPaymentInfo = new CustomerOrderDTO.OrderPaymentInfo();
            orderPaymentInfo.setId(orderPayment.getId());
            orderPaymentInfo.setAmount(orderPayment.getAmount());
            orderPaymentInfo.setLastPayment(orderPayment.getLastPayment());
            orderPaymentInfo.setPaymentHistory(orderPayment.getListHistoryPayment());
            dto.setOrderPayment(orderPaymentInfo);
        }

        return dto;
    }


    @Override
    public List<CustomerOrderDTOMini> findByStatus(Status status) {
        List<Order> orderList = orderService.findByStatus(status);
       return orderList.stream()
                .map(this::mapToCustomerOrderDTOMini)
                .toList();
          }

    @Override
    public CustomerOrderDTO findOrderById(Long orderId) {
        Order order = orderService.findOrderById(orderId);
        if(order != null){
            return mapToCustomerOrderDTO(order);
        }
        return null;
    }

    @Override
    public List<CustomerOrderDTOMini> findAllOrder() {
        List<Order> orderList = orderService.findAllOrder();
        return orderList.stream()
                .map(this::mapToCustomerOrderDTOMini)
                .toList();
    }


    @Transactional(readOnly = true)
    public CustomerOrderDTOMini mapToCustomerOrderDTOMini(Order order) {
        CustomerOrderDTOMini dtoMini = new CustomerOrderDTOMini();
        Customer customer = new Customer();
        if(order.getCustomerId() != null){
            customer = customerService.findById(order.getCustomerId());
        }


        if(customer != null) {
            dtoMini.setCustomerName(customer.getName());
            dtoMini.setCustomerEmail(customer.getEmail());
            dtoMini.setCustomerPhone(customer.getPhone());

        }
        // Order info
        dtoMini.setOrderId(order.getId());
        dtoMini.setOrderName(order.getOrderName());
        dtoMini.setOrderDescription(order.getOrderDescription());
        dtoMini.setOrderStatus(order.getOrderStatus().getStatus());


        return dtoMini;
    }

    @Override
    public List<CustomerOrderDTOMini> findOrderByCustomerId(Long id) {
        List<Order> orders = orderService.findOrderByCustomerId(id);
        if(orders != null && orders.size() > 0){
            return orders.stream()
                    .map(this::mapToCustomerOrderDTOMini)
                    .toList();
        }
        return new ArrayList<>();
    }
    }

