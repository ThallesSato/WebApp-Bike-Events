package com.example.bikeevents.services;

import com.example.bikeevents.dto.ClientDto;
import com.example.bikeevents.exception.CustomExceptions;
import com.example.bikeevents.models.Client;
import com.example.bikeevents.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    private SubscriptionService subscriptionService;
    @Autowired
    private ClientRepository clientRepository;


    public List<Client> clientsGetAll(){
        return clientRepository.findAll();
    }

    public Client clientGetById(int id) {
        return clientRepository.findById(id).orElseThrow(() -> new CustomExceptions.NotFoundException("Client not found with Id "+id));
    }

    public Client clientGetByUsername(String username) {
        return clientRepository.findByUsername(username).orElse(null);
    }

    public void clientPost(Client client){
        clientRepository.save(client);
    }

    public Client clientPut(int id, ClientDto client){
        Client oldClient = clientGetById(id);
//        isOwner(Client);
        oldClient.setUsername(client.getUsername());
        oldClient.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(oldClient);
    }

    public void clientDelete(int id) {
        Client client = clientGetById(id);
//        isOwner(Client);
        subscriptionService.subscriptionClientDelete(client);
        clientRepository.delete(client);
    }

    public void isOwner(Client client) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String owner = client.getUsername();

        if(!userDetails.getUsername().equals(owner)) throw new CustomExceptions.OwnershipException("You aren't the owner");
    }

    public void isUsernameAlreadyUsed(String username) {
        if (clientGetByUsername(username)!=null) throw new CustomExceptions.BadRequestException("Username already used");
    }
}
