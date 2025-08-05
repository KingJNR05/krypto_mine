package com.kingjnr.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String hashPower;
    private int contractPeriod;
    private BigDecimal dailyProfit;
    private BigDecimal totalProfit;
    private BigDecimal investmentAmount;


    public Contract() {}

    public Contract(String title, String hashPower, int contractPeriod, BigDecimal dailyProfit, BigDecimal totalProfit, BigDecimal investmentAmount) {
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

    public BigDecimal getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(BigDecimal dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(BigDecimal investmentAmount) {
        this.investmentAmount = investmentAmount;
    }
}

