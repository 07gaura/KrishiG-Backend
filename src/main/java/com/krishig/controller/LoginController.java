package com.krishig.controller;

import com.krishig.dto.req.LoginReqDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getLogin(@RequestBody LoginReqDto loginReqDto) {
        if(loginReqDto.getUserName().equals("deven") && loginReqDto.getPassword().equals("deven")) {
            return "Login Successful!";
        }
        return "Login Failed";
    }
}
