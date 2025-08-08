package com.kingjnr.Application.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserContractDTO {
    private Long contract_id;

    private String title;

    private BigDecimal currentAmount;
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amountAtEndOfContract;
    private BigDecimal investedAmount;

    private ContractStatus contractStatus;

    public UserContractDTO(Long contract_id, String title, BigDecimal currentAmount, LocalDate startDate, LocalDate endDate, BigDecimal amountAtEndOfContract, BigDecimal investedAmount, ContractStatus contractStatus) {
        this.contract_id = contract_id;
        this.title = title;
        this.currentAmount = currentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountAtEndOfContract = amountAtEndOfContract;
        this.investedAmount = investedAmount;
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
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
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
}
