package com.kingjnr.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String hashPower;
    private int contractPeriod;
    private double dailyProfit;
    private double totalProfit;
    private double investmentAmount;


    public Contract() {}

    public Contract(String title, String hashPower, int contractPeriod, double dailyProfit, double totalProfit, double investmentAmount) {
        this.title = title;
        this.hashPower = hashPower;
        this.contractPeriod = contractPeriod;
        this.dailyProfit = dailyProfit;
        this.totalProfit = totalProfit;
        this.investmentAmount = investmentAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHashPower() {
        return hashPower;
    }

    public void setHashPower(String hashPower) {
        this.hashPower = hashPower;
    }

    public int getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(int contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public double getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(double dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }
}

