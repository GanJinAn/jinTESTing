<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息管理系统登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{  font-family: 微软雅黑;
			color:white;  padding: 0;  margin: 0;  }
		#mainContent{
			width:100%;
			height:700px;
			background: url("images/bgimage.jpg");
			background-repeat: no-repeat;
            background-size: cover;
		}
		#includeTable{
			position:relative;
			width:400px;
			height:400px;
			top:80px;
			left:71px;
			border:2px solid white;
			border-radius:15px;
		}
		#rectangle{
			position:relative;
			width:550px;
			height:550px;
			top:50px;
			margin-left:50%;
			left:-275px;
			background-color:rgba(0,0,0,0.35);
		}
		#userInfo{
			position:relative;
			height:250px;
			margin-left:95px;
			top:50px;
		}
		#userName,#pwd{
			color:black;
			cursor: pointer;
		}
		.loginBtn{
			position:relative;
			margin-left:10px;
			width:60px;
			height:30px;
			color:black;
			cursor: pointer;
			border:1px solid white;
			border-radius:8px;
		}
		.loginBtn:hover{
			background-color:white;
		}
		#btnRest{
			position:relative;
			margin-left:50px;
		}
		#footer{
			width:100%;
			height:30px;
			color:white;
			position:relative;
			top:10px;
		}
		#footer span{
			position:relative;
			left:50%;
			margin-left:-85px;
			top:5px;
			font-size:12px;
		}
		#tips{
			border:3px dotted lightyellow;
			width:95px;
			border-radius:10px;
			height:30px;
			padding:20px;
			position:relative;
			font-size:22px;
			top:30px;
			left:40px;
			cursor: pointer;
		}
	</style>
  </head>
  
  <body>
	  <div id="mainContent">
		 <div id="rectangle">
		 	<div id="includeTable">
		 		<div id="tips">用户登录</div>
			  	<form action="doLogin" method="post">
			    	<table id="userInfo" align="center">
				    	<tr>
				    		<td>用户名：</td>
				    		<td><input type="text" name="userName" id="userName" value=${userName }></td>
				    	</tr>
				    	<tr>
				    		<td>密&nbsp;&nbsp;&nbsp;码：</td>
				    		<td><input type="password" name="pwd" id="pwd" value=${password }></td>
				    	</tr>
				    	<tr>
					    	<td><input type="submit" value="登录" class="loginBtn" /></td>
					    	<td><input type="reset" value="重置" class="loginBtn" id="btnRest" /></td>
				    	</tr>
				    	<tr>
				    		<td>&nbsp;</td>
					    	<td colspan="2">${error }</td>
				    	</tr>
			    	</table>
		    	</form>
			  </div>
		  </div>
		  <div id="footer"><span>版权所有：gan_jinan@qq.com</span></div>
	  </div>
  </body>
</html>
