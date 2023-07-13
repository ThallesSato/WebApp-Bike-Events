package com.example.bikeevents.services;

import com.example.bikeevents.dto.RideDto;
import com.example.bikeevents.exception.CustomExceptions;
import com.example.bikeevents.models.Ride;
import com.example.bikeevents.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RideService {
    @Autowired
    private ClientService clientService;
    @Autowired
    @Lazy
    private SubscriptionService subscriptionService;
    @Autowired
    private RideRepository rideRepository;


    public List<Ride> rideGetAll() {
        return rideRepository.findAll();
    }
    public Ride rideGetById(int id){
        return rideRepository.findById(id).orElseThrow(() -> new CustomExceptions.NotFoundException("Ride not found with Id "+id));
    }
    public Ride ridePost(RideDto rideDto){
        Ride ride = new Ride();

        updateRide(ride, rideDto);
        verifyDates(ride);

        try {
            ride.setClient(clientService.clientGetById(rideDto.getClient_id()));
        } catch (CustomExceptions.BadRequestException e){
            throw new CustomExceptions.BadRequestException("Client does not exist");
        }

        return rideRepository.save(ride);
    }

    public Ride ridePut(int id, RideDto rideDto){
        Ride ride = rideGetById(id);

//        isOwner(ride);
        updateRide(ride, rideDto);
        verifyDates(ride);

        return rideRepository.save(ride);
    }

    public void rideDelete(int id) {
        Ride ride = rideGetById(id);
//        isOwner(ride);
        subscriptionService.subscriptionRideDelete(ride);
        rideRepository.delete(ride);
    }

    public void isOwner(Ride ride) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String owner = ride.getClient().getUsername();

        if(!userDetails.getUsername().equals(owner)) throw new CustomExceptions.OwnershipException("You aren't the owner");
    }

    private void updateRide(Ride ride, RideDto rideDto) {
        ride.setName(rideDto.getName());
        ride.setStart_date(rideDto.getStart_date());
        ride.setStart_date_registration(rideDto.getStart_date_registration());
        ride.setEnd_date_registration(rideDto.getEnd_date_registration());
        ride.setAdditional_information(rideDto.getAdditional_information());
        ride.setStart_place(rideDto.getStart_place());
        if (ride.getParticipants_limit() <= 0) {
            ride.setParticipants_limit(-1);
        }else {
            ride.setParticipants_limit(rideDto.getParticipants_limit());
        }
    }

    private void verifyDates(Ride ride){
        if (ride.getStart_date().before(new Date()))
            throw new CustomExceptions.BadRequestException("The start date is in the past");

        if (ride.getStart_date_registration().before(new Date()))
            ride.setStart_date_registration(new Date());

        if (ride.getEnd_date_registration().before(new Date()))
            throw new CustomExceptions.BadRequestException("The end registration date is before now or start registration date");
    }
}
