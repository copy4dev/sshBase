package com.copy4dev.sshBase.base.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//抽象类action 复用代码
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 模型驱动
	private T model;

	// 页面接收参数easyui使用 只写入
	// 第几页
	protected Integer page;

	// 每页几行
	protected Integer rows;

	// 排序字段
	protected String sort;

	// 排序规则
	protected String order;

	// easyui结果转为json
	protected Object jsonResult;

	public void setModel(T model) {
		this.model = model;
	}

	public T getModel() {
		return model;
	}

	@SuppressWarnings("unchecked")
	public BaseAction() {
		Type genericSupserclass = findParameterizedType(this.getClass());
		ParameterizedType parameterizedType = (ParameterizedType) genericSupserclass;
		// 第一个参数
		Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

		// 创建一个
		try {
			model = modelClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("model初始化失败...");
		}
	}

	// 通过当前创建的Action对象的Class，找到参数化泛型
	@SuppressWarnings("rawtypes")
	private ParameterizedType findParameterizedType(Class clazz) {
		// 父类
		Type genericSuperclass = clazz.getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			return (ParameterizedType) genericSuperclass;
		} else {
			// 找父类
			return findParameterizedType(clazz.getSuperclass());
		}
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Object getJsonResult() {
		return jsonResult;
	}

//	//将分页结果压入堆栈中
//	protected void pushToValueStack(Page<T> pageData){
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("total", pageData.getTotalElements());
//		resultMap.put("rows", pageData.getContent());
//		//压入
//		jsonResult = resultMap;
//	}

	// request请求
	public String getRequestParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}

	public String[] getRequestParameterValues(String name) {
		return ServletActionContext.getRequest().getParameterValues(name);
	}

	// session保存数据
	public void setSessionAttribute(String name, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(name, value);
	}

	// 从session中获取数据
	public Object getSessionAttribute(String name) {
		return ServletActionContext.getRequest().getSession().getAttribute(name);
	}

	// 获取请求方式 post get put delete
	public String getRequestMethod() {
		return ServletActionContext.getRequest().getMethod();
	}

}
