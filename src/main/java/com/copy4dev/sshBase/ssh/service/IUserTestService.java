

package com.copy4dev.sshBase.ssh.service;

import com.copy4dev.sshBase.base.service.IBaseService;
import com.copy4dev.sshBase.ssh.vo.UserTest;

/**
 * 测试用的service接口
 * 
 * @author Admin
 *
 */
public interface IUserTestService extends IBaseService<UserTest> {

	/**
	 * 根据id获取单个实体
	 * @param id
	 * @return
	 */
	UserTest findByName(String name);

}
