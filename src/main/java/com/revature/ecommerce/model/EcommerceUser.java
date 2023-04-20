package com.revature.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class EcommerceUser {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String password;

    @JsonIgnore
    @OneToOne
    private EcommerceCart userCart;

    @JsonIgnore
    @OneToMany
    private List<EcommerceProduct> recentlyPurchased;
}
