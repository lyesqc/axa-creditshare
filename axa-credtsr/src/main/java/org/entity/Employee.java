package org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable{
	/*
	@EmbeddedId
	EmployeeId id;
	public EmployeeId getId() {
		return id;
	}
	public void setId(EmployeeId id) {
		this.id = id;
	}*/
	@Id
	@Column(name="CONTRNO")
	private String contrno;
	
	@Id
	@Column(name="MSISDN")
	private String msisdn;
	
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContrno() {
		return contrno;
	}
	public void setContrno(String contrno) {
		this.contrno = contrno;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	@Column(name="ISTELECO")
	private String isTeleco;
	
	@Column(name="DEFAULT_AMOUNT")
	String amount;
	
    public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
    public String getIsTeleco() {
		return isTeleco;
	}
	public void setIsTeleco(String isTeleco) {
		this.isTeleco = isTeleco;
	}
	
    
	

}
