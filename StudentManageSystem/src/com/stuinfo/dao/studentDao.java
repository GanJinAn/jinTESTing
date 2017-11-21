package com.stuinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.stuinfo.model.Page;
import com.stuinfo.model.StudentBean;
import com.stuinfo.util.DateUtil;

public class studentDao {
	
	//��ȡ��ѯ���
	public ResultSet studentList(Connection con,Page page,StudentBean student,String preBirth,String laterBirth,String classNo,String className)throws Exception{
		StringBuffer sbf= new StringBuffer("SELECT* FROM studentinfo sif,classinfo cif where sif.classid=cif._id");
		//��ѯ����
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
		
		if(classNo!=""&&classNo!=null&&!classNo.equals("��ѡ��")){
			sbf.append(" and cif.classNo = '"+classNo+"'");
		}
		if(className!=""&&className!=null&&!className.equals("��ѡ��")){
			sbf.append(" and cif.className = '"+className+"'");
		}
		sbf.append(" group by S_id");
		//��ȡ��ҳ����
		if(page!=null){
			sbf.append(" limit "+page.getStart()+","+page.getRows());//����ÿһҳ��ʾ����������
		}
		PreparedStatement pstmt=con.prepareStatement(sbf.toString()/*.replaceFirst("and", "where")*/);
		//���sql���
		System.out.println(sbf.toString());
		return pstmt.executeQuery();
	}
	//�õ��ܼ�¼��
	public int getStudentCount(Connection con,StudentBean student,String preBirth,String laterBirth,String classNo,String className)throws Exception{
		StringBuffer sql=new StringBuffer("SELECT COUNT(*) AS total FROM studentinfo sif,classinfo cif where sif.classid=cif._id");
		//��ѯ����
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
		
		if(classNo!=""&&classNo!=null&&!classNo.equals("��ѡ��")){
			sql.append(" and cif.classNo = '"+classNo+"'");
		}
		if(className!=""&&className!=null&&!className.equals("��ѡ��")){
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
	//ɾ������
	public int deleteData(Connection con,String deleteIDs)throws Exception{
		String sql="delete from studentinfo where S_id in ("+deleteIDs+")";
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();//������Ӱ�������
	}
	
	//�������
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
	
	//�޸�����
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
	
	//ͨ���༶id��ѯѧ��
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
