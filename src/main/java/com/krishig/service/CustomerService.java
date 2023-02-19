package com.krishig.service;

import com.krishig.dto.req.CustomerReqDto;
import com.krishig.entity.Customer;

public interface CustomerService {
    public Customer saveCustomer(CustomerReqDto customerReqDto);
}
