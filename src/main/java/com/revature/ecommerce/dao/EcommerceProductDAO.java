package com.revature.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.model.EcommerceProduct;

@Repository
public interface EcommerceProductDAO extends JpaRepository<EcommerceProduct, Long> {
    @Query("SELECT p FROM EcommerceProduct p ORDER BY p.id DESC")
    List<EcommerceProduct> findAllOrderByIdDesc();
}
