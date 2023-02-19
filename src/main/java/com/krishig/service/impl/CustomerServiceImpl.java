package com.krishig.service.impl;

import com.krishig.dto.req.CustomerReqDto;
import com.krishig.entity.Customer;
import com.krishig.entity.Product;
import com.krishig.repository.CustomerRepository;
import com.krishig.repository.ProductRepository;
import com.krishig.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Customer saveCustomer(CustomerReqDto customerReqDto) {
        Customer customer = convertDtoToEntity(customerReqDto);
        Customer customerdb = customerRepository.save(customer);
        return customerdb;
    }

    public Customer convertDtoToEntity(CustomerReqDto customerReqDto){
        Customer customer = new Customer();
        Optional<Customer> dbCustomer = customerRepository.findByMobileNo(customerReqDto.getMobileNo());
        if(dbCustomer.isPresent()) {
            customer.setId(dbCustomer.get().getId());
        }
        customer.setFullName(customerReqDto.getFullName());;
        customer.setAddress(customerReqDto.getAddress());
        customer.setMobileNo(customerReqDto.getMobileNo());
        customer.setEmailId(customerReqDto.getEmailId());
        customer.setTehsil(customerReqDto.getTehsil());
        customer.setCity(customerReqDto.getCity());
        customer.setPincode(customerReqDto.getPincode());

        return customer;
    }
}
