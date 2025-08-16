package com.kingjnr.Application.service;

import com.kingjnr.Application.model.*;
import com.kingjnr.Application.repository.ContractRepository;
import com.kingjnr.Application.repository.UserContractRepository;
import com.kingjnr.Application.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ContractUserService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserContractRepository userContractRepository;

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<?> createUserContract(Long userId, Long contractId) {
        Map<String,Object> response = new HashMap<>();

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Contract> contractOptional = contractRepository.findById(contractId);

        if (userOptional.isEmpty() || contractOptional.isEmpty()) {
            response.put("message", "User or Contract not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        Contract contract = contractOptional.get();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(contract.getContractPeriod());


        BigDecimal amountAtEndOfContract = BigDecimal.valueOf(contract.getContractPeriod() * contract.getDailyProfit().longValue());

        ContractStatus contractStatus = startDate.isAfter(endDate) ? ContractStatus.EXPIRED : ContractStatus.ACTIVE;


        UserContract userContract = new UserContract(
                null,
                contract.getTitle(),
                startDate,
                endDate,
                contract.getDailyProfit(),
                amountAtEndOfContract,
                contract.getInvestmentAmount(),
                getContractProgress(startDate,endDate).getBody(),
                contractStatus,
                user
        );

        userContractRepository.save(userContract);

        response.put("message","Contract assigned to user successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BigDecimal> getCurrentAmount(Long contractId) {
        Optional<UserContract> optionalUserContract = userContractRepository.findById(contractId);
        if (optionalUserContract.isPresent()) {

            LocalDate startDate = optionalUserContract.get().getStartDate();
            LocalDate currentDate = LocalDate.now();
            long daysBetweenStartDateAndCurrentDate = ChronoUnit.DAYS.between(startDate,currentDate);

            BigDecimal currentAmount = optionalUserContract.get().getDailyProfit().multiply(BigDecimal.valueOf(daysBetweenStartDateAndCurrentDate));

            return new ResponseEntity<>(currentAmount,HttpStatus.OK);

        }

        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }


    public ResponseEntity<BigDecimal> getEndOfContractAmount(Long contractId) {
        Optional<UserContract> optionalUserContract = userContractRepository.findById(contractId);

        return optionalUserContract.map(userContract -> new ResponseEntity<>(userContract.getAmountAtEndOfContract(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED));

    }

    public ResponseEntity<Integer> getContractProgress(LocalDate startDate, LocalDate endDate){

            long daysBetweenStartDateAndCurrentDate = ChronoUnit.DAYS.between(startDate, LocalDate.now());
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);

            long progress = ((daysBetweenStartDateAndCurrentDate * 100) /totalDays);

            System.out.println(progress);

            return new ResponseEntity<>((int)progress,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getNumberOfActiveContracts(HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
           List<UserContract> userContracts = user.getUserContracts();
           return new ResponseEntity<>(userContracts.size(),HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
    }


    public ResponseEntity<BigDecimal> getTotalEarnings(HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            List<UserContract> userContracts = user.getUserContracts();
            BigDecimal totalAmount = new BigDecimal(0);
            for (int i=0;i<userContracts.size();i++) {

                totalAmount = totalAmount.add(getCurrentAmount(userContracts.get(i).getContract_id()).getBody());

            }
            return new ResponseEntity<>(totalAmount,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
    }


    public ResponseEntity<List<UserContract>> getRecentEarningContracts() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String email = authentication.getName();
         Optional<User> optionalUser = userRepository.findByEmail(email);

         if(optionalUser.isPresent()){
             List<UserContract> contracts = optionalUser.get().getUserContracts();
             List<UserContract> completedContracts = new ArrayList<>();
             for(UserContract contract : contracts){
                 if(contract.getContractStatus() == ContractStatus.ACTIVE){
                     completedContracts.add(contract);
                 }
             }
            return new ResponseEntity<>(completedContracts,HttpStatus.OK);
         }

         return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
    }
}
