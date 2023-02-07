package com.krishig.service;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;

import java.util.List;

public interface CartItemsService {
    public String saveCartItems(CartItemsReqDto cartItemsReqDto);
    public Long getCountByUserId(Long userId);
    public List<CartItemResDto> getCartItemProducts(Long userId);
}
