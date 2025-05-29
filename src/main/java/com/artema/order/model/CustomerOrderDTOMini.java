package com.artema.order.model;

import com.artema.order.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDTOMini {
    // Customer fields

    private String customerName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;

    // Order fields
    private Long orderId;
    private String orderName;
    private String orderDescription;

    // Current order status
    private Status orderStatus;

}
