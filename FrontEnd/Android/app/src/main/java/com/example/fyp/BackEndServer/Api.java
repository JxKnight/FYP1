package com.example.fyp.BackEndServer;

import com.example.fyp.Class.Buyer;
import com.example.fyp.Class.Category;
import com.example.fyp.Class.Location;
import com.example.fyp.Class.Order;
import com.example.fyp.Class.Product;
import com.example.fyp.Class.Role;
import com.example.fyp.Class.Storage;
import com.example.fyp.Class.User;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("/searchUser")
    Call<User> searchUser(@Body User user);

    @POST("/currentUser")
    Call<User> searchCurrentUser(@Body User user);

    @POST("/createUser")
    Call<User> createUser(@Body User user);

    @POST("/updateUser")
    Call<User> updateUser(@Body User user);

    @POST("/deleteUser")
    Call<User> deleteUser(@Body User user);

//    @FormUrlEncoded
//    @POST("/searchUser")
//    Call<User> searchUserr(@Field("uID") String uid,@Field("uPassword") String password);

    @GET("/users")
    Call<List<User>> findAllUser();

    @POST("/employees")
    Call<List<User>> findAllEmployees(@Body User user);

    @GET("/location")
    Call<List<Location>> findAllLocation();


    @POST("/getBuyerDetails")
    Call<Buyer> getBuyerDetail(@Body Buyer buyer);


    @GET("/storage")
    Call<List<Storage>> findAllStorage();

    @POST("/createRoles")
    Call<Role> createRoles(@Body Role role);

    @POST("/updateStorage")
    Call<Storage> updateStorage(@Body Storage storage);

    @GET("/Employees")
    Call<List<User>> findAllEmployees();

    @GET("/Users")
    Call<List<User>> findAllUsers();

    @GET("/roles")
    Call<List<Role>> findAllRoles();


    @POST("/updateUserRole")
    Call<User> updateUserRole(@Body User user);

    @POST("/currentRole")
    Call<Role> getRolePrivileges(@Body Role role);

    @POST("/deleteRole")
    Call<Role> deleteRole(@Body Role role);

    @GET("/buyers")
    Call<List<Buyer>> findAllBuyers();

    @GET("/products")
    Call<List<Product>> findAllProducts();

    @GET("/buyersUncheck")
    Call<List<Buyer>> findAllBuyersUncheck();

    @POST("/deleteBuyer")
    Call<Buyer> deleteBuyer(@Body Buyer buyer);

    @POST("/deleteStorage")
    Call<Storage> deleteWarehouse(@Body Storage storage);

    @POST("/createOrder")
    Call<Order> createOrder(@Body Order order);

    @POST("/createProduct")
    Call<Product> createProduct(@Body Product product);

    @POST("/deleteProduct")
    Call<Product> deleteProduct(@Body Product product);

    @POST("/updateBuyer")
    Call<Buyer> updateBuyer(@Body Buyer buyer);

    @POST("/createBuyer")
    Call<Buyer> createBuyer(@Body Buyer buyer);

}
