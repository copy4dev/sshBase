package com.copy4dev.sshBase.vo.ssh;

import java.io.Serializable;

/**
 * 测试:用户类
 * @author Admin
 * @version 2016年9月27日
 *
 */
public class UserTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6455485276828167508L;

	private Integer id;
	
	private String name;
	
	private String password;
	
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
