package com.copy4dev.sshBase.ssh.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传下载<br/>
 * 参考:http://blog.csdn.net/hzc543806053/article/details/7538723
 * @author Admin
 * @version 2016年10月12日
 *
 */
public class FileLoadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 778603000571766317L;

	private int number;

	private String fileName;

	/*
	 * 3) fileName="${fileName}" ：在这定义的名字是一个动态的，该名字是显示在下载框上的文件名字
	 * 
	 * 
	 * 4.<param
	 * name="inputName">downloadFile</param>，这个downloadFile名字要和FileDownload
	 * .java类中的getDownloadFile()方法名去掉get 一致
	 */

	// 返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
	public InputStream getDownloadFile() throws Exception {
		String s = "我.txt";//乱码
		this.fileName = s;
//		this.fileName = new String(s.getBytes("UTF-8"), "UTF-8");
		// 获取资源路径
		return ServletActionContext.getServletContext().getResourceAsStream("downloads/" + fileName);
	}

	/**
	 * 一定会执行
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	// --- set and get ---

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}