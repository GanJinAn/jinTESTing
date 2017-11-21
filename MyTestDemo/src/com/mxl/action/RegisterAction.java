package com.mxl.action;



import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

	public String execute() {
		
		//ActionContext获取表单数据
		/*
		ActionContext context=ActionContext.getContext();
		Map<String, Parameter> map=context.getParameters();
		Set<String> keys=map.keySet();
		for(String key:keys) {
			Parameter obj=map.get(key);
			System.out.println(obj.toString());
		}*/
		
		//HttpServletRequest获取request对象方式获取表单数据
		/*
		HttpServletRequest request=ServletActionContext.getRequest();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		System.out.println(username+":"+password+":"+address);
		*/
		
		//Action操作域对象
		//1 request域
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("res", "resValue");
		//2 session域对象
		HttpSession session=request.getSession();
		session.setAttribute("sessionName", "sessionValue");
		//3.ServletContext域对象
		ServletContext context=ServletActionContext.getServletContext();
		context.setAttribute("contextName", "contextValue");
		
		return NONE;
	}
}
