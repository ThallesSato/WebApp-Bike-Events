package com.example.bikeevents.services;

import com.example.bikeevents.dto.SubscriptionDto;
import com.example.bikeevents.exception.CustomExceptions;
import com.example.bikeevents.models.Client;
import com.example.bikeevents.models.Ride;
import com.example.bikeevents.models.Subscription;
import com.example.bikeevents.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private RideService rideService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;


    public List<Subscription> subscriptionGetAll() {
        return subscriptionRepository.findAll();
    }
    public Subscription subscriptionGetById(int id){
        return subscriptionRepository.findById(id).orElseThrow(() -> new CustomExceptions.NotFoundException("Inscription not found with Id " + id));
    }

    public Subscription subscriptionPost(SubscriptionDto subscriptionDto){

        Client client;
        Ride ride;
        Date date = new Date();

        try {
            client = clientService.clientGetById(subscriptionDto.getClient_id());
        } catch (CustomExceptions.NotFoundException e){
            throw new CustomExceptions.BadRequestException("Client does not exist");
        }

        try {
            ride = rideService.rideGetById(subscriptionDto.getRide_id());
        } catch (CustomExceptions.NotFoundException e){
            throw new CustomExceptions.BadRequestException("Ride does not exist");
        }

        if (subscriptionRepository.findByRideAndClient(ride,client).isPresent())
            throw new CustomExceptions.BadRequestException("Inscription already exists");
        if (date.after(ride.getEnd_date_registration()) || date.before(ride.getStart_date_registration()))
            throw new CustomExceptions.BadRequestException("Registration closed");
        if (ride.getSubscriptions().size() >= ride.getParticipants_limit() && ride.getParticipants_limit() != -1)
            throw new CustomExceptions.BadRequestException("Subscription limit reached");

        Subscription subscription = new Subscription();

        subscription.setRide(ride);
        subscription.setClient(client);
        subscription.setSubscription_date(date);

        try {
//        isOwner(subscription);
        } catch (CustomExceptions.OwnershipException e){
            throw new CustomExceptions.BadRequestException("You cant subscribe for another one");
        }

        return subscriptionRepository.save(subscription);
    }

    public void subscriptionDelete(int id){
        Subscription subscription = subscriptionGetById(id);
//        isOwner(subscription);
        subscriptionRepository.delete(subscription);
    }

    public void subscriptionRideDelete(Ride ride){
        subscriptionRepository.deleteAll(subscriptionRepository.findAllByRide(ride));
    }

    public void subscriptionClientDelete(Client client){
        subscriptionRepository.deleteAll(subscriptionRepository.findAllByClient(client));
    }


    public void isOwner(Subscription subscription) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String owner = subscription.getClient().getUsername();

        if(!userDetails.getUsername().equals(owner)) throw new CustomExceptions.OwnershipException("You aren't the owner");
    }
}
