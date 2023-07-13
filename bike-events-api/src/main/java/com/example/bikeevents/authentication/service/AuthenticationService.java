package com.example.bikeevents.authentication.service;

import com.example.bikeevents.authentication.models.AuthenticationModel;
import com.example.bikeevents.authentication.models.ResponseModel;
import com.example.bikeevents.exception.CustomExceptions;
import com.example.bikeevents.models.Client;
import com.example.bikeevents.models.Role;
import com.example.bikeevents.security.JwtService;
import com.example.bikeevents.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientService clientService;

    public ResponseModel register(AuthenticationModel request) {
        clientService.isUsernameAlreadyUsed(request.getUsername());

        Client client = new Client();

        client.setUsername(request.getUsername());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setRole(Role.USER);

        clientService.clientPost(client);
        var jwtToken = jwtService.generateToken(client);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setToken(jwtToken);
        return responseModel;
    }

    public ResponseModel authenticate(AuthenticationModel request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e){
            throw new CustomExceptions.AuthenticationException("Invalid login");
        }
        Client client = clientService.clientGetByUsername(request.getUsername());

        var jwtToken = jwtService.generateToken(client);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setToken(jwtToken);
        return responseModel;
    }
}
