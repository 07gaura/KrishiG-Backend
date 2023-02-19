package com.krishig.service;

import com.krishig.dto.req.PaymentReqDto;
import com.krishig.entity.Payment;

public interface PaymentService {
    public Payment savePayment(PaymentReqDto paymentReqDto);
}
