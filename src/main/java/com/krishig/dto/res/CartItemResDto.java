package com.krishig.dto.res;

import lombok.Data;

@Data
public class CartItemResDto {

    private Long cartItemId;
    private Long userId;
    private int quantity;
    private String action;
    private Long productId;
    private String ProductName;
    private String description;
    private double actualPrice;
    private int discount;
    private int productQuantity;
    private String imageLink;
}
