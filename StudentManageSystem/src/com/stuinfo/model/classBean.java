package com.stuinfo.model;

public class classBean {

	private int _id;
	private String classNo;
	private String className;
	
	public classBean(){
		super();
	}
	public classBean(String classNo,String className){
		super();
		this.classNo=classNo;
		this.className=className;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
}
