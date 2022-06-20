package com.Lims.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Lims.dao.AgentRepository;
import com.Lims.entities.Agent;
import com.Lims.entities.User;

@SpringBootTest
class Agnetrepo {

	@Autowired
	private AgentRepository agentRepo;
	
	
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
  
	@Test
	void findAll() {
		Agent agent=new Agent(36,"abc","abc@gmail.com",1234566342,"kljmk");
		agentRepo.save(agent);
		List<Agent> lists=agentRepo.findAll();
		assertThat(lists.size()).isGreaterThan(0);
	}
	
	@AfterEach
	void tearoDown() {
		System.out.println("Agent Deleted");
//		agentRepo.deleteById(76);
	}
	
	
	@Test
	public void getAgentbyId() {
		Agent agent=agentRepo.findById(78).get();
	assertThat(agent.getEmail()).isEqualTo("jk@gmail.com");
	}
	
	@Test
	public void getListofAgent() {
		List<Agent> agent=agentRepo.findAll();
		
		assertThat(agent.size()).isGreaterThan(0);
	}
	
	@Test
	public void updateAgent() {
		Agent agent=agentRepo.findById(78).get();
		agent.setName("update");
		Agent Agentupdated=agentRepo.save(agent);
		assertThat(Agentupdated.getName()).isEqualTo("update");
		
	}
	

	
	
}
