

package com.copy4dev.sshBase.service.ssh;

import com.copy4dev.sshBase.service.base.IBaseService;
import com.copy4dev.sshBase.vo.ssh.UserTest;

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
