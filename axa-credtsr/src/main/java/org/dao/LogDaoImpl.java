package org.dao;

import org.entity.MyLog;
import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl extends AbstractLogDao implements LogDao {

	//@Override
	public boolean save(MyLog log) {
		// TODO Auto-generated method stub
		getSession().save(log);
		//getSession().flush();
		return true;
	}
	

}
