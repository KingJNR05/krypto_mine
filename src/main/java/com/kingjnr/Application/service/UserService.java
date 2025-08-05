package com.kingjnr.Application.service;

import com.kingjnr.Application.model.Role;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.model.UserDTO;
import com.kingjnr.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<String> registerUser(User user) {
        try {
            if (user.getUsername() == null || user.getUsername().isBlank() ||
                    user.getPassword() == null || user.getPassword().isBlank()) {
                return new ResponseEntity<>("Username and password must not be empty", HttpStatus.BAD_REQUEST);
            }

            // Check if user already exists
            if (userRepository.findByEmail(user.getUsername()).isPresent()) {
                return new ResponseEntity<>("Username already taken", HttpStatus.CONFLICT);
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> login(UserDTO user) {
        try {
            if (user.getUsername() == null || user.getUsername().isBlank() ||
                    user.getPassword() == null || user.getPassword().isBlank()) {
                return new ResponseEntity<>("Username and password must not be empty", HttpStatus.BAD_REQUEST);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return new ResponseEntity<>("Login Success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Login error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
