package com.stuinfo.web;

import java.io.IOException;
import java.sql.Connection;

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

public class classListServlet extends HttpServlet {
	
	DataBaseUtil dbUtil=new DataBaseUtil();
	classDao cld=new classDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//easyui向后台发送请求的参数
		String page=request.getParameter("page");//当前页面
		String rows=request.getParameter("rows");//每页的行数
		String className=request.getParameter("className");//获取表格的className
		//没有班级名称查询条件是，s_className为null
		if(className==null){
			className="";
		}
		classBean clb=new classBean();
		clb.setClassName(className);
		//输出当前第几页和多少行
		//System.out.println("page:"+page+"\t rows:"+rows);
		//page对象
		Page p=new Page(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//cld.classList(con, p);//传入参数连接对象和Page对象,返回ResultSet结果集
			//将结果集返回成JSONArray
			JSONArray jsonArray=JsonUtil.ResultSetToJasonArray(cld.classList(con, p,clb));
			int total=cld.getClassCount(con,clb);//获得总数
			JSONObject result=new JSONObject();
			//rows和total参数需查看文档
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
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
