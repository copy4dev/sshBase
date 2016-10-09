package com.copy4dev.sshBase.ssh.action;

import com.alibaba.fastjson.JSONObject;
import com.copy4dev.sshBase.base.action.BaseAction;
import com.copy4dev.sshBase.db.DynamicDataSource;
import com.copy4dev.sshBase.ssh.service.IUserTestService;
import com.copy4dev.sshBase.ssh.vo.UserTest;

public class UserTestAction extends BaseAction<UserTest> {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -671660989292087304L;

	private IUserTestService userTestService;

	/**
	 * 页面跳转测试
	 * 
	 * @return
	 */
	public String jump() {
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
			// 数据源切换测试
//			changeDB();
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
		System.out.println("json: " + JSONObject.toJSON(userTest).toString());

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
