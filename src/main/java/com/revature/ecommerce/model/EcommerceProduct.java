package com.revature.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class EcommerceProduct {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @NonNull
    private String name;

    @NonNull
    private BigDecimal price;

    @NonNull
    private String imageUrl;
}
