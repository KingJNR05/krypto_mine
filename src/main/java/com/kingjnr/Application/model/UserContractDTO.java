package com.kingjnr.Application.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserContractDTO {
    private Long contract_id;

    private String title;

    private BigDecimal dailyProfit;
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amountAtEndOfContract;
    private BigDecimal investedAmount;

    private Integer progress;

    private ContractStatus contractStatus;

    public UserContractDTO(Long contract_id, String title, BigDecimal dailyProfit, LocalDate startDate, LocalDate endDate, BigDecimal amountAtEndOfContract, BigDecimal investedAmount, Integer progress, ContractStatus contractStatus) {
        this.contract_id = contract_id;
        this.title = title;
        this.dailyProfit = dailyProfit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountAtEndOfContract = amountAtEndOfContract;
        this.investedAmount = investedAmount;
        this.progress = progress;
        this.contractStatus = contractStatus;
    }

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCurrentAmount() {
        return dailyProfit;
    }

    public void setCurrentAmount(BigDecimal dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAmountAtEndOfContract() {
        return amountAtEndOfContract;
    }

    public void setAmountAtEndOfContract(BigDecimal amountAtEndOfContract) {
        this.amountAtEndOfContract = amountAtEndOfContract;
    }

    public BigDecimal getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(BigDecimal investedAmount) {
        this.investedAmount = investedAmount;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public BigDecimal getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(BigDecimal dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
