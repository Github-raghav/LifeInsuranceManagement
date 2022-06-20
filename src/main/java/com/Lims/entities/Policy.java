package com.Lims.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="POLICY")
public class Policy {

	public long getpNumber() {
		return pNumber;
	}
	
	public void setpNumber(long pNumber) {
		this.pNumber = pNumber;
	}

	//@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int pid;
	@Column(unique=true)
	private String policyName;
//	@Column(unique=true)
	@Id
	private long pNumber;
	private String description;
	private int pAmountPerYear;
	private int pTerm;
	private Date matureDate;
	
	@ManyToOne
	private User user;
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getpAmountPerYear() {
		return pAmountPerYear;
	}

	public void setpAmountPerYear(int pAmountPerYear) {
		this.pAmountPerYear = pAmountPerYear;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getpAmount() {
		return pAmountPerYear;
	}

	public void setpAmount(int pAmount) {
		this.pAmountPerYear = pAmount;
	}

	public int getpTerm() {
		return pTerm;
	}

	public void setpTerm(int pTerm) {
		this.pTerm = pTerm;
	}

	public Date getMatureDate() {
		return matureDate;
	}

	public void setMatureDate(Date matureDate) {
		this.matureDate = matureDate;
	}

//	Policy policy=new Policy();
//	if(policy.getPolicyName.equals("HomeInsurance")) {
//		policy.setpAmount(5000);
//		
//	}
	
	@Override
	public String toString() {
		return "Policy [pid="+pid  + ", policyName=" + policyName + ", pNumber=" + pNumber + ", description="
				+ description + ", pAmountPerYear=" + pAmountPerYear + ", pTerm=" + pTerm + ", matureDate=" + matureDate
				+ ", user=" + user + "]";
	}
	
}
