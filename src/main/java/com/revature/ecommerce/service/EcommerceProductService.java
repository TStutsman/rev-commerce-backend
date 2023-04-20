package com.revature.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.dao.EcommerceProductDAO;
import com.revature.ecommerce.model.EcommerceProduct;

@Service
public class EcommerceProductService {

    private EcommerceProductDAO ecommerceProductDAO;

    @Autowired
    public EcommerceProductService(EcommerceProductDAO ecommerceProductDAO){
        this.ecommerceProductDAO = ecommerceProductDAO;
    }

    public ResponseEntity<List<EcommerceProduct>> getAllProducts(){
        List<EcommerceProduct> ticketList;
        ticketList = ecommerceProductDAO.findAllOrderByIdDesc();
        return ResponseEntity.status(200).body(ticketList);
    }
}
