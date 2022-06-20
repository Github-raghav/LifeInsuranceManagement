package com.Lims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lims.entities.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
