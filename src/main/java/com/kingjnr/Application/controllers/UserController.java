package com.kingjnr.Application.controllers;

import com.kingjnr.Application.model.Role;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.model.UserLoginDTO;
import com.kingjnr.Application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( origins = "https://kryptomine.netlify.app/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/")
    public String welcome(){
        return "Welcome to the APP";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        user.setRole(Role.USER);
        return userService.registerUser(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> adminRegister(@RequestBody User user){
        user.setRole(Role.ADMIN);
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO user, HttpServletRequest request){
        return userService.login(user, request);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
       return userService.logout(request);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request){
        return userService.getUserInfo(request);

    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return ResponseEntity.ok("Logged in as: " + email);
    }



}
