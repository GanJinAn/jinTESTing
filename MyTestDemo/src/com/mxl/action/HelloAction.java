package com.mxl.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mxl.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//2.实现模型驱动封装获取表单数据
public class HelloAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	public String execute() {
		//1 request域
		/*
		HttpServletRequest request=ServletActionContext.getRequest();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		User user=new User(username,password,address);
		System.out.println(user.getUsername()+":"+user.getPassword()+":"+user.getAddress());*/
		System.out.println(user.getUsername()+":"+user.getPassword()+":"+user.getAddress());
		return NONE;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
