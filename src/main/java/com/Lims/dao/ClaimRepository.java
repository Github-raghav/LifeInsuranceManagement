package com.Lims.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.Lims.entities.claimPolicy;

public interface ClaimRepository extends JpaRepository<claimPolicy, Integer> {

}
