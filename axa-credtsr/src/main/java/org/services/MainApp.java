package org.services;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonProcessingException;
import org.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) throws JsonProcessingException, IOException{
		try{
		ApplicationContext context =
			    new ClassPathXmlApplicationContext(new String[] {"mySpringContext.xml"});
		
		
		/*
		QuantityService	myservice = (QuantityService)context.getBean("quantityService");
		myservice.sendBatchQuantity(new ArrayList<Employee>());
		*/
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
