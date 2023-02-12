package com.krishig.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductResDto {
    private Long userId;
    private Long cartItemId;
    private Long cpId;
    private Long productId;
    private String productName;
    private double actualPrice;
    private int discount;
    private double discountPrice;
    private String status;
    private int quantity;
}
