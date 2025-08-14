package com.kingjnr.Application.controllers;

import com.kingjnr.Application.service.ContractUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin( origins = "https://kryptomine.netlify.app/")
@RestController
public class UserContractController {

    @Autowired
    private ContractUserService contractUserService;

    @PostMapping("/contract/{contractId}")
    public ResponseEntity<?> createContract(@RequestBody Long userId, @PathVariable Long contractId){
        return contractUserService.createUserContract(userId, contractId);
    }

    @GetMapping("/current_amount/{userId}")
    public ResponseEntity<BigDecimal> getCurrentAmount(@PathVariable Long contractId){
       return contractUserService.getCurrentAmount(contractId);
    }

    @GetMapping("/endOfContractAmount")
    public ResponseEntity<BigDecimal> amountAtEndOfContract(@PathVariable Long userId){
        return contractUserService.getEndOfContractAmount(userId);
    }

    @GetMapping("/totalEarnings")
    public ResponseEntity<BigDecimal> getTotalEarnings(HttpServletRequest request){
        return contractUserService.getTotalEarnings(request);
    }

    @GetMapping("/contractProgress/{contractId}")
    public ResponseEntity<Integer> getContractProgress(@PathVariable Long contractId){
        return contractUserService.getContractProgress(contractId);
    }

    @GetMapping("activeContracts")
    public ResponseEntity<Integer> getNumberOfActiveContracts(HttpServletRequest request){
        return contractUserService.getNumberOfActiveContracts(request);
    }



}
