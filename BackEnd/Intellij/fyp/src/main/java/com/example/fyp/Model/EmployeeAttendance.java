package com.example.fyp.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "buyer_id",unique = true)
    private String buyerId;
    @Column(name = "buyer_name")
    private String buyerName;
    @Column(name = "buyer_contact")
    private String buyerContact;
    @Column(name = "buyer_address1")
    private String buyerAddress1;
    @Column(name = "buyer_address2")
    private String buyerAddress2;
    @Column(name = "buyer_rate")
    private String buyerRate;
    @Column(name = "user_id")
    private String userId;
}
