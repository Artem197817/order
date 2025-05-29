package com.artema.order.controllers;

import com.artema.order.model.Customer;
import com.artema.order.model.OrderPayment;
import com.artema.order.model.Payment;
import com.artema.order.services.PaymentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Data
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping("/{id}")
    public OrderPayment findByOrderId(@PathVariable Long id) {
        return paymentService.findByOrderId(id);
}

    @PostMapping("save_payment")
    public String saveEmployee(@RequestBody Payment payment) {
        paymentService.saveOrderPayment(payment);
        return "Payment successfully saved";
    }
}
