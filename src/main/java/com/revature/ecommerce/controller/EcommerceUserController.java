package com.revature.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ecommerce.model.EcommerceUser;
import com.revature.ecommerce.service.EcommerceUserService;

@RestController
@RequestMapping("users")
public class EcommerceUserController {
    
    private EcommerceUserService ecommerceUserService;

    @Autowired
    public EcommerceUserController(EcommerceUserService ecommerceUserService){
        this.ecommerceUserService = ecommerceUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody EcommerceUser ecommerceUser){
        return ecommerceUserService.login(ecommerceUser);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EcommerceUser ecommerceUser){
        return ecommerceUserService.register(ecommerceUser);
    }
}
