package com.krishig.service.impl;

import com.krishig.dto.req.CartItemsReqDto;
import com.krishig.dto.res.CartItemResDto;
import com.krishig.entity.CartItem;
import com.krishig.entity.Product;
import com.krishig.entity.User;
import com.krishig.repository.CartItemRepository;
import com.krishig.repository.ProductRepository;
import com.krishig.repository.UserRepository;
import com.krishig.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public String saveCartItems(CartItemsReqDto cartItemsReqDto) {
       CartItem cartItem = convertDtoToEntity(cartItemsReqDto);
       cartItemRepository.save(cartItem);
       return "Item added in cart";
    }

    @Override
    public Long getCountByUserId(Long userId) {
        Long count = cartItemRepository.findCartItemsCount(userId);
        if(count == null) {
            return 0L;
        }

        return count;
    }

    @Override
    public List<CartItemResDto> getCartItemProducts(Long userId) {
        List<CartItem> cartItem = cartItemRepository.findByUserId(userId);
        List<CartItemResDto> cartItemResDtos = convertEntityToDto(cartItem);
        return cartItemResDtos;
    }

    private List<CartItemResDto> convertEntityToDto(List<CartItem> cartItems) {
        List<CartItemResDto> cartItemResDtos = new ArrayList<>();
        for(CartItem cartItem:cartItems) {
            CartItemResDto cartItemResDto = new CartItemResDto();
            cartItemResDto.setCartItemId(cartItem.getId());
            cartItemResDto.setUserId(cartItem.getUser().getId());
            cartItemResDto.setAction(cartItem.getAction());
            cartItemResDto.setQuantity(cartItem.getQuantity());
            cartItemResDto.setProductId(cartItem.getProduct().getId());
            cartItemResDto.setDescription(cartItem.getProduct().getDescription());
            cartItemResDto.setActualPrice(cartItem.getProduct().getActualPrice());
            cartItemResDto.setDiscount(cartItem.getProduct().getDiscount());
            cartItemResDto.setProductName(cartItem.getProduct().getProductName());
            cartItemResDtos.add(cartItemResDto);
        }
        return cartItemResDtos;
    }

    private CartItem convertDtoToEntity(CartItemsReqDto cartItemsReqDto) {
        CartItem cartItem = new CartItem();
        Optional<User> user = userRepository.findById(cartItemsReqDto.getUserId());
        if(user.isPresent()) {
            cartItem.setUser(user.get());
        }
        Optional<Product> product = productRepository.findById(cartItemsReqDto.getProductId());
        if(product.isPresent()) {
            cartItem.setProduct(product.get());
        }
        cartItem.setQuantity(cartItemsReqDto.getQuantity());
        cartItem.setAction(cartItemsReqDto.getAction());
        return cartItem;
    }
}
