package com.stuinfo.model;

public class User {
	
	private int userid;
	private String password;
	private String username;
	
	public User(){
		super();
	}
	
	public User(String username,String password){
		super();
		this.username=username;
		this.password=password;
	}
	public User(int userid,String username,String password){
		super();
		this.userid=userid;
		this.username=username;
		this.password=password;
	}
	public void setUserid(int userid){
		this.userid=userid;
	}
	public int getUserid(){
		return this.userid;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return this.username;
	}
}
