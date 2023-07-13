package com.example.bikeevents.authentication.controller;

import com.example.bikeevents.authentication.models.AuthenticationModel;
import com.example.bikeevents.authentication.models.ResponseModel;
import com.example.bikeevents.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseModel> register(@RequestBody AuthenticationModel request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> authenticate(@RequestBody AuthenticationModel request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
