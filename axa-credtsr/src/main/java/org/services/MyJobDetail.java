package org.services;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonProcessingException;
import org.entity.Employee;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJobDetail extends QuartzJobBean {

	
	QuantityService quantityService;
	
	public void setQuantityService(QuantityService quantityService) {
		this.quantityService = quantityService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		// call my services
		try {
			System.out.println("calling job");
			if(quantityService == null ){
				System.out.println("quantityService is null");
			}
			quantityService.sendBatchQuantity(new ArrayList<Employee>());
			System.out.println("End calling job");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
