package com.copy4dev.sshBase.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 获取项目目录在服务器的部署位置<br/>
 * 参考:http://hbiao68.iteye.com/blog/1947618
 * @author Admin
 * @version 2016年10月11日
 *
 */
public class ProjectDirListener implements ServletContextListener {

	public static final String PROJECT_DIR_KEY = "projectDir";

	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		System.getProperties().remove(PROJECT_DIR_KEY);
	}

	public void contextInitialized(ServletContextEvent servletcontextevent) {

		String projectDir = servletcontextevent.getServletContext().getRealPath("/");
		System.setProperty(PROJECT_DIR_KEY, projectDir);

		System.out.println("projectDir:" + projectDir);
	}

}
