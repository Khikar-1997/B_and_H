package com.example.demo.persistance.repository;

import com.example.demo.persistance.model.CategoryType;
import com.example.demo.persistance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Long countByCategory_Type(CategoryType type);
}
