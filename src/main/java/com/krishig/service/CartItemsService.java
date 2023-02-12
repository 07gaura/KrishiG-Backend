package com.krishig.service;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;
import com.krishig.dto.res.CartProductResDto;

import java.util.List;

public interface CartItemsService {
    public List<CartProductResDto> saveCartItems(CartItemsReqDto cartItemsReqDto);
    public Integer getCountByUserId(Long userId);
    public List<CartProductResDto> getCartItemProducts(Long userId);
    public void deleteCartItems(Long itemId);
}
