package com.Lims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lims.entities.ClientInfo;

public interface ClientRepository extends JpaRepository<ClientInfo, Integer> {

}
