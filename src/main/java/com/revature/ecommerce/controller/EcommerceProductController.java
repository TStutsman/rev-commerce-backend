package com.revature.ecommerce.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.ecommerce.model.EcommerceCart;
import com.revature.ecommerce.model.EcommerceProduct;
import com.revature.ecommerce.model.SessionUser;
import com.revature.ecommerce.service.EcommerceCartService;
import com.revature.ecommerce.service.EcommerceProductService;

@RestController
@RequestMapping("products")
public class EcommerceProductController {

    @Resource(name = "sessionUser")
    private SessionUser sessionUser;
    
    private EcommerceProductService ecommerceProductService;
    private EcommerceCartService ecommerceCartService;

    @Autowired
    public EcommerceProductController(EcommerceProductService ecommerceProductService, EcommerceCartService ecommerceCartService){
        this.ecommerceProductService = ecommerceProductService;
        this.ecommerceCartService = ecommerceCartService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<EcommerceProduct>> getAllProducts(){
        return ecommerceProductService.getAllProducts();
    }

    @PostMapping("/addToCart")
    public ResponseEntity<EcommerceCart> addToCart(@RequestBody EcommerceProduct ecommerceProduct){
        return ecommerceCartService.addToCart(ecommerceProduct, sessionUser.getUser());
    }

    @DeleteMapping("/removeFromCart")
    public ResponseEntity<EcommerceProduct> removeFromCart(@RequestBody EcommerceProduct ecommerceProduct){
        return ecommerceCartService.removeFromCart(ecommerceProduct, sessionUser.getUser());
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<EcommerceCart> placeOrder(@RequestBody EcommerceCart ecommerceCart){
        return ecommerceCartService.placeOrder(sessionUser.getUser());
    }
}
