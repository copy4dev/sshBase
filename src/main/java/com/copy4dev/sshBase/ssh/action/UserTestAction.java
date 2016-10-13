package com.copy4dev.sshBase.ssh.action;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.copy4dev.sshBase.base.action.BaseAction;
import com.copy4dev.sshBase.db.DynamicDataSource;
import com.copy4dev.sshBase.ssh.service.IUserTestService;
import com.copy4dev.sshBase.ssh.vo.UserTest;
import com.copy4dev.sshBase.utils.ProjectDirListener;

public class UserTestAction extends BaseAction<UserTest> {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -671660989292087304L;

	private static final Logger LOG = Logger.getLogger(UserTestAction.class);

	private IUserTestService userTestService;

	/**
	 * 页面跳转测试
	 * 
	 * @return
	 */
	public String jump() {
		LOG.warn("日志及缓存保存点" + System.getProperties().getProperty(ProjectDirListener.PROJECT_DIR_KEY));
		return SUCCESS;
	}

	/**
	 * josn测试
	 * 
	 * @return
	 */
	public String getJSON() {

		try {
			UserTest userTest = userTestService.get(UserTest.class, 1);
			jsonResult = JSONObject.toJSON(userTest);
			System.out.println("old: " + jsonResult);
			System.out.println("${web:rootDir}");
			// 数据源切换测试
			changeDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 数据源切换测试
	 */
	private void changeDB() {
		// 切换数据源2ndDataSource
		DynamicDataSource.setCurrentLookupKey("2ndDataSource");

		UserTest userTest = userTestService.findByName("admin");
		System.out.println("changeDB: " + JSONObject.toJSON(userTest).toString());

		// 切换默认数据源defDataSource回来
		DynamicDataSource.setCurrentLookupKey("defDataSource");
	}

	// --- set and get ---

	public IUserTestService getUserTestService() {
		return userTestService;
	}

	public void setUserTestService(IUserTestService userTestService) {
		this.userTestService = userTestService;
	}

}
