package com.krishig.service.impl;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;
import com.krishig.dto.res.CartProductResDto;
import com.krishig.entity.CartItem;
import com.krishig.entity.CartProduct;
import com.krishig.entity.Product;
import com.krishig.entity.User;
import com.krishig.repository.CartItemRepository;
import com.krishig.repository.CartProductRepository;
import com.krishig.repository.ProductRepository;
import com.krishig.repository.UserRepository;
import com.krishig.repository.reposervice.CartProductService;
import com.krishig.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartProductService cartProductService;

    @Override
    public CartItemResDto saveCartItems(CartItemsReqDto cartItemsReqDto) {
        CartItemResDto cartItemResDto = new CartItemResDto();
        CartProduct cartProduct = null;
        Optional<CartProduct> dbCartProduct = cartProductRepository.findByProduct(cartItemsReqDto.getProductId());
        if(dbCartProduct.isPresent()) {
            cartProduct = dbCartProduct.get();
            cartProduct.setQuantity(cartItemsReqDto.getQuantity());
        } else {
            cartProduct = convertDtoToEntity(cartItemsReqDto);
        }
        cartProductRepository.save(cartProduct);
        return cartItemRes(cartItemsReqDto.getUserId());
    }

    @Override
    public Integer getCountByUserId(Long userId) {
        List<CartProduct> cartProductsRes = cartProductService.getProductsFromCart(userId);
        if(cartProductsRes.isEmpty()) {
            return 0;
        }

        return cartProductsRes.size();
    }

    @Override
    public CartItemResDto getCartItemProducts(Long userId) {
        return cartItemRes(userId);
    }

    @Override
    public CartItemResDto deleteCartItems(Long itemId, Long userId) {
        cartProductRepository.deleteCartProduct(itemId);
        return cartItemRes(userId);
    }

    @Override
    public void updateStatus(Long userId) {
        CartItem cartItem = cartItemRepository.findByUserId(userId);
        List<CartProduct> cartProducts = cartProductRepository.findByCartItem(cartItem.getCartId());
        for(CartProduct cartProduct : cartProducts) {
            cartProduct.setStatus("closed");
            cartProductRepository.save(cartProduct);
        }
    }

    private List<CartProductResDto> convertEntityToDto(List<CartProduct> cartProducts) {
        List<CartProductResDto> cartProductResDtos = new ArrayList<>();
        for(CartProduct cartProduct:cartProducts) {
            CartProductResDto cartProductResDto = new CartProductResDto();
            cartProductResDto.setCpId(cartProduct.getCpId());
            cartProductResDto.setQuantity(cartProduct.getQuantity());
            cartProductResDto.setStatus(cartProduct.getStatus());
            cartProductResDto.setProductId(cartProduct.getProduct().getId());
            cartProductResDto.setProductName(cartProduct.getProduct().getProductName());
            cartProductResDto.setActualPrice(cartProduct.getProduct().getActualPrice());
            cartProductResDto.setDiscount(cartProduct.getProduct().getDiscount());
            cartProductResDto.setDiscountPrice(cartProduct.getProduct().getDiscountPrice());
            cartProductResDtos.add(cartProductResDto);
        }
        return cartProductResDtos;
    }

    private CartProduct convertDtoToEntity(CartItemsReqDto cartItemsReqDto) {
        CartProduct cartProduct = new CartProduct();
        CartItem cartItem = cartItemRepository.findByUserId(cartItemsReqDto.getUserId());
        cartProduct.setCartItem(cartItem);
        Optional<Product> product = productRepository.findById(cartItemsReqDto.getProductId());
        if(product.isPresent()) {
            cartProduct.setProduct(product.get());
        }
        cartProduct.setQuantity(cartItemsReqDto.getQuantity());
        cartProduct.setStatus(cartItemsReqDto.getStatus());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateObj= null;
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            dateObj = sdf.parse(sdf.format(new Date()));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        cartProduct.setCreatedDate(dateObj);
        User user = userRepository.findById(cartItemsReqDto.getUserId()).get();
        cartProduct.setCreatedBy(user.getFirstName()+ " " +user.getLastName());
        return cartProduct;
    }

    private CartItemResDto cartItemRes(Long userId) {
        CartItemResDto cartItemResDto = new CartItemResDto();
        List<CartProduct> cartProductsRes = cartProductService.getProductsFromCart(userId);
        List<CartProductResDto> cartProductResDtos = convertEntityToDto(cartProductsRes);
        CartItem cartItem = cartItemRepository.findByUserId(userId);
        cartItemResDto.setUserId(userId);
        cartItemResDto.setCartItemId(cartItem.getCartId());
        cartItemResDto.setCartProductResDtoList(cartProductResDtos);
        cartItemResDto.setCount(getCountByUserId(userId));
        return cartItemResDto;
    }
}
