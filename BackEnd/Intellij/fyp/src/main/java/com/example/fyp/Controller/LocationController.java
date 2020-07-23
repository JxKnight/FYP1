package com.example.fyp.Controller;

import com.example.fyp.Model.Location;
import com.example.fyp.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LocationController {
    @Autowired
    LocationRepository LocationRepo;

    @GetMapping("location")
    public List<Location> getAllLocations() {
        return LocationRepo.findAll();
    }

    @PostMapping("createLocation")
    public void createLocation(@RequestBody Map<String, String> payload) {
        LocationRepo.save(new Location(payload.get("locationName")));
    }
}
