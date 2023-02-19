package com.krishig.controller;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;
import com.krishig.dto.res.CartProductResDto;
import com.krishig.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @PutMapping("/items")
    public ResponseEntity<CartItemResDto> saveCartItems(@RequestBody CartItemsReqDto cartItemsReqDto) {
        CartItemResDto cartItemResDto = cartItemsService.saveCartItems(cartItemsReqDto);
        return ResponseEntity.ok(cartItemResDto);
    }

    @DeleteMapping("/items/{cpId}/{userId}")
    public ResponseEntity<CartItemResDto> DeleteCartItems(@PathVariable("cpId") Long cpId, @PathVariable("userId") Long userId) {
        CartItemResDto cartItemResDto = cartItemsService.deleteCartItems(cpId, userId);
        return ResponseEntity.ok(cartItemResDto);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Integer> getCountFromCart(@PathVariable("userId") Long userId) {
       Integer count = cartItemsService.getCountByUserId(userId);
       return ResponseEntity.ok(count);
    }

    @GetMapping("/product/{userId}")
    public ResponseEntity<CartItemResDto> getCartItemProducts(@PathVariable("userId") Long userId) {
        CartItemResDto cartItemResDtos = cartItemsService.getCartItemProducts(userId);
        return ResponseEntity.ok(cartItemResDtos);
    }

}
