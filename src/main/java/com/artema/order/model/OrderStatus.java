package com.artema.order.model;

import com.artema.order.model.enumerated.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderStatus {

    private Status status;
    private LocalDate dateOfChange;
    private String comment;

    public OrderStatus(Status status, LocalDate dateOfChange, String comment) {
        this.status = status;
        this.dateOfChange = dateOfChange;
        this.comment = comment;
    }
}
