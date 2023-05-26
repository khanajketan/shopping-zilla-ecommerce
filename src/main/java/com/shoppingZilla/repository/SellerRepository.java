package com.shoppingZilla.repository;

import com.shoppingZilla.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmailId(String email);
}
