package com.artema.order.repositopies;

import com.artema.order.model.CustomerOrderDTO;
import com.artema.order.model.Order;
import com.artema.order.model.enumerated.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByOrderStatus_Status(Status status);

    Optional<Order> findOrderById(Long orderId);

    List<Order> findOrderByCustomerId (Long id);
    List<Order> findByOrderStatus_StatusIn(List<Status> statuses);

}
