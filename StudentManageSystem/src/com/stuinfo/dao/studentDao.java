package com.stuinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.stuinfo.model.Page;
import com.stuinfo.model.StudentBean;
import com.stuinfo.util.DateUtil;

public class studentDao {
	
	//获取查询结果
	public ResultSet studentList(Connection con,Page page,StudentBean student,String preBirth,String laterBirth,String classNo,String className)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT* FROM studentinfo sif,classinfo cif where sif.classid=cif._id");
		//查询条件
		if(student.getSno()!=""&&student.getSno()!=null){
			sbf.append(" and Sno like '%"+student.getSno()+"%'");
		}
		if(student.getSname()!=""&&student.getSname()!=null){
			sbf.append(" and Sname like '%"+student.getSname()+"%'");
		}
		if(student.getSex()!=""&&student.getSex()!=null){
			sbf.append(" and sex ='"+student.getSex()+"'");
		}
		if(preBirth!=""&&preBirth!=null){
			sbf.append(" and birth>='"+preBirth+"'");
			//sbf.append(" and TO_DAYS(birth)>=TO_DAYS('"+preBirth+"')");
		}
		if(laterBirth!=""&&laterBirth!=null){
			sbf.append(" and birth<='"+laterBirth+"'");
			//sbf.append(" and TO_DAYS(birth)<=TO_DAYS('"+laterBirth+"')");
		}
		
		if(classNo!=""&&classNo!=null&&!classNo.equals("请选择")){
			sbf.append(" and cif.classNo = '"+classNo+"'");
		}
		if(className!=""&&className!=null&&!className.equals("请选择")){
			sbf.append(" and cif.className = '"+className+"'");
		}
		sbf.append(" group by S_id");
		//获取分页条件
		if(page!=null){
			sbf.append(" limit "+page.getStart()+","+page.getRows());//限制每一页显示多少条数据
		}
		PreparedStatement pstmt=con.prepareStatement(sbf.toString()/*.replaceFirst("and", "where")*/);
		//输出sql语句
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	//得到总记录数
	public int getStudentCount(Connection con,StudentBean student,String preBirth,String laterBirth,String classNo,String className)throws Exception{
		StringBuffer sql=new StringBuffer("SELECT COUNT(*) AS total FROM studentinfo sif,classinfo cif where sif.classid=cif._id");
		//查询条件
		if(student.getSno()!=""&&student.getSno()!=null){
			sql.append(" and Sno like '%"+student.getSno()+"%'");
		}
		if(student.getSname()!=""&&student.getSname()!=null){
			sql.append(" and Sname like '%"+student.getSname()+"%'");
		}
		if(student.getSex()!=""&&student.getSex()!=null){
			sql.append(" and sex ='"+student.getSex()+"'");
		}
		if(preBirth!=""&&preBirth!=null){
			sql.append(" and birth>='"+preBirth+"'");
			//sbf.append(" and TO_DAYS(birth)>=TO_DAYS('"+preBirth+"')");
		}
		if(laterBirth!=""&&laterBirth!=null){
			sql.append(" and birth<='"+laterBirth+"'");
			//sbf.append(" and TO_DAYS(birth)<=TO_DAYS('"+laterBirth+"')");
		}
		
		if(classNo!=""&&classNo!=null&&!classNo.equals("请选择")){
			sql.append(" and cif.classNo = '"+classNo+"'");
		}
		if(className!=""&&className!=null&&!className.equals("请选择")){
			sql.append(" and cif.className = '"+className+"'");
		}
		PreparedStatement pstmt=con.prepareStatement(sql.toString()/*.replace("and", "where")*/);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	//删除数据
	public int deleteData(Connection con,String deleteIDs)throws Exception{
		String sql="delete from studentinfo where S_id in ("+deleteIDs+")";
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();//返回受影响的行数
	}
	
	//添加数据
	public int addStudentData(Connection con,StudentBean stub)throws Exception{
		String sql="insert into studentinfo values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,stub.getSno());
		pstmt.setString(2, stub.getSname());
		pstmt.setString(3, stub.getSex());
		pstmt.setInt(4, Integer.parseInt(stub.getClassid()));
		pstmt.setString(5, DateUtil.dateFormateString(stub.getBirth(), "yyyy-MM-dd"));
		pstmt.setString(6, stub.getEmail());
		pstmt.setString(7, stub.getPhone());
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	//修改数据
	public int modifyStudentData(Connection con,StudentBean stub)throws Exception{
		String sql="update studentinfo set Sno=?,Sname=?,sex=?,classid=?,birth=?,email=?,phone=? where S_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,stub.getSno());
		pstmt.setString(2, stub.getSname());
		pstmt.setString(3, stub.getSex());
		pstmt.setInt(4, Integer.parseInt(stub.getClassid()));
		pstmt.setString(5, DateUtil.dateFormateString(stub.getBirth(), "yyyy-MM-dd"));
		pstmt.setString(6, stub.getEmail());
		pstmt.setString(7, stub.getPhone());
		pstmt.setInt(8, stub.getSid());
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	//通过班级id查询学生
	public boolean getStudentByClassId(Connection con,String classId)throws Exception{
		String sql="select * from studentinfo where classid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
}
