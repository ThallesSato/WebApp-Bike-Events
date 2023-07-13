package com.example.bikeevents.controllers;

import com.example.bikeevents.dto.SubscriptionDto;
import com.example.bikeevents.models.Subscription;
import com.example.bikeevents.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping()
    public ResponseEntity<List<Subscription>> getSubscriptions(
            @RequestParam(name = "ride_id") Optional<Integer> ride,
            @RequestParam(name = "client_id") Optional<Integer> client){
        if (client.isEmpty() && ride.isEmpty()) return ResponseEntity.ok(subscriptionService.subscriptionGetAll());
        return ResponseEntity.ok(subscriptionService.findAllByRide_IdOrClient_Id(ride,client));
    }

    @GetMapping("{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Integer id){
        return ResponseEntity.ok(subscriptionService.subscriptionGetById(id));
    }

    @PostMapping()
    public ResponseEntity<Subscription> postSubscription(@RequestBody SubscriptionDto subscriptionDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.subscriptionPost(subscriptionDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Integer id){
        subscriptionService.subscriptionDelete(id);
        return ResponseEntity.ok("Inscription deleted successful");
    }
}
