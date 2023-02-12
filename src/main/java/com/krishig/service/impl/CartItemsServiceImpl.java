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
    public List<CartProductResDto> saveCartItems(CartItemsReqDto cartItemsReqDto) {
        CartProduct cartProduct = null;
        if(cartItemsReqDto.getCpId()!=null) {
            cartProduct = cartProductRepository.findById(cartItemsReqDto.getCpId()).get();
            cartProduct.setQuantity(cartItemsReqDto.getQuantity());
        } else {
            cartProduct = convertDtoToEntity(cartItemsReqDto);
        }
       cartProductRepository.save(cartProduct);
       List<CartProductResDto> cartProductsRes = cartProductService.getProductsFromCart(cartItemsReqDto.getUserId());
       return cartProductsRes;
    }

    @Override
    public Integer getCountByUserId(Long userId) {
        List<CartProductResDto> cartProductsRes = cartProductService.getProductsFromCart(userId);
        if(cartProductsRes.isEmpty()) {
            return 0;
        }

        return cartProductsRes.size();
    }

    @Override
    public List<CartProductResDto> getCartItemProducts(Long userId) {
        List<CartProductResDto> cartProductsRes = cartProductService.getProductsFromCart(userId);
        return cartProductsRes;
    }

    @Override
    public void deleteCartItems(Long itemId) {
        cartProductRepository.deleteCartProduct(itemId);
    }

    private List<CartItemResDto> convertEntityToDto(List<CartItem> cartItems) {
        List<CartItemResDto> cartItemResDtos = new ArrayList<>();
        for(CartItem cartItem:cartItems) {
            CartItemResDto cartItemResDto = new CartItemResDto();
            /*cartItemResDto.setCartItemId(cartItem.getId());
            cartItemResDto.setUserId(cartItem.getUser().getId());
            cartItemResDto.setAction(cartItem.getAction());
            cartItemResDto.setQuantity(cartItem.getQuantity());
            cartItemResDto.setProductId(cartItem.getProduct().getId());
            cartItemResDto.setDescription(cartItem.getProduct().getDescription());
            cartItemResDto.setActualPrice(cartItem.getProduct().getActualPrice());
            cartItemResDto.setDiscount(cartItem.getProduct().getDiscount());
            cartItemResDto.setProductName(cartItem.getProduct().getProductName());*/
            cartItemResDtos.add(cartItemResDto);
        }
        return cartItemResDtos;
    }

    private CartProduct convertDtoToEntity(CartItemsReqDto cartItemsReqDto) {
        CartProduct cartProduct = new CartProduct();
        if(cartItemsReqDto.getCpId() != null) {

        }
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
}
