package com.copy4dev.sshBase.ssh.service.impl;

import java.io.Serializable;

import com.copy4dev.sshBase.ssh.dao.IUserTestDao;
import com.copy4dev.sshBase.ssh.service.IUserTestService;
import com.copy4dev.sshBase.ssh.vo.UserTest;

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
		return userTestDao.get(entityClass, id);
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
	public UserTest findByName(String name) {
		return userTestDao.findByName(name);
	}

	// --- set and get ---

	public IUserTestDao getUserTestDao() {
		return userTestDao;
	}

	public void setUserTestDao(IUserTestDao userTestDao) {
		this.userTestDao = userTestDao;
	}

}
