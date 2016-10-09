package com.copy4dev.sshBase.ssh.dao.impl;

import com.copy4dev.sshBase.base.dao.impl.HibernateDaoImpl;
import com.copy4dev.sshBase.ssh.dao.IUserTestDao;
import com.copy4dev.sshBase.ssh.vo.UserTest;
import com.copy4dev.sshBase.utils.StringUtils;

public class UserTestDaoImpl extends HibernateDaoImpl implements IUserTestDao {

	@Override
	public UserTest findByName(String name) {

		if (!StringUtils.isEmpty(name)) {
			StringBuffer hql = new StringBuffer();
			hql.append("from UserTest where 1 = 1 and name = '");
			hql.append(name);
			hql.append("'");
			return findUniqueEntity(hql.toString());
		}
		return null;
		
		
		
		

	}

}
