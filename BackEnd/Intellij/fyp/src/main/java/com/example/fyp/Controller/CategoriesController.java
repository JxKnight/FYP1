package com.example.fyp.Controller;

import com.example.fyp.Model.Category;
import com.example.fyp.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CategoriesController {
    @Autowired
    CategoryRepository CategoryRepo;

    @GetMapping("category")
    public List<Category> getAllCategory() {
        return CategoryRepo.findAll();
    }

    @PostMapping("createCategory")
    public void createCategory(@RequestBody Map<String, String> payload) {
        CategoryRepo.save(new Category(payload.get("categoriesName")));
    }
}
