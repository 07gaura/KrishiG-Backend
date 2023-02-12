package com.krishig.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsReqDto {
    private Long cpId;
    private Long cartItemId;
    private Long userId;
    private Long productId;
    private int quantity;
    private String status;
}
