package com.sample.Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanId;
	private double loanAmt;
	private String loanType;
	private int duration;
	private double monthlyEMI;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")//,insertable = false, updatable = false)
	//@JoinColumns({
	  //  @JoinColumn(name="firstName", referencedColumnName="fname"),
	    //@JoinColumn(name="LastName", referencedColumnName="lname")
	  //})
	private Customer customer;


	public Loan() {

	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getMonthlyEMI() {
		return monthlyEMI;
	}

	public void setMonthlyEMI(double monthlyEMI) {
		this.monthlyEMI = monthlyEMI;
	}
	@JsonBackReference
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanAmt=" + loanAmt + ", loanType=" + loanType + ", duration=" + duration
				+ ", monthlyEMI=" + monthlyEMI + ", customer=" + customer + "]";
	}
	public Loan(String jsonString) {
		ObjectMapper objectMapper=new ObjectMapper();
		try
		{
			Loan loan=objectMapper.readValue(jsonString,Loan.class);
			this.loanId=loan.getLoanId();
			this.loanAmt=loan.getLoanAmt();
			this.loanType=loan.getLoanType();
			this.duration=loan.getDuration();
			this.monthlyEMI=loan.getMonthlyEMI();
			this.customer=loan.getCustomer();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

}
