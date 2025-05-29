package com.artema.order.model;

import com.artema.order.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDTO {

    // Customer fields
    private Long customerId;
    private String customerName;
    private String customerLastName;
    private String customerFatherName;
    private String customerEmail;
    private String customerPhone;
    private String customerComment;

    // Order fields
    private Long orderId;
    private String orderName;
    private String orderPrice;
    private String orderDescription;

    // Current order status
    private Status orderStatus;

    // Order status history
    private List<OrderStatusInfo> orderStatusHistory;

    // Order files metadata (excluding file data for performance)
    private List<OrderFileInfo> orderFiles;

    // Order payments
    private OrderPaymentInfo orderPayment;

    // Nested DTO classes inside the main DTO

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderStatusInfo {
        private Status status;
        private LocalDate dateOfChange;
        private String comment;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderFileInfo {
        private Long id;
        private String fileName;
        private String contentType;
        // Excluding byte[] data for response size optimization
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderPaymentInfo {
        private Long id;
        private Payment lastPayment;
        private Double amount;
        private List<Payment> paymentHistory;
    }
}
