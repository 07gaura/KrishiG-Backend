package com.krishig.service.impl;

import com.krishig.dto.req.OrderReqDto;
import com.krishig.entity.*;
import com.krishig.repository.OrderRepository;
import com.krishig.repository.ProductRepository;
import com.krishig.repository.UserRepository;
import com.krishig.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String saveOrder(OrderReqDto orderReqDto, Customer customer, Payment payment) {
        Order order = convertDtoToEntity(orderReqDto, customer, payment);
        Order order1 = orderRepository.save(order);
        return order1.getOrderNo();
    }

    private Order convertDtoToEntity(OrderReqDto orderReqDto, Customer customer, Payment payment) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setPayment(payment);
        Optional<User> user = userRepository.findById(orderReqDto.getUserId());
        if (user.isPresent()) {
            order.setUser(user.get());
        }
        order.setOrderNo(generateOrderNumber());
        order.setStatus("inProgress");
        order.setTotalPrice(orderReqDto.getTotalPrice());
        order.setCreateBy(user.get().getFirstName()+" "+user.get().getLastName());
        List<Product> lstProduct = new ArrayList<>();
        for(Long id : orderReqDto.getProductsId()) {
            Optional<Product> productdb = productRepository.findById(id);
            if(productdb.isPresent()) {
                lstProduct.add(productdb.get());
            }
        }
        order.setProducts(lstProduct);
        return order;
    }

    private String generateOrderNumber() {
        Random random = new Random();
        String s = "OR"+random.nextInt(10000);
        return s;
    }
}
