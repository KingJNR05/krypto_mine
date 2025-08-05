package com.kingjnr.Application.service;

import com.kingjnr.Application.model.Contract;
import com.kingjnr.Application.model.Role;
import com.kingjnr.Application.model.User;
import com.kingjnr.Application.repository.ContractRepository;
import com.kingjnr.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
                    new Contract("Bitcoin Miner S21 Pro", "200 TH/s", 365, 3.20, 1170, 1000),
                    new Contract("Ethereum Mining Rig Pro", "500 MH/s", 180, 8.75, 3000, 2500),
                    new Contract("Enterprise Mining Package", "2 PH/s", 730, 35.00, 13500, 10000),
                    new Contract("Litecoin Miner L7", "9.5 GH/s", 240, 2.50, 900, 750),
                    new Contract("Dogecoin Starter Pack", "580 MH/s", 150, 1.67, 625, 500),
                    new Contract("Bitcoin Cash Pro Miner", "150 TH/s", 300, 6.25, 2250, 1800),
                    new Contract("Multi-Coin Mining Farm", "1.2 PH/s", 540, 18.75, 6750, 5000),
                    new Contract("Solana Cloud Miner", "800 H/s", 365, 4.11, 1500, 1200),
                    new Contract("Premium Mining Suite", "3.5 PH/s", 912, 57.89, 21000, 15000)
                            ));


            contractRepository.saveAll(contracts);


            User admin = new User(null,"Admin", "admin", "admin@a.com", "adminpass", Role.ADMIN);
            userRepository.save(admin);


        }
    }
}
