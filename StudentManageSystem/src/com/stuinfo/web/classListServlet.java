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
		//easyui���̨��������Ĳ���
		String page=request.getParameter("page");//��ǰҳ��
		String rows=request.getParameter("rows");//ÿҳ������
		String className=request.getParameter("className");//��ȡ����className
		//û�а༶���Ʋ�ѯ�����ǣ�s_classNameΪnull
		if(className==null){
			className="";
		}
		classBean clb=new classBean();
		clb.setClassName(className);
		//�����ǰ�ڼ�ҳ�Ͷ�����
		//System.out.println("page:"+page+"\t rows:"+rows);
		//page����
		Page p=new Page(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//cld.classList(con, p);//����������Ӷ����Page����,����ResultSet�����
			//����������س�JSONArray
			JSONArray jsonArray=JsonUtil.ResultSetToJasonArray(cld.classList(con, p,clb));
			int total=cld.getClassCount(con,clb);//�������
			JSONObject result=new JSONObject();
			//rows��total������鿴�ĵ�
			result.put("rows", jsonArray);
			result.put("total", total);
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
