package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "products_id",unique = true)
    private String productsId;
    @Column(name = "products_name",unique = true)
    private String productsName;
    @Column(name = "products_category")
    private String productsCategory;
    @Column(name = "products_quantity")
    private String productsQuantity;

    public Storage() {
    }

    public Storage(String productsCategory, String productsId, String productsName, String productsQuantity){
        this.productsCategory = productsCategory;
        this.productsId = productsId;
        this.productsName = productsName;
        this.productsQuantity = productsQuantity;
    }

    public String getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(String productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public String getProductsCategory() {
        return productsCategory;
    }

    public void setProductsCategory(String productsCategory) {
        this.productsCategory = productsCategory;
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

}
