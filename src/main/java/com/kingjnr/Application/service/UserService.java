package com.kingjnr.Application.service;

import com.kingjnr.Application.model.*;
import com.kingjnr.Application.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> registerUser(User user) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (user.getUsername() == null || user.getUsername().isBlank() ||
                    user.getPassword() == null || user.getPassword().isBlank()) {
                response.put("message","Email and password must not be empty");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }


            if (userRepository.findByEmail(user.getUsername()).isPresent()) {
                response.put("message","An account with this email already exists.");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);

            response.put("message","User registered successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> login(UserLoginDTO userLoginDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (userLoginDto.getEmail() == null || userLoginDto.getEmail().isBlank() ||
                    userLoginDto.getPassword() == null || userLoginDto.getPassword().isBlank()) {
                response.put("message", "Email and password must not be empty");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );

            if (authentication.isAuthenticated()) {

                response.put("message", jwtService.generateToken(userLoginDto));

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Login failed");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

        } catch (BadCredentialsException e) {
            response.put("message", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            response.put("message", "Login error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> logout(HttpServletRequest request) {
        Map<String,Object> response = new HashMap<>();

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        response.put("message","Logged out successfully");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> userOptional = userRepository.findByEmail(authentication.getName());

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            List<UserContractDTO> contracts = user.getUserContracts().
                            stream().
                            map(contract -> new UserContractDTO(contract.getContract_id(),
                                    contract.getTitle(),
                                    contract.getDailyProfit(),
                                    contract.getStartDate(),
                                    contract.getEndDate(),
                                    contract.getAmountAtEndOfContract(),
                                    contract.getInvestedAmount(),
                                    contract.getContractStatus()))
                    .toList();


            UserSafeDTO userSafeDTO = new UserSafeDTO(user.getId(), user.getFirstName(),user.getLastName(),user.getReferralCode(), contracts);
            return new ResponseEntity<>(userSafeDTO, HttpStatus.OK);
        }
        response.put("message", "User not found");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
