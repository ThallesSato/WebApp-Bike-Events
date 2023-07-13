package com.example.bikeevents.controllers;

import com.example.bikeevents.dto.ClientDto;
import com.example.bikeevents.models.Client;
import com.example.bikeevents.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok(clientService.clientsGetAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.clientGetById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> putClient(@PathVariable Integer id, @RequestBody ClientDto client){
        return ResponseEntity.ok(clientService.clientPut(id,client));
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteClient(@PathVariable Integer id){
        clientService.clientDelete(id);
        return ResponseEntity.ok("Client deleted successful");
    }

}
