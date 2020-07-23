package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name="user_firstname")
    private String firstName;
    @Column(name="user_lastname")
    private String lastName;
    @Column(name="user_ic",unique = true)
    private String userIc;
    @Column (name="user_birthdate")
    private String birthDate;
    @Column(name="user_contact")
    private String userContact;
    @Column(name="user_address1")
    private String userAddress1;
    @Column(name="user_address2")
    private String userAddress2;
    @Column(name="user_totalsalary")
    private Integer userTotalSalary;
    @Column(name="user_Role")
    private String userRole;

    public User() {
    }

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public User(String userPassword, String firstName, String lastName, String userIc, String birthDate, String userContact, String userAddress1, String userAddress2, Integer userTotalSalary, String userRole) {
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userIc = userIc;
        this.birthDate = birthDate;
        this.userContact = userContact;
        this.userAddress1 = userAddress1;
        this.userAddress2 = userAddress2;
        this.userTotalSalary = userTotalSalary;
        this.userRole = userRole;
    }

    public String getUserIc() {
        return userIc;
    }

    public void setUserIc(String userIc) {
        this.userIc = userIc;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserAddress1() {
        return userAddress1;
    }

    public void setUserAddress1(String userAddress1) {
        this.userAddress1 = userAddress1;
    }

    public String getUserAddress2() {
        return userAddress2;
    }

    public void setUserAddress2(String userAddress2) {
        this.userAddress2 = userAddress2;
    }

    public Integer getUserTotalSalary() {
        return userTotalSalary;
    }

    public void setUserTotalSalary(Integer userTotalSalary) {
        this.userTotalSalary = userTotalSalary;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}