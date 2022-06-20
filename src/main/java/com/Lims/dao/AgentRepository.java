package com.Lims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.Lims.entities.Agent;
import com.Lims.entities.User;


public interface AgentRepository extends JpaRepository<Agent, Integer> {
	
	@Query("SELECT a from Agent a where a.Name Like %?1%")
	public List<Agent>  findAll(String keyword);

}
