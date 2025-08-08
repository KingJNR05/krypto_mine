package com.kingjnr.Application.model;


import java.util.List;

public class UserSafeDTO {
    private Long id;


    private String firstName;
    private String lastName;



    private Role role;

    private String referralCode;


    private List<UserContractDTO> userContractDTOs;

    public UserSafeDTO(Long id, String firstName, String lastName, String referralCode, List<UserContractDTO> userContractDTOs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.referralCode = referralCode;
        this.userContractDTOs = userContractDTOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public List<UserContractDTO> getUserContractDTOs() {
        return userContractDTOs;
    }

    public void setUserContractDTOs(List<UserContractDTO> userContractDTOs) {
        this.userContractDTOs = userContractDTOs;
    }
}
