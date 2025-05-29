package com.artema.order.repositopies;


import com.artema.order.model.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFileRepository extends JpaRepository<OrderFile, Long> {

    List<OrderFile> findByOrderId(Long orderId);
}
