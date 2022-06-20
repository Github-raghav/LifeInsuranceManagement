package com.Lims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Lims.entities.Payment;
import com.Lims.entities.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query("SELECT cardNumber from Payment p where p.cardNumber= :cardNumber")
	public Long getcardNumberBycardNumber(@Param("cardNumber") long cardNumber);
	
//	@Query("SELECT availableBalance from Payment p where p.cardNumber= :cardNumber")
//	public Long getavailableBalanceBycardNumber(@Param("cardNumber") long cardNumber);
	
}
