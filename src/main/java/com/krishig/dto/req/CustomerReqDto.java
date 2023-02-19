package com.krishig.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReqDto {
    private String fullName;
    private String emailId;
    private String mobileNo;
    private String address;
    private String tehsil;
    private String city;
    private String state;
    private String pincode;
}
