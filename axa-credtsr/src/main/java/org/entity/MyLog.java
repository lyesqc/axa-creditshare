package org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOG_DBSS")
public class MyLog implements Serializable {

	@Column(name="operation")
	private String operation;
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getContrno() {
		return contrno;
	}
	public void setContrno(String contrno) {
		this.contrno = contrno;
	}
	public String getMsgResponse() {
		return msgResponse;
	}
	public void setMsgResponse(String msgResponse) {
		this.msgResponse = msgResponse;
	}
	public String getLabelx() {
		return labelx;
	}
	public void setLabelx(String labelx) {
		this.labelx = labelx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@Id
	@Column(name="actor")
	private String actor;
	@Column(name="controno")
	private String contrno;
	@Column(name="message_response")
	private String msgResponse;
	@Id
	@Column(name="label_x")
	private String labelx;
	@Column(name="status")
	private String status;
	@Column(name="request_id")
	private String requestId;
	
}
