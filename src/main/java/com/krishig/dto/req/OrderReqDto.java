package com.krishig.dto.req;

import com.krishig.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {
    private Long userId;
    private CustomerReqDto customerReqDto;
    private PaymentReqDto paymentReqDto;
    private List<Long> productsId;
    private String status;
    private double totalPrice;
}
