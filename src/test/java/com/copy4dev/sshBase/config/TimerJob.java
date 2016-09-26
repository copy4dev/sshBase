package com.copy4dev.sshBase.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务 测试
 * 
 * @author copy4dev
 * @date 2016年9月3日
 *
 */
@Component
public class TimerJob {

	@Scheduled(cron = "*/20 * * * * *")
	public void test() {
		System.out.println(" --- start --- ");
		System.out.println(" ---  end  --- ");
	}
}
