package com.stuinfo.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.stuinfo.dao.classDao;
import com.stuinfo.dao.studentDao;
import com.stuinfo.util.DataBaseUtil;
import com.stuinfo.util.ResponseUtil;

public class deleteClassServlet extends HttpServlet {

	DataBaseUtil dbUtil=new DataBaseUtil();
	classDao cld=new classDao();
	studentDao stuDao=new studentDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deleteIDs=request.getParameter("deleteIDs");//参数名称要和前台的一样
		Connection con=null;
		JSONObject result=new JSONObject();
		String str[]=deleteIDs.split(",");//将字符串数组用“,”隔开
		try{
			con=dbUtil.getCon();
			for(int i=0;i<str.length;i++){
				boolean f=stuDao.getStudentByClassId(con, str[i]);
				if(f){
					result.put("errorIndex", i);
					result.put("errorMsg", "班级还有学生，不能删除！");
					ResponseUtil.write(response, result);
					return;
				}
			}
			//将结果集返回成JSONArray
			int deleteCount=cld.deleteData(con, deleteIDs);//获得删除的总数
			
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
