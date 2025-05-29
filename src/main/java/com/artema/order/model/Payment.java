package com.artema.order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Payment {

    private Double payment;
    private String paymentData;
    private String comment;

    @Transient
    private Long orderId;

}
