package com.example.fyp.Repository;

import com.example.fyp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByProductsCategory(String productsCategory);
    Optional<Product> findByProductsId(String productsId);
    Optional<Product> findByProductsCategoryAndProductsName(String productsCategory, String productsName);
}
