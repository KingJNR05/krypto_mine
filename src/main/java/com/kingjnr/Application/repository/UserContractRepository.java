package com.kingjnr.Application.repository;

import com.kingjnr.Application.model.UserContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContractRepository extends JpaRepository<UserContract,Long> {
}
