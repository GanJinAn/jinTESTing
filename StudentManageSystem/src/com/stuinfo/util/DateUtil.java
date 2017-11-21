package com.stuinfo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	//������ת�����ַ���
	public static String dateFormateString(Date date,String format){
		//���ص��ַ������
		String res="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			//res��Ϊ����ת������ַ���
			res=sdf.format(date);
		}
		return res;
	}
	//���ַ���ת������������
	public static Date stringformatDate(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
