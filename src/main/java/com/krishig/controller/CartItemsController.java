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
    public ResponseEntity<List<CartProductResDto>> saveCartItems(@RequestBody CartItemsReqDto cartItemsReqDto) {
        List<CartProductResDto> lstCartProducts = cartItemsService.saveCartItems(cartItemsReqDto);
        return ResponseEntity.ok(lstCartProducts);
    }

    @DeleteMapping("/items/{cpId}")
    public void DeleteCartItems(@PathVariable("cpId") Long cpId) {
        cartItemsService.deleteCartItems(cpId);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Integer> getCountFromCart(@PathVariable("userId") Long userId) {
       Integer count = cartItemsService.getCountByUserId(userId);
       return ResponseEntity.ok(count);
    }

    @GetMapping("/product/{userId}")
    public ResponseEntity<List<CartProductResDto>> getCartItemProducts(@PathVariable("userId") Long userId) {
        List<CartProductResDto> cartProductResDtos = cartItemsService.getCartItemProducts(userId);
        return ResponseEntity.ok(cartProductResDtos);
    }

}
