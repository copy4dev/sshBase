package com.copy4dev.sshBase.ssh.dao;

import com.copy4dev.sshBase.base.dao.IHibernateDao;
import com.copy4dev.sshBase.ssh.vo.UserTest;

public interface IUserTestDao extends IHibernateDao {

	public UserTest findByName(String name);

}



