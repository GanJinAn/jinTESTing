package com.stuinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.stuinfo.model.Page;
import com.stuinfo.model.classBean;

public class classDao {

	//获取查询结果集
	public ResultSet classList(Connection con,Page page,classBean classbean)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT* FROM classinfo");
		if(classbean==null){
			sbf.append(" and className like '%"+classbean.getClassName()+"%'");
		}
		//如果查询班级名称条件不为空时
		if(classbean!=null&&classbean.getClassName()!=""){
			sbf.append(" and className like '%"+classbean.getClassName()+"%'");
		}
		sbf.append(" group by classNo");
		//获取分页条件
		if(page!=null){
			sbf.append(" limit "+page.getStart()+","+page.getRows());//限制每一页显示多少条数据
		}
		
		PreparedStatement pstmt=con.prepareStatement(sbf.toString().replaceFirst("and", "where"));
		//输出sql语句
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	
	//得到总记录数
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
	//删除数据
	public int deleteData(Connection con,String deleteIDs)throws Exception{
		String sql="delete from classinfo where _id in ("+deleteIDs+")";
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();//返回受影响的行数
	}
	
	//添加数据
	public int addClassData(Connection con,classBean clb)throws Exception{
		String sql="insert into classinfo values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,clb.getClassNo());
		pstmt.setString(2, clb.getClassName());
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	//修改数据
	public int modifyClassData(Connection con,classBean clb)throws Exception{
		String sql="update classinfo set classNo=?,className=? where _id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, clb.getClassNo());
		pstmt.setString(2, clb.getClassName());
		pstmt.setInt(3, clb.get_id());
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	//用于和studentinfo表的连接异步读取数据
	public ResultSet getclassNoList(Connection con)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT * FROM classinfo WHERE classNo!='' group by classNo");
		PreparedStatement pstmt=con.prepareStatement(sbf.toString());
		//输出sql语句
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	public ResultSet getclassNameList(Connection con)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT* FROM classinfo WHERE className!='' group by className");//DISTINCT(className)
		PreparedStatement pstmt=con.prepareStatement(sbf.toString());
		//输出sql语句
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	
	//获取班级名称对应的_id
	public ResultSet getclassID(Connection con,String className)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT _id FROM classinfo WHERE className='"+className+"'");
		PreparedStatement pstmt=con.prepareStatement(sbf.toString());
		//输出sql语句
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
}
