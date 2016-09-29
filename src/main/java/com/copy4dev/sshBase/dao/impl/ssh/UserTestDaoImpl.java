package com.copy4dev.sshBase.dao.impl.ssh;

import com.copy4dev.sshBase.dao.impl.base.HibernateDaoImpl;
import com.copy4dev.sshBase.dao.ssh.IUserTestDao;
import com.copy4dev.sshBase.utils.StringUtils;
import com.copy4dev.sshBase.vo.ssh.UserTest;

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
