package com.Lims.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	
    @OneToMany(cascade=CascadeType.ALL,fetch =FetchType.LAZY,mappedBy="user" )
	private List<ClientInfo> clientinfo=new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL,fetch =FetchType.LAZY,mappedBy="user")
    private List<Payment> payment=new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL,fetch =FetchType.LAZY,mappedBy="user")
    private List<Policy> policy=new ArrayList<>();
    
	public List<Policy> getPolicy() {
		return policy;
	}

	public void setPolicy(List<Policy> policy) {
		this.policy = policy;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String FirstName;
	private String LastName;
	private String password;
	private String role;
	private String gender;
	private String contactNo;
	@Column(unique=true)
	private String email;
	private Date dob;
	
	
	public List<ClientInfo> getClientinfo() {
		return clientinfo;
	}

	public void setClientinfo(List<ClientInfo> clientinfo) {
		this.clientinfo = clientinfo;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	
	
}
