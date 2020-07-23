package com.example.fyp.Class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userPassword")
    @Expose
    private String password;
    @SerializedName("userContact")
    @Expose
    private String contact;
    @SerializedName("userIc")
    @Expose
    private String userIc;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userAddress1")
    @Expose
    private String address1;
    @SerializedName("userAddress2")
    @Expose
    private String address2;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("userTotalSalary")
    @Expose
    private Integer salary;
    @SerializedName("userRole")
    @Expose
    private String role;
    public User(String userIc,String password){
        this.userIc = userIc;
        this.password = password;
    }

    public User(String firstName, String lastName, String userIc, String contact) {
        this.contact = contact;
        this.userIc = userIc;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String contact, String userIc, String role) {
        this.contact = contact;
        this.userIc = userIc;
        this.role = role;
    }

    public User(String userIc){
        this.userIc=userIc;
    }
    public User(String firstName, String lastName,String password,String userIc, String contact, String birthDate,String address1,String address2) {
        this.password = password;
        this.contact = contact;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.birthDate = birthDate;
        this.userIc=userIc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIC() {
        return userIc;
    }

    public void setIC(String userIc) {
        this.userIc = userIc;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
