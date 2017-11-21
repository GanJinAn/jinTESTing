package com.stuinfo.dao;
import java.sql.*;

import com.stuinfo.model.User;
public class UserDao {
	//用户登录验证
	public User login(Connection con,User user)throws Exception{
		User loginuser=null;
		String sql="select * from userinfo where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			loginuser=new User();
			loginuser.setUsername(rs.getString("userName"));
			loginuser.setPassword(rs.getString("password"));
		}
		return loginuser;
	}

}
