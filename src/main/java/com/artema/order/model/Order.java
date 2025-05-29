package com.artema.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String orderName;

    private String orderPrice;

    private String orderDescription;

    private OrderStatus orderStatus;

    // Храним историю статусов как список строк или enum
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_status_history", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderStatus> orderHistoryList = new ArrayList<>();

    // Связь с файлами заказа
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderFile> orderFiles = new ArrayList<>();

    // Дополнительные методы (например, для добавления файла)
    public void addOrderFile(OrderFile file) {
        orderFiles.add(file);
        file.setOrder(this);
    }

    public void removeOrderFile(OrderFile file) {
        orderFiles.remove(file);
        file.setOrder(null);
    }
}


