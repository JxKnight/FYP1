package com.example.fyp.Repository;

import com.example.fyp.Model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    List<Buyer> findByBuyerLocation(String buyerLocation);
    Optional<Buyer> findByBuyerId(String buyerId);
    Optional<Buyer> findByBuyerLocationAndAndBuyerName(String buyerLocation, String buyerName);
}
