package com.example.fyp.Repository;

import com.example.fyp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserIc(String userIc);
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByUserIcAndUserPassword(String userIc, String userPassword); // remember the sequence, and the pass in parameter have to follow your User class name
}
