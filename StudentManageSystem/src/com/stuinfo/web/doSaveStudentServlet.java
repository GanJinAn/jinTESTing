package com.stuinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.stuinfo.dao.classDao;
import com.stuinfo.dao.studentDao;
import com.stuinfo.model.StudentBean;
import com.stuinfo.model.classBean;
import com.stuinfo.util.DataBaseUtil;
import com.stuinfo.util.DateUtil;
import com.stuinfo.util.ResponseUtil;

public class doSaveStudentServlet extends HttpServlet {

	DataBaseUtil dbUtil=new DataBaseUtil();
	studentDao stuDao=new studentDao();
	classDao cld=new classDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//��������������������⣨�첽�ύ�������룩
		String Sno=request.getParameter("Sno");
		String Sname=request.getParameter("Sname");
		String sex=request.getParameter("sex");
		String birth=request.getParameter("birth");
		String className=request.getParameter("className");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String Sid=request.getParameter("S_id");
		System.out.println(Sid);
		StudentBean stub=null;
		String getID=new String();
		Connection con=null;
		try {
			con = dbUtil.getCon();
			ResultSet rs=cld.getclassID(con, className);
			if(rs.next()){
				getID=rs.getString(1);
				System.out.println("getID:"+getID);
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stub = new StudentBean(Sno,Sname,sex,DateUtil.stringformatDate(birth, "yyyy-MM-dd"),
					getID,email,phone);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(Sid!=null){
			stub.setSid(Integer.parseInt(Sid));
		}
		//Connection con=null;
		try{
			//con=dbUtil.getCon();
			int updateCount=0;
			//����������س�JSONArray
			JSONObject result=new JSONObject();
			if(Sid!=null){//_id��Ϊ��ʱ����ִ���޸Ĳ���
				updateCount=stuDao.modifyStudentData(con,stub);
			}else{//_idΪ��ʱ��ִ����Ӳ���
				updateCount=stuDao.addStudentData(con,stub);
			}
			if(updateCount>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");//��success��Ϣ������ǰ̨ҵ���߼������ԭ��JS���뷵��success�������ж��Ƿ�Я��errorMsg��Ϣ��
				result.put("errorMsg","��������ʧ�ܣ�");
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
