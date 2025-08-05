package com.kingjnr.Application.service;

import com.kingjnr.Application.model.Contract;
import com.kingjnr.Application.model.Role;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.repository.ContractRepository;
import com.kingjnr.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (contractRepository.count() == 0) {
            List<Contract> contracts = new ArrayList<>(Arrays.asList(
                    new Contract("Bitcoin Miner S21 Pro", "200 TH/s", 365, new BigDecimal("3.20"), new BigDecimal("1170"), new BigDecimal("1000")),
                    new Contract("Ethereum Mining Rig Pro", "500 MH/s", 180, new BigDecimal("8.75"), new BigDecimal("3000"), new BigDecimal("2500")),
                    new Contract("Enterprise Mining Package", "2 PH/s", 730, new BigDecimal("35.00"), new BigDecimal("13500"), new BigDecimal("10000")),
                    new Contract("Litecoin Miner L7", "9.5 GH/s", 240, new BigDecimal("2.50"), new BigDecimal("900"), new BigDecimal("750")),
                    new Contract("Dogecoin Starter Pack", "580 MH/s", 150, new BigDecimal("1.67"), new BigDecimal("625"), new BigDecimal("500")),
                    new Contract("Bitcoin Cash Pro Miner", "150 TH/s", 300, new BigDecimal("6.25"), new BigDecimal("2250"), new BigDecimal("1800")),
                    new Contract("Multi-Coin Mining Farm", "1.2 PH/s", 540, new BigDecimal("18.75"), new BigDecimal("6750"), new BigDecimal("5000")),
                    new Contract("Solana Cloud Miner", "800 H/s", 365, new BigDecimal("4.11"), new BigDecimal("1500"), new BigDecimal("1200")),
                    new Contract("Premium Mining Suite", "3.5 PH/s", 912, new BigDecimal("57.89"), new BigDecimal("21000"), new BigDecimal("15000"))
            ));



            contractRepository.saveAll(contracts);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            String adminPassword = encoder.encode("adminpass");
            User admin = new User(null,"Admin", "admin", "admin@a.com",adminPassword , Role.ADMIN);

            userRepository.save(admin);


        }
    }
}
