package com.copy4dev.sshBase.dao.ssh;

import com.copy4dev.sshBase.dao.base.IHibernateDao;
import com.copy4dev.sshBase.vo.ssh.UserTest;

public interface IUserTestDao extends IHibernateDao {

	public UserTest findByName(String name);

}
