package com.artema.order.services;

import com.artema.order.model.Order;
import com.artema.order.model.OrderPayment;
import com.artema.order.model.Payment;
import com.artema.order.repositopies.OrderPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

   private OrderPaymentRepository orderPaymentRepository;
   private OrderService orderService;

   public OrderPayment findByOrderId(Long id){
      Optional<OrderPayment> order = orderPaymentRepository.findByOrderId(id);
      return order.orElse(null);
   }

   @Override
   public void saveOrderPayment(Payment payment) {
       System.out.println(payment);
      if(payment != null){
         OrderPayment orderPayment = findByOrderId(payment.getOrderId());
         if(orderPayment != null){
            orderPayment.setLastPayment(payment);
            orderPayment.setAmount(orderPayment.getAmount() + payment.getPayment());
            List<Payment> paymentList = orderPayment.getListHistoryPayment();
            paymentList.add(payment);
            orderPayment.setListHistoryPayment(paymentList);
            orderPaymentRepository.save(orderPayment);
            return;
         }
        OrderPayment orderPaymentNew = new OrderPayment();
         orderPaymentNew.setOrderId(payment.getOrderId());
         orderPaymentNew.setLastPayment(payment);
         orderPaymentNew.setAmount(payment.getPayment());
         List<Payment> paymentListNew = new ArrayList<>();
         paymentListNew.add(payment);
         orderPaymentNew.setListHistoryPayment(paymentListNew);
         orderPaymentRepository.save(orderPaymentNew);
      }
   }


}
