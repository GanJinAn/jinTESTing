package com.stuinfo.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	//将结果集转换成JSONArray使得EasyUI可识别
	public static JSONArray ResultSetToJasonArray(ResultSet rs)throws Exception{
		//获取纵向数据
		ResultSetMetaData rsmd=rs.getMetaData();
		int num=rsmd.getColumnCount();
		//将结果集封装，（键值对）
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject obj=new JSONObject();
			for(int i=1;i<=num;i++){
			//判断获取的对象是否为日期类型
				Object o=rs.getObject(i);
				if(o instanceof Date){
				//将日期类型转换成字符串
					obj.put(rsmd.getColumnName(i), DateUtil.dateFormateString((Date)o, "yyyy-MM-dd"));
				}else{
					obj.put(rsmd.getColumnName(i), rs.getObject(i));
				}
			}
			array.add(obj);
		}
		return array;
	}
}
