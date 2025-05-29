package com.artema.order.model;

import com.artema.order.model.enumerated.Status;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Embeddable
public class OrderStatus {

    @Enumerated
    private Status status;
    private LocalDate dateOfChange;
    private String comment;

    public OrderStatus(Status status, LocalDate dateOfChange, String comment) {
        this.status = status;
        this.dateOfChange = dateOfChange;
        this.comment = comment;
    }
}
