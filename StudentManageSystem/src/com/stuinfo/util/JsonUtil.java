package com.stuinfo.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	//�������ת����JSONArrayʹ��EasyUI��ʶ��
	public static JSONArray ResultSetToJasonArray(ResultSet rs)throws Exception{
		//��ȡ��������
		ResultSetMetaData rsmd=rs.getMetaData();
		int num=rsmd.getColumnCount();
		//���������װ������ֵ�ԣ�
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject obj=new JSONObject();
			for(int i=1;i<=num;i++){
			//�жϻ�ȡ�Ķ����Ƿ�Ϊ��������
				Object o=rs.getObject(i);
				if(o instanceof Date){
				//����������ת�����ַ���
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
