package com.kingjnr.Application.controllers;

import com.kingjnr.Application.service.ContractUserService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
