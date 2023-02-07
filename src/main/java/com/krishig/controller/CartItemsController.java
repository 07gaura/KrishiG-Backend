package com.krishig.controller;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;
import com.krishig.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @PostMapping("/add")
    public ResponseEntity<String> saveCartItems(@RequestBody CartItemsReqDto cartItemsReqDto) {
        String msg = cartItemsService.saveCartItems(cartItemsReqDto);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> getCountFromCart(@PathVariable("userId") Long userId) {
       Long count = cartItemsService.getCountByUserId(userId);
       return ResponseEntity.ok(count);
    }

    @GetMapping("/product/{userId}")
    public ResponseEntity<List<CartItemResDto>> getCartItemProducts(@PathVariable("userId") Long userId) {
        List<CartItemResDto> cartItemResDtos = cartItemsService.getCartItemProducts(userId);
        return ResponseEntity.ok(cartItemResDtos);
    }
}
