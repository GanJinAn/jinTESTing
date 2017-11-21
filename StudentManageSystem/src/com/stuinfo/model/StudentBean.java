package com.stuinfo.model;

import java.util.Date;

public class StudentBean {

	private int Sid;
	private String Sno;
	private String Sname;
	private String sex;
	private Date birth;
	private String classid;
	private String email;
	private String phone;
	private String classname;
	
	public StudentBean(){
		super();
	}
	public StudentBean(String Sno,String Sname,String sex){
		this.Sno=Sno;
		this.Sname=Sname;
		this.sex=sex;
	}
	public StudentBean(String sno, String sname, String sex, Date birth,
			String classid, String email, String phone) {
		super();
		this.Sno = sno;
		this.Sname = sname;
		this.sex = sex;
		this.birth = birth;
		this.classid = classid;
		this.email = email;
		this.phone = phone;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
