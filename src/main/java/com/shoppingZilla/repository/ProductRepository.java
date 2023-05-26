package com.shoppingZilla.repository;

import com.shoppingZilla.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
