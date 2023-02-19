package com.krishig.service;

import com.krishig.dto.req.OrderReqDto;
import com.krishig.entity.Customer;
import com.krishig.entity.Payment;

public interface OrderService {

    public String saveOrder(OrderReqDto orderReqDto, Customer customer, Payment payment);
}
