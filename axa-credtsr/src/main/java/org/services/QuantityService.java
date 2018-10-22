package org.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.entity.Employee;

public interface QuantityService {
	public Map<String,String> sendBatchQuantity(List<Employee> list) throws JsonProcessingException,IOException  ;

}
