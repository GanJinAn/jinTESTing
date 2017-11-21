package com.stuinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.stuinfo.model.Page;
import com.stuinfo.model.classBean;

public class classDao {

	//��ȡ��ѯ�����
	public ResultSet classList(Connection con,Page page,classBean classbean)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT* FROM classinfo");
		if(classbean==null){
			sbf.append(" and className like '%"+classbean.getClassName()+"%'");
		}
		//�����ѯ�༶����������Ϊ��ʱ
		if(classbean!=null&&classbean.getClassName()!=""){
			sbf.append(" and className like '%"+classbean.getClassName()+"%'");
		}
		sbf.append(" group by classNo");
		//��ȡ��ҳ����
		if(page!=null){
			sbf.append(" limit "+page.getStart()+","+page.getRows());//����ÿһҳ��ʾ����������
		}
		
		PreparedStatement pstmt=con.prepareStatement(sbf.toString().replaceFirst("and", "where"));
		//���sql���
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	
	//�õ��ܼ�¼��
	public int getClassCount(Connection con,classBean classbean)throws Exception{
		StringBuffer sql=new StringBuffer("SELECT COUNT(*) AS total FROM classinfo");
		if(classbean.getClassName()!=""){
			sql.append(" and className like '%"+classbean.getClassName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sql.toString().replace("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	//ɾ������
	public int deleteData(Connection con,String deleteIDs)throws Exception{
		String sql="delete from classinfo where _id in ("+deleteIDs+")";
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();//������Ӱ�������
	}
	
	//�������
	public int addClassData(Connection con,classBean clb)throws Exception{
		String sql="insert into classinfo values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,clb.getClassNo());
		pstmt.setString(2, clb.getClassName());
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	//�޸�����
	public int modifyClassData(Connection con,classBean clb)throws Exception{
		String sql="update classinfo set classNo=?,className=? where _id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, clb.getClassNo());
		pstmt.setString(2, clb.getClassName());
		pstmt.setInt(3, clb.get_id());
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	//���ں�studentinfo��������첽��ȡ����
	public ResultSet getclassNoList(Connection con)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT * FROM classinfo WHERE classNo!='' group by classNo");
		PreparedStatement pstmt=con.prepareStatement(sbf.toString());
		//���sql���
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	public ResultSet getclassNameList(Connection con)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT* FROM classinfo WHERE className!='' group by className");//DISTINCT(className)
		PreparedStatement pstmt=con.prepareStatement(sbf.toString());
		//���sql���
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	
	//��ȡ�༶���ƶ�Ӧ��_id
	public ResultSet getclassID(Connection con,String className)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT _id FROM classinfo WHERE className='"+className+"'");
		PreparedStatement pstmt=con.prepareStatement(sbf.toString());
		//���sql���
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
}
