package com.example.fyp.Controller;

import com.example.fyp.Model.Buyer;
import com.example.fyp.Model.Orders;
import com.example.fyp.Model.User;
import com.example.fyp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository UserRepo;

    @GetMapping("users")
    public List<User> getAllUser() {
        return UserRepo.findAll();
    }

    @PostMapping("createUser")
    public User createUser(@RequestBody Map<String, String> payload) {
        Integer salary=0;
        String value="null";
       return UserRepo.save(new User(payload.get("userPassword"),payload.get("firstName"),payload.get("lastName"),payload.get("userIc"), payload.get("birthDate"), payload.get("userContact"), payload.get("userAddress1"), payload.get("userAddress2"), salary,value));
    }


    @PostMapping("updateUser")
    public User updateUser(@RequestBody Map<String, String> payload) {
        return UserRepo.findByFirstNameAndLastName(payload.get("firstName"),payload.get("lastName")).map(user -> {
            user.setUserContact(payload.get("userContact"));
            user.setUserAddress1(payload.get("userAddress1"));
            user.setUserAddress2(payload.get("userAddress2"));
            UserRepo.save(user);
            return user;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }


    @PostMapping("searchUser")//login
    public User searchUser(@RequestBody Map<String, String> payload) {
        Optional<User> userOptional = UserRepo.findByUserIcAndUserPassword(payload.get("userIc"), payload.get("userPassword"));
        return userOptional.orElseThrow(() -> new NullPointerException("No Record Found"));
    }


    @PostMapping("currentUser")
    public User searchCurrentUser(@RequestBody Map<String, String> payload) {
        Optional<User> userOptional = UserRepo.findByUserIc(payload.get("userIc"));
        return userOptional.orElseThrow(() ->  new NullPointerException("No record : " + payload.get("firstName")+payload.get("lastName")));
    }

    @GetMapping("Employees")
    public List<User> Employees (){
        List<User> employeeList = new ArrayList<>();
        List<User> list = getAllUser();
        for(User currentUser: list){
            if(currentUser.getUserRole().equals("null")) {
                //employeeList.add(currentUser);
            }else{
                employeeList.add(currentUser);
            }
        }
        return employeeList;
    }

    @GetMapping("Users")
    public List<User> Users() {
        List<User> usersList = new ArrayList<>();
        List<User> list = getAllUser();
        for(User currentUser: list){
            if(currentUser.getUserRole().equals("null")){
                usersList.add(currentUser);
            }
        }
        return usersList;
    }

    @PostMapping("updateUserRole")
    public User updateUserRole(@RequestBody Map<String, String> payload) {
        return UserRepo.findByUserIc(payload.get("userIc")).map(user -> {
            user.setUserRole(payload.get("userRole"));
            UserRepo.save(user);
            return user;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }

    @PostMapping("deleteUser")
    public User deleteUser(@RequestBody Map<String, String> payload) {
        return UserRepo.findByUserIc(payload.get("userIc")).map(user -> {
            UserRepo.delete(user);
            return user;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }
}

