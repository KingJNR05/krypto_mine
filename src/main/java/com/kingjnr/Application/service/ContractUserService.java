package com.kingjnr.Application.service;

import com.kingjnr.Application.model.Contract;
import com.kingjnr.Application.model.ContractStatus;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.model.UserContract;
import com.kingjnr.Application.repository.ContractRepository;
import com.kingjnr.Application.repository.UserContractRepository;
import com.kingjnr.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(contract.getContractPeriod());
        BigDecimal currentAmount = BigDecimal.valueOf(contract.getDailyProfit().longValue() * (endDate.getDayOfYear() - currentDate.getDayOfYear()));
        BigDecimal amountAtEndOfContract = BigDecimal.valueOf(contract.getContractPeriod() * contract.getDailyProfit().longValue());

        ContractStatus contractStatus = currentDate.isAfter(endDate) ? ContractStatus.EXPIRED : ContractStatus.ACTIVE;


        UserContract userContract = new UserContract(
                null,
                contract.getTitle(),
                currentAmount,
                currentDate,
                endDate,
                amountAtEndOfContract,
                contract.getInvestmentAmount(),
                contractStatus,
                user
        );

        userContractRepository.save(userContract);

        response.put("message","Contract assigned to user successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BigDecimal> getCurrentAmount(Long contractId) {
        Optional<UserContract> optionalUserContract = userContractRepository.findById(contractId);

        return optionalUserContract.map(userContract -> new ResponseEntity<>(userContract.getCurrentAmount(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED));

    }


    public ResponseEntity<BigDecimal> getEndOfContractAmount(Long contractId) {
        Optional<UserContract> optionalUserContract = userContractRepository.findById(contractId);

        return optionalUserContract.map(userContract -> new ResponseEntity<>(userContract.getAmountAtEndOfContract(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED));

    }
}
