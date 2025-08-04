package com.kingjnr.Application.controllers;

import com.kingjnr.Application.model.User;
import com.kingjnr.Application.model.UserDTO;
import com.kingjnr.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserService userService;





    @GetMapping("/")
    public String welcome(){
        return "Welcome to the APP";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        return userService.login(user);
    }
}
