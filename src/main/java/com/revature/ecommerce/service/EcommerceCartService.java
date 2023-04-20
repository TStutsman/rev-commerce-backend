package com.revature.ecommerce.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.dao.EcommerceProductDAO;
import com.revature.ecommerce.model.EcommerceCart;
import com.revature.ecommerce.model.EcommerceProduct;
import com.revature.ecommerce.model.EcommerceUser;

@Service
public class EcommerceCartService {
    
    private EcommerceProductDAO ecommerceProductDAO;

    @Autowired
    public EcommerceCartService(EcommerceProductDAO ecommerceProductDAO){
        this.ecommerceProductDAO = ecommerceProductDAO;
    }

    public ResponseEntity<EcommerceCart> addToCart(EcommerceProduct ecommerceProduct, EcommerceUser user){
        EcommerceCart userCart = user.getUserCart();
        // Check if the product is already in the cart
        if(userCart.getProductIdQuantities().containsKey(ecommerceProduct.getId())){
            // Update the quantity
            int quantity = userCart.getProductIdQuantities().get(ecommerceProduct.getId());
            userCart.getProductIdQuantities().put(ecommerceProduct.getId(), quantity++);
        } else {
            // Add to cart
            userCart.getProductIdQuantities().put(ecommerceProduct.getId(), 1);
        }

        return ResponseEntity.status(200).body(userCart);
    }

    public ResponseEntity<EcommerceProduct> removeFromCart(EcommerceProduct ecommerceProduct, EcommerceUser user){
        EcommerceCart userCart = user.getUserCart();
        // Check if the product is not in the cart
        if(!userCart.getProductIdQuantities().containsKey(ecommerceProduct.getId())){
            return ResponseEntity.status(400).body(ecommerceProduct);
        }

        // Remove the item from the cart
        userCart.getProductIdQuantities().remove(ecommerceProduct.getId());

        return ResponseEntity.status(200).body(ecommerceProduct);
    }

    public ResponseEntity<EcommerceCart> placeOrder(EcommerceUser user){
        EcommerceCart userCart = user.getUserCart();
        // Get all order products by id and the user's recent purchases list
        Set<Long> orderProductIds = userCart.getProductIdQuantities().keySet();
        List<EcommerceProduct> recentlyPurchased = user.getRecentlyPurchased();

        // Add all purchases to recently purchased
        for(long productId : orderProductIds){
            EcommerceProduct product = ecommerceProductDAO.getReferenceById(productId);
            recentlyPurchased.add(product);
        }

        // Remove products from cart
        userCart.getProductIdQuantities().clear();
        return ResponseEntity.status(200).body(userCart);
    }
}
