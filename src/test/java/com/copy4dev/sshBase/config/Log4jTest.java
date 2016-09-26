package com.copy4dev.sshBase.config;

import org.apache.log4j.Logger;

/**
 * log4j 测试
 * @author copy4dev
 * @version 2016年9月25日
 *
 */
public class Log4jTest {

	// 引入log4j
	private Logger logger = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {
		Log4jTest log4jTest = new Log4jTest();
		log4jTest.test1();
	}

	public void test1() {
		// 打印输出
		logger.debug("succest");
	}

}
