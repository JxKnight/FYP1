package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="buyer")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "buyer_id",unique = true)
    private String buyerId;
    @Column(name = "buyer_name",unique = true)
    private String buyerName;
    @Column(name = "buyer_contact")
    private String buyerContact;
    @Column(name = "buyer_Location")
    private String buyerLocation;
    @Column(name = "buyer_address1")
    private String buyerAddress1;
    @Column(name = "buyer_address2")
    private String buyerAddress2;
    @Column(name = "buyer_rate")
    private String buyerRate;
    @Column(name = "user_ic")
    private String userIc;
    @Column(name = "user_check")
    private String userCheck;

    public Buyer() {
    }

    public Buyer(String buyerId, String buyerName, String buyerContact, String buyerLocation, String buyerAddress1, String buyerAddress2, String buyerRate, String userIc, String userCheck) {
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.buyerContact = buyerContact;
        this.buyerLocation = buyerLocation;
        this.buyerAddress1 = buyerAddress1;
        this.buyerAddress2 = buyerAddress2;
        this.buyerRate = buyerRate;
        this.userIc = userIc;
        this.userCheck = userCheck;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerContact() {
        return buyerContact;
    }

    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }


    public String getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate;
    }

    public String getBuyerLocation() {
        return buyerLocation;
    }

    public void setBuyerLocation(String buyerLocation) {
        this.buyerLocation = buyerLocation;
    }

    public String getBuyerAddress1() {
        return buyerAddress1;
    }

    public void setBuyerAddress1(String buyerAddress1) {
        this.buyerAddress1 = buyerAddress1;
    }

    public String getBuyerAddress2() {
        return buyerAddress2;
    }

    public void setBuyerAddress2(String buyerAddress2) {
        this.buyerAddress2 = buyerAddress2;
    }

    public String getUserIc() {
        return userIc;
    }

    public void setUserIc(String userIc) {
        this.userIc = userIc;
    }

    public String getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(String userCheck) {
        this.userCheck = userCheck;
    }
}
