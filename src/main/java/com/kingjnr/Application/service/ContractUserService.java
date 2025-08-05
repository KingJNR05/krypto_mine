package com.kingjnr.Application.service;

import com.kingjnr.Application.model.Contract;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.model.UserContract;
import com.kingjnr.Application.repository.ContractRepository;
import com.kingjnr.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ContractUserService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<String> createUserContract(Long userId, Long contractId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Contract> contractOptional = contractRepository.findById(contractId);

        if (userOptional.isEmpty() || contractOptional.isEmpty()) {
            return new ResponseEntity<>("User or Contract not found", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        Contract contract = contractOptional.get();
        UserContract userContract = new UserContract(null,
                contract.getTitle(),
                LocalDate.now(),
                LocalDate.now().plusDays(contract.getContractPeriod()),
                user);


        contractRepository.save(contract); // Persist the update

        return new ResponseEntity<>("Contract assigned to user successfully", HttpStatus.OK);
    }

}
