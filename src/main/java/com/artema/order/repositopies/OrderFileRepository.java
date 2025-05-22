package com.artema.order.repositopies;


import com.artema.order.model.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFileRepository extends JpaRepository<OrderFile, Long> {
}
