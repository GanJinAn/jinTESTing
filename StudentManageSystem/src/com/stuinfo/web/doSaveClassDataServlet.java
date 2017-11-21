package com.stuinfo.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.stuinfo.dao.classDao;
import com.stuinfo.model.classBean;
import com.stuinfo.util.DataBaseUtil;
import com.stuinfo.util.ResponseUtil;

public class doSaveClassDataServlet extends HttpServlet {
	
	DataBaseUtil dbUtil=new DataBaseUtil();
	classDao cld=new classDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//解决服务器中文乱码问题（异步提交中文乱码）
		String classNo=request.getParameter("classNo");
		String className=request.getParameter("className");
		String updateId=request.getParameter("_id");
		//System.out.println(updateId);
		//System.out.println(classNo+"/t"+className);
		classBean clb=new classBean(classNo,className);
		if(updateId!=null){
			clb.set_id(Integer.parseInt(updateId));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int updateCount=0;
			//将结果集返回成JSONArray
			JSONObject result=new JSONObject();
			if(classNo.length()<=10&&className.length()<=20){
				if(updateId!=null){//_id不为空时，则执行修改操作
					updateCount=cld.modifyClassData(con, clb);
				}else{//_id为空时则执行添加操作
					updateCount=cld.addClassData(con, clb);
				}
				if(updateCount>0){
					result.put("success", "true");
				}else{
					result.put("success", "true");//此success信息是由于前台业务逻辑的设计原因（JS代码返回success，但会判断是否携带errorMsg信息）
					result.put("errorMsg","保存数据失败！");
				}
				ResponseUtil.write(response, result);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{//关闭连接
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
