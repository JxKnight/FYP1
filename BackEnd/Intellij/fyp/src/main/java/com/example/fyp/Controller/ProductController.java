package com.example.fyp.Controller;

import com.example.fyp.Model.Buyer;
import com.example.fyp.Model.Product;
import com.example.fyp.Model.Storage;
import com.example.fyp.Repository.ProductRepository;
import com.example.fyp.Repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository ProductRepo;
    @Autowired
    StorageRepository StorageRepo;
    @GetMapping("products")
    public List<Product> getAllProducts() {
        return ProductRepo.findAll();
    }

    @PostMapping("createProduct")
    public Product createProduct(@RequestBody Map<String, String> payload) {
        String productsQuantity="0";
        StorageRepo.save(new Storage(payload.get("productsCategory"),payload.get("productsId"),payload.get("productsName"),productsQuantity));
       return ProductRepo.save(new Product(payload.get("productsCategory"),payload.get("productsId"),payload.get("productsName"),payload.get("productsDescription"),payload.get("productsPack"),payload.get("productsQuantity"),payload.get("productsPrice")));

    }
    @PostMapping("getAllProductByCategory")
    public List<Product> getAllProductByCategory(@RequestBody Map<String, String> payload){
        List<Product> products = ProductRepo.findAllByProductsCategory(payload.get("productsCategory"));
        return products;
    }
    @PostMapping("getProductDetails")
    public Product getProductDetails(@RequestBody Map<String, String> payload){
        Optional<Product> productOptional = ProductRepo.findByProductsCategoryAndProductsName(payload.get("productsCategory"),payload.get("productsName"));
        return productOptional.orElseThrow(() -> new NullPointerException("No Record Found"));
    }

    @PostMapping("deleteProduct")
    public Product deleteProduct(@RequestBody Map<String, String> payload) {
        return ProductRepo.findByProductsId(payload.get("productsId")).map(product -> {
            ProductRepo.delete(product);
            return product;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }
}
