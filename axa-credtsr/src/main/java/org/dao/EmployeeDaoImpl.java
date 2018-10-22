package org.dao;

import java.util.List;

import org.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractEmployeeDao implements EmployeeDao{


	public List<Employee> getAllEmployees( String contrno) {
		// TODO Auto-generated method stub
		Criteria myCriteria = getSession().createCriteria(Employee.class);
		myCriteria.add(Restrictions.eq("contrno",contrno));
		myCriteria.add(Restrictions.eq("status","A"));
		System.out.println("CONTRNO :"+contrno);
		List<Employee> listEmployee = myCriteria.list();
	    
		return listEmployee;
	}
	public String getAuxSimOfContrno(List<Employee> list){
		String msisdn=null;
		for (Employee e : list){
			
			msisdn =e.getIsTeleco().equals("1")?e.getMsisdn():null;
			if(msisdn!= null) {
				System.out.println("DAO AUX1 "+e.getMsisdn());
				break;
			}
		}
		return msisdn;
	}

}
