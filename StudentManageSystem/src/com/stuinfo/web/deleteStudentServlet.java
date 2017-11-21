package com.stuinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.stuinfo.dao.studentDao;
import com.stuinfo.util.DataBaseUtil;
import com.stuinfo.util.ResponseUtil;

public class deleteStudentServlet extends HttpServlet {


	DataBaseUtil dbUtil=new DataBaseUtil();
	studentDao stuDao=new studentDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deleteIDs=request.getParameter("deleteIDs");//参数名称要和前台的一样
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//将结果集返回成JSONArray
			int deleteCount=stuDao.deleteData(con, deleteIDs);//获得删除的总数
			JSONObject result=new JSONObject();
			if(deleteCount>0){//删除成功
				result.put("success", "true");
				result.put("deleteCount", deleteCount);
			}else{
				result.put("errorMsg","删除数据失败！");
			}
			
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
