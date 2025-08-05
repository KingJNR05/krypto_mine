package com.kingjnr.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user_contract")
public class UserContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;



    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public UserContract() {}

    public UserContract(Long contract_id, String title, LocalDate startDate, LocalDate endDate, User user) {
        this.contract_id = contract_id;
        this.title = title;

        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
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








    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
