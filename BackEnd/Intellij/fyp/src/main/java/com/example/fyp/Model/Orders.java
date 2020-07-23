package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "orders_id",unique = true)
    private String ordersId;
    @Column(name = "orders_description")
    private String ordersDescription;
    @Column(name = "orders_date")
    private String ordersDate;
    @Column(name = "buyer_id")
    private String buyerId;
    @Column(name = "products_id")
    private String productsId;
    @Column(name = "products_quantity")
    private String productsQuantity;
    @Column(name = "user_ic")
    private String userIc;

    public Orders() {
    }

    public Orders(String ordersId, String ordersDescription, String ordersDate, String buyerId, String productsId, String productsQuantity, String userIc){
        this.ordersId = ordersId;
        this.ordersDescription = ordersDescription;
        this.ordersDate = ordersDate;
        this.buyerId = buyerId;
        this.productsId = productsId;
        this.productsQuantity = productsQuantity;
        this.userIc = userIc;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrdersDescription() {
        return ordersDescription;
    }

    public void setOrdersDescription(String ordersDescription) {
        this.ordersDescription = ordersDescription;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(String productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public String getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(String ordersDate) {
        this.ordersDate = ordersDate;
    }

    public String getUserIc() {
        return userIc;
    }

    public void setUserIc(String userIc) {
        this.userIc = userIc;
    }
}
