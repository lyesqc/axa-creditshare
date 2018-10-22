package org.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEmployeeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public void persist(Object em){
		getSession().persist(em);
	}
	public void delete(Object entity) {
        getSession().delete(entity);
    }

}