package com.stuinfo.util;

import java.sql.*;


public class DataBaseUtil {
	
	private String dbUrl="jdbc:mysql://localhost:3306/studentdb";
	private String dbUserName="gan";
	private String dbPassword="123456";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	public DataBaseUtil(){
		super();
	}
	//�������ݿ�
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	//�ر����ݿ�����
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DataBaseUtil dbUtil=new DataBaseUtil();
		try {
			dbUtil.getCon();
			System.out.println("���ݿ����ӳɹ�!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
