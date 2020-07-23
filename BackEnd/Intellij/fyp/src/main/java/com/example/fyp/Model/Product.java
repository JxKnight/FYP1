package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "products_id",unique = true)
    private String productsId;
    @Column(name = "products_name",unique = true)
    private String productsName;
    @Column(name = "products_description")
    private String productsDescription;
    @Column(name = "products_pack")
    private String productsPack;
    @Column(name = "products_quantity")
    private String productsQuantity;
    @Column(name = "products_price")
    private String productsPrice;
    @Column(name = "products_category")
    private String productsCategory;

    public Product() {
    }

    public Product(String productsCategory, String productsId, String productsName, String productsDescription, String productsPack, String productsQuantity, String productsPrice){
        this.productsCategory = productsCategory;
        this.productsId = productsId;
        this.productsName = productsName;
        this.productsDescription = productsDescription;
        this.productsPack=productsPack;
        this.productsQuantity = productsQuantity;
        this.productsPrice = productsPrice;
    }

    public String getProductsPack() {
        return productsPack;
    }

    public void setProductsPack(String productsPack) {
        this.productsPack = productsPack;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(String productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getProductsCategory() {
        return productsCategory;
    }

    public void setProductsCategory(String productsCategory) {
        this.productsCategory = productsCategory;
    }
}
