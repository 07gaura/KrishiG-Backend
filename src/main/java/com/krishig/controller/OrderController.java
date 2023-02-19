package com.krishig.controller;

import com.krishig.dto.req.OrderReqDto;
import com.krishig.entity.CartProduct;
import com.krishig.entity.Customer;
import com.krishig.entity.Payment;
import com.krishig.service.CartItemsService;
import com.krishig.service.CustomerService;
import com.krishig.service.OrderService;
import com.krishig.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartItemsService cartItemsService;

    @PostMapping("/book")
    public ResponseEntity<String> bookOrder(@RequestBody OrderReqDto orderReqDto) {
        Customer customer = customerService.saveCustomer(orderReqDto.getCustomerReqDto());
        Payment payment = paymentService.savePayment(orderReqDto.getPaymentReqDto());
        String orderNo = orderService.saveOrder(orderReqDto, customer, payment);
        cartItemsService.updateStatus(orderReqDto.getUserId());
        return ResponseEntity.ok(orderNo);
    }
}
