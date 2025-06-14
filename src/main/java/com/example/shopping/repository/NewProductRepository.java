package com.example.shopping.repository;

import com.example.shopping.model.NewProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewProductRepository extends JpaRepository<NewProduct, Integer> {
    // Additional query methods can be defined here if needed
}