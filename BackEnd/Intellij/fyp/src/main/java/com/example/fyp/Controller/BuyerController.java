package com.example.fyp.Controller;

import com.example.fyp.Model.Buyer;
import com.example.fyp.Model.Role;
import com.example.fyp.Repository.BuyerRepository;
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
public class BuyerController {
    @Autowired
    BuyerRepository BuyerRepo;

    @GetMapping("buyers")
    public List<Buyer> getAllBuyers() {
        return BuyerRepo.findAll();
    }

    @PostMapping("createBuyer")
    public void createBuyer(@RequestBody Map<String, String> payload) {
        Integer count=0;
        List<Buyer> list = getAllBuyers();
        for(Buyer countlist: list){
            count++;
        }
        String userCheck="false";
        String buyerId = "buyer"+count;
        BuyerRepo.save(new Buyer(buyerId,payload.get("buyerName"),payload.get("buyerContact"),payload.get("buyerAddress1"),payload.get("buyerAddress2"),payload.get("buyerLocation"),payload.get("buyerRate"),payload.get("userIc"),userCheck));
    }
    @PostMapping("getBuyerByLocation")
    public List<Buyer> getBuyerByLocation(@RequestBody Map<String, String> payload){
        List<Buyer> buyer = BuyerRepo.findByBuyerLocation(payload.get("buyerLocation"));
        return buyer;
    }
    @PostMapping("getBuyerDetails")
    public Buyer getBuyerDetails(@RequestBody Map<String, String> payload){
        Optional<Buyer> buyerOptional = BuyerRepo.findByBuyerLocationAndAndBuyerName(payload.get("buyerLocation"),payload.get("buyerName"));
        return buyerOptional.orElseThrow(() -> new NullPointerException("No Record Found"));
    }

    @GetMapping("buyersUncheck")
    public List<Buyer> getAllBuyersUncheck() {
        List<Buyer> buyer = BuyerRepo.findAll();
        List<Buyer> buyerUncheck = new ArrayList<>();
        for(Buyer currentBuyer: buyer){
            if(currentBuyer.getUserCheck().equals("false")){
                buyerUncheck.add(currentBuyer);
            }
        }
        return buyerUncheck;
    }

    @PostMapping("updateBuyer")
    public Buyer updateBuyer(@RequestBody Map<String, String> payload) {
        return BuyerRepo.findByBuyerId(payload.get("buyerId")).map(buyer -> {
            buyer.setBuyerName(payload.get("buyerName"));
            buyer.setBuyerContact(payload.get("buyerContact"));
            buyer.setBuyerLocation(payload.get("buyerLocation"));
            buyer.setBuyerAddress1(payload.get("buyerAddress1"));
            buyer.setBuyerAddress2(payload.get("buyerAddress2c"));
            buyer.setBuyerRate(payload.get("buyerRate"));
            buyer.setUserCheck(payload.get("userCheck"));
            BuyerRepo.save(buyer);
            return buyer;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }

    @PostMapping("deleteBuyer")
    public Buyer deleteRole(@RequestBody Map<String, String> payload) {
        return BuyerRepo.findByBuyerId(payload.get("buyerId")).map(role -> {
            BuyerRepo.delete(role);
            return role;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }
}
