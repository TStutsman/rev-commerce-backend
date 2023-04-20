package com.revature.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.model.EcommerceUser;

@Repository
public interface EcommerceUserDAO extends JpaRepository<EcommerceUser, Long> {
    @Query("SELECT e FROM EcommerceUser e WHERE LOWER(e.email) = LOWER(:email)")
    Optional<EcommerceUser> findByEmail(String email);

    @Query("SELECT COUNT(e) > 0 FROM EcommerceUser e WHERE LOWER(e.email) = LOWER(:email)")
    boolean existsByEmail(String email);
}
