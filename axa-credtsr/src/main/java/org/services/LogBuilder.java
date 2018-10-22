package org.services;

import javax.persistence.Column;

import org.entity.MyLog;

public class LogBuilder {
	private String operation;
	private String actor;
	private String contrno;
	private String msgResponse;
	private String labelx;
	private String status;
	private String requestId;
	
	public LogBuilder operationIs(String operation){
		this.operation = operation;
		return this;
	}
	
	public LogBuilder actorIs(String actor){
		this.actor = actor;
		return this;
	}
	public LogBuilder contrnoIs(String contrno){
		this.contrno = contrno;
		return this;
	}
	public LogBuilder msgResponseIs(String msgResponse){
		this.msgResponse = msgResponse;
		return this;
	}
	public LogBuilder labelxIs(String labelx){
		this.labelx = labelx;
		return this;
	}
	
	public LogBuilder statusIs(String status){
		this.status = status;
		return this;
	}
	public LogBuilder requestIdIs(String requestId){
		this.requestId = requestId;
		return this;
	}
	
	public MyLog build(){
		MyLog log = new MyLog();
		log.setActor(this.actor);
		log.setContrno(this.contrno);
		log.setLabelx(this.labelx);
		log.setMsgResponse(this.msgResponse);
		log.setOperation(this.operation);
		log.setStatus(this.status);
		log.setRequestId(this.requestId);
		return log;
	}

}
