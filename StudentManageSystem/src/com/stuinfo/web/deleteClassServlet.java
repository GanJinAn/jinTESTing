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
		String deleteIDs=request.getParameter("deleteIDs");//��������Ҫ��ǰ̨��һ��
		Connection con=null;
		JSONObject result=new JSONObject();
		String str[]=deleteIDs.split(",");//���ַ��������á�,������
		try{
			con=dbUtil.getCon();
			for(int i=0;i<str.length;i++){
				boolean f=stuDao.getStudentByClassId(con, str[i]);
				if(f){
					result.put("errorIndex", i);
					result.put("errorMsg", "�༶����ѧ��������ɾ����");
					ResponseUtil.write(response, result);
					return;
				}
			}
			//����������س�JSONArray
			int deleteCount=cld.deleteData(con, deleteIDs);//���ɾ��������
			
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
