package com.krishig.service.impl;

import com.krishig.dto.req.PaymentReqDto;
import com.krishig.entity.Payment;
import com.krishig.repository.PaymentRepository;
import com.krishig.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(PaymentReqDto paymentReqDto) {
        Payment payment = convertDtoToEntity(paymentReqDto);
        Payment paymentdb = paymentRepository.save(payment);
        return paymentdb;
    }

    private Payment convertDtoToEntity(PaymentReqDto paymentReqDto) {
        Payment payment = new Payment();
        payment.setPaymentType(paymentReqDto.getPaymentType());
        return payment;
    }
}
