/*
package com.krishig.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String emailId;
    private String mobileNo;
    private String address;
    private String tehsil;
    private String city;
    private String state;
    private String pincode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToMany
    private Product product;
}
*/
