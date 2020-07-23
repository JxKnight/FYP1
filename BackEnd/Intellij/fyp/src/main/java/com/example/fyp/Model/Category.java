package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "categories_name",unique = true)
    private String categoriesName;

    public Category() {
    }

    public Category(String categoriesName){
        this.categoriesName = categoriesName;
    }
    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
}
