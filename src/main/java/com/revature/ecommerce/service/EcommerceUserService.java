package com.revature.ecommerce.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.dao.EcommerceUserDAO;
import com.revature.ecommerce.model.EcommerceUser;
import com.revature.ecommerce.model.SessionUser;

@Service
public class EcommerceUserService {
    
    private EcommerceUserDAO ecommerceUserDAO;
    
    @Resource(name = "sessionUser")
    private SessionUser sessionUser;

    @Autowired
    public EcommerceUserService(EcommerceUserDAO ecommerceUserDAO){
        this.ecommerceUserDAO = ecommerceUserDAO;
    }

    public ResponseEntity<String> login(EcommerceUser ecommerceUser){
        if(Boolean.TRUE.equals(ecommerceUserDAO.existsByEmail(ecommerceUser.getEmail()))){
            EcommerceUser user = ecommerceUserDAO.findByEmail(ecommerceUser.getEmail()).get();
            if (user.getPassword().equals(user.getPassword())){
                sessionUser.setUser(user);
                return ResponseEntity.status(200).body("You're Logged In! Welcome");
            } else{
                return ResponseEntity.status(403).body("Invalid Password");
            }
        } else{
            return ResponseEntity.status(404).body("Error - Email Not Recognized");
        }
    }

    public ResponseEntity<String> register(EcommerceUser ecommerceUser){
        // check if email already exists
        if(Boolean.TRUE.equals(ecommerceUserDAO.existsByEmail(ecommerceUser.getEmail()))){
            return ResponseEntity.status(403).body("Email Already Taken");
        }

        // save the employee to the database
        ecommerceUserDAO.save(ecommerceUser);

        return ResponseEntity.status(200).body("You're Registered! Welcome");
    }
}
