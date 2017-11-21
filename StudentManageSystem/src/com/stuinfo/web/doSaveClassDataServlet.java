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
		request.setCharacterEncoding("utf-8");//��������������������⣨�첽�ύ�������룩
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
			//����������س�JSONArray
			JSONObject result=new JSONObject();
			if(classNo.length()<=10&&className.length()<=20){
				if(updateId!=null){//_id��Ϊ��ʱ����ִ���޸Ĳ���
					updateCount=cld.modifyClassData(con, clb);
				}else{//_idΪ��ʱ��ִ����Ӳ���
					updateCount=cld.addClassData(con, clb);
				}
				if(updateCount>0){
					result.put("success", "true");
				}else{
					result.put("success", "true");//��success��Ϣ������ǰ̨ҵ���߼������ԭ��JS���뷵��success�������ж��Ƿ�Я��errorMsg��Ϣ��
					result.put("errorMsg","��������ʧ�ܣ�");
				}
				ResponseUtil.write(response, result);
			}
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
