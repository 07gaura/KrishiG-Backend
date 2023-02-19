package com.krishig.service;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;
import com.krishig.dto.res.CartProductResDto;

import java.util.List;

public interface CartItemsService {
    public CartItemResDto saveCartItems(CartItemsReqDto cartItemsReqDto);
    public Integer getCountByUserId(Long userId);
    public CartItemResDto getCartItemProducts(Long userId);
    public CartItemResDto deleteCartItems(Long itemId, Long userId);
    public void updateStatus(Long userId);
}
