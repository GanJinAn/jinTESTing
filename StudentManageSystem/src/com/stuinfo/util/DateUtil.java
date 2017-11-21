package com.stuinfo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	//将日期转化成字符串
	public static String dateFormateString(Date date,String format){
		//返回的字符串结果
		String res="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			//res作为日期转换后的字符串
			res=sdf.format(date);
		}
		return res;
	}
	//将字符串转换成日期类型
	public static Date stringformatDate(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
