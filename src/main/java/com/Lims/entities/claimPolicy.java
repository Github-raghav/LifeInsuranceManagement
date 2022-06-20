package com.Lims.entities;
/**
 * @author Raghav Arora
 *purpose-This is claim policy class .
 */
import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLAIMPOLICY")
public class claimPolicy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int claimNumber;
	private String IncurredDate;
	private String ReportedDate;
	private String claimPaid;
	private String admission;
	private String discharge;
	private String claimStatus;
	private long totalCharges;
	private long totalDeductiblePaid;
	private long totalCoInsurancePaid;
	private long totalExcludedAmount;
	private long totalExceededAmount;
	private long benefitPaid;
	private long preHospitalCharges;
	private long postHospitalCharges;
	private File docFile;
	
	
	
	public File getDocFile() {
		return docFile;
	}
	public void setDocFile(File docFile) {
		this.docFile = docFile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(int claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getIncurredDate() {
		return IncurredDate;
	}
	public void setIncurredDate(String incurredDate) {
		IncurredDate = incurredDate;
	}
	public String getReportedDate() {
		return ReportedDate;
	}
	public void setReportedDate(String reportedDate) {
		ReportedDate = reportedDate;
	}
	public String getClaimPaid() {
		return claimPaid;
	}
	public void setClaimPaid(String claimPaid) {
		this.claimPaid = claimPaid;
	}
	public String getAdmission() {
		return admission;
	}
	public void setAdmission(String admission) {
		this.admission = admission;
	}
	public String getDischarge() {
		return discharge;
	}
	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public long getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(long totalCharges) {
		this.totalCharges = totalCharges;
	}
	public long getTotalDeductiblePaid() {
		return totalDeductiblePaid;
	}
	public void setTotalDeductiblePaid(long totalDeductiblePaid) {
		this.totalDeductiblePaid = totalDeductiblePaid;
	}
	public long getTotalCoInsurancePaid() {
		return totalCoInsurancePaid;
	}
	public void setTotalCoInsurancePaid(long totalCoInsurancePaid) {
		this.totalCoInsurancePaid = totalCoInsurancePaid;
	}
	public long getTotalExcludedAmount() {
		return totalExcludedAmount;
	}
	public void setTotalExcludedAmount(long totalExcludedAmount) {
		this.totalExcludedAmount = totalExcludedAmount;
	}
	public long getTotalExceededAmount() {
		return totalExceededAmount;
	}
	public void setTotalExceededAmount(long totalExceededAmount) {
		this.totalExceededAmount = totalExceededAmount;
	}
	public long getBenefitPaid() {
		return benefitPaid;
	}
	public void setBenefitPaid(long benefitPaid) {
		this.benefitPaid = benefitPaid;
	}
	public long getPreHospitalCharges() {
		return preHospitalCharges;
	}
	public void setPreHospitalCharges(long preHospitalCharges) {
		this.preHospitalCharges = preHospitalCharges;
	}
	public long getPostHospitalCharges() {
		return postHospitalCharges;
	}
	public void setPostHospitalCharges(long postHospitalCharges) {
		this.postHospitalCharges = postHospitalCharges;
	}
	
}
