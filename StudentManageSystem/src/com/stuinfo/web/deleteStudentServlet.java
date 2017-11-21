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
		String deleteIDs=request.getParameter("deleteIDs");//��������Ҫ��ǰ̨��һ��
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//����������س�JSONArray
			int deleteCount=stuDao.deleteData(con, deleteIDs);//���ɾ��������
			JSONObject result=new JSONObject();
			if(deleteCount>0){//ɾ���ɹ�
				result.put("success", "true");
				result.put("deleteCount", deleteCount);
			}else{
				result.put("errorMsg","ɾ������ʧ�ܣ�");
			}
			
			ResponseUtil.write(response, result);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{//�ر�����
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
