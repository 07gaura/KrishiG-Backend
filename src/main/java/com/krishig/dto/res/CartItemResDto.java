package com.krishig.dto.res;

import lombok.Data;

import java.util.List;

@Data
public class CartItemResDto {
    private Long cartItemId;
    private Long userId;
    private List<CartProductResDto> cartProductResDtoList;
    private int count;
}
