package com.example.demo.controllers;

import com.example.demo.user.UserLogin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    //@CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/api/login")
    public Boolean loginUser(@RequestBody UserLogin loginUser){
        if(loginUser.getUsername().equals("test"))
            return true;
        return false;

    }




}
