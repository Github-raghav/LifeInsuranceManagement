package com.Lims.entities;
/**
 * @author Raghav Arora
 * purpose-This is agent entity class containing description of agent.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Agent")
public class Agent {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String Name;
	@Column(unique=true)
	private String email;
	private long mobileNumber;
	private String address;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Agent(int id, String name, String email, long mobileNumber, String address) {
		super();
		this.id = id;
		Name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	public Agent() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
