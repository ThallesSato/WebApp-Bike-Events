package com.example.bikeevents.repository;

import com.example.bikeevents.models.Client;
import com.example.bikeevents.models.Ride;
import com.example.bikeevents.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findByRideAndClient(Ride ride, Client client);
    List<Subscription> findAllByRide(Ride ride);
    List<Subscription> findAllByClient(Client client);
    List<Subscription> findAllByRide_IdOrClient_Id(Optional<Integer> ride_Id,Optional<Integer> client_Id);
}
