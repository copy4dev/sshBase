package com.copy4dev.sshBase.service.impl.ssh;

import java.io.Serializable;

import com.copy4dev.sshBase.dao.ssh.IUserTestDao;
import com.copy4dev.sshBase.service.ssh.IUserTestService;
import com.copy4dev.sshBase.vo.ssh.UserTest;

public class UserTestServiceImpl implements IUserTestService {

	private IUserTestDao userTestDao;

	@Override
	public Serializable save(UserTest entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTest get(Class<UserTest> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserTest entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(UserTest entity) {
		// TODO Auto-generated method stub

	}

	// --- user define ---
	
	@Override
	public UserTest getById(Integer id) {
		return userTestDao.get(UserTest.class, id);
	}

	// --- set and get ---

	public IUserTestDao getUserTestDao() {
		return userTestDao;
	}

	public void setUserTestDao(IUserTestDao userTestDao) {
		this.userTestDao = userTestDao;
	}

}
