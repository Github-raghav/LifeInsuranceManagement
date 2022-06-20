package com.Lims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lims.entities.Agent;

@Service
public class AgentServices {

	@Autowired
	private AgentRepository agentRepository;
	
	public List<Agent> listAll(String keyword){
		System.out.println(keyword);
		if(keyword!=null) {
			return agentRepository.findAll(keyword);
		}
		
		return agentRepository.findAll();
	}
	public void save(Agent agent) {
		agentRepository.save(agent);
	}
}
