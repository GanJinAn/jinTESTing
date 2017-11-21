package com.stuinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.stuinfo.dao.classDao;
import com.stuinfo.model.Page;
import com.stuinfo.model.classBean;
import com.stuinfo.util.DataBaseUtil;
import com.stuinfo.util.JsonUtil;
import com.stuinfo.util.ResponseUtil;

public class asyReadDataServlet extends HttpServlet {
	
	DataBaseUtil dbUtil=new DataBaseUtil();
	classDao cld=new classDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//将结果集返回成JSONArray
			JSONArray jsonArray=new JSONArray();
			JSONObject jobj=new JSONObject();
			jobj.put("classNo", "请选择");
			jobj.put("className", "请选择");
			jsonArray.add(jobj);
			jsonArray.addAll(JsonUtil.ResultSetToJasonArray(cld.getclassNoList(con)));
			jsonArray.addAll(JsonUtil.ResultSetToJasonArray(cld.getclassNameList(con)));
			ResponseUtil.write(response, jsonArray);
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
