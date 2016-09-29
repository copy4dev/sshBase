package com.copy4dev.sshBase.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * com.thinkgem.jeesite.common.db
 * @author copy4dev
 * @date 2016年9月3日
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	@Override 
    protected Object determineCurrentLookupKey() { 
        return getCurrentLookupKey(); 
    }  
	
	public static String getCurrentLookupKey() { 
        return (String) contextHolder.get(); 
    }  
	
	public static void setCurrentLookupKey(String currentLookupKey) { 
        contextHolder.set(currentLookupKey); 
    }
}
