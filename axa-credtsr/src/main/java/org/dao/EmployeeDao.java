package org.dao;


import java.util.List;

import org.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
public interface   EmployeeDao {
	
	List<Employee> getAllEmployees(String contrno);
	String getAuxSimOfContrno(List<Employee> list);

}
