package com.copy4dev.sshBase.action.ssh;

import com.alibaba.fastjson.JSONObject;
import com.copy4dev.sshBase.action.base.BaseAction;
import com.copy4dev.sshBase.service.ssh.IUserTestService;
import com.copy4dev.sshBase.vo.ssh.UserTest;

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
//			UserTest userTest = userTestService.findByName("root");
			UserTest userTest = userTestService.get(UserTest.class, 1);
			jsonResult = JSONObject.toJSON(userTest);
			System.out.println("json: " + jsonResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// --- set and get ---

	public IUserTestService getUserTestService() {
		return userTestService;
	}

	public void setUserTestService(IUserTestService userTestService) {
		this.userTestService = userTestService;
	}

}
