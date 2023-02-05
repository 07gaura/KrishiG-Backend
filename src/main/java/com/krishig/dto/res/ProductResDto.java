package com.krishig.dto.res;

import com.krishig.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResDto {
    private Long id;

    private String productName;

    private double actualPrice;

    private int discount;

    private String description;

    private double discountPrice;

    private int quantity;

    private String imageLink;

    private boolean isActive;
}
