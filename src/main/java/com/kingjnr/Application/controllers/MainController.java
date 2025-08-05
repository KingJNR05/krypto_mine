package com.kingjnr.Application.controllers;

import com.kingjnr.Application.model.Role;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.model.UserDTO;
import com.kingjnr.Application.service.ContractUserService;
import com.kingjnr.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContractUserService contractUserService;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to the APP";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        user.setRole(Role.USER);
        return userService.registerUser(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<String> adminRegister(@RequestBody User user){
        user.setRole(Role.ADMIN);
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user){
        return userService.login(user);
    }



    @PostMapping("/contract/{id}")
    public ResponseEntity<String> createContract(@RequestBody Long userId, @PathVariable Long id){

        return contractUserService.createUserContract(userId, id);
    }
}
