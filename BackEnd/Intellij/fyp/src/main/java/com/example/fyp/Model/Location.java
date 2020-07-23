package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "location_Name",unique = true)
    private String locationName;

    public Location() {
    }

    public Location(String locationName){
        this.locationName = locationName;
    }
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
