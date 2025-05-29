package com.artema.order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Payment {

    private Double payment;
    private LocalDate paymentData;
    private String comment;

}
