package com.sample.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	private double totalAmt;
	private int duration;
	private double monthlyEMI;
	
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
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
	//@JsonBackReference
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", totalAmt=" + totalAmt + ", duration=" + duration + ", monthlyEMI="
				+ monthlyEMI + "]";
	}
	
	
	
//	public Payment(int paymentId, double totalAmt, int duration, double monthlyEMI, Customer string1) {
//		super();
//		this.paymentId = paymentId;
//		this.totalAmt = totalAmt;
//		this.duration = duration;
//		this.monthlyEMI = monthlyEMI;
//		this.customer=string1;
//	}
//	
	
}
