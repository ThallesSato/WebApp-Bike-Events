package com.example.bikeevents.controllers;

import com.example.bikeevents.dto.RideDto;
import com.example.bikeevents.models.Ride;
import com.example.bikeevents.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private RideService rideService;


    @GetMapping()
    public ResponseEntity<List<Ride>> getRides(){
        return ResponseEntity.ok(rideService.rideGetAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Ride> getRide(@PathVariable Integer id){
        return ResponseEntity.ok(rideService.rideGetById(id));
    }

    @PostMapping()
    public ResponseEntity<Ride> postRide(@RequestBody RideDto rideDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rideService.ridePost(rideDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Ride> putRide(@PathVariable Integer id, @RequestBody RideDto rideDtoPut){
        return ResponseEntity.ok(rideService.ridePut(id, rideDtoPut));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRide(@PathVariable Integer id){
        rideService.rideDelete(id);
        return ResponseEntity.ok("Ride deleted successful");
    }
}
