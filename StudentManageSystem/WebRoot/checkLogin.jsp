<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>验证登录</title>
    <%
    //权限验证
    if(session.getAttribute("currentUser")==null){
    	response.sendRedirect("index.jsp");
    	return;
    }
     %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

	<style type="text/css">
	*{  font-family: 微软雅黑;
		padding: 0;
		margin: 0;  }
	#s-header{
		height:80px;
		text-align:center;
		padding-top:14px;
		background-color:rgb(224,236,255);
	}
	#mainHeader{
		font-size:24px;
	}
	#s-nav{
		width:200px;
		text-align:center;
	}
	#s-footer{
		height:30px;
		font-size:12px;
		text-align:center;
		padding-top:8px;
		background-color:rgb(224,236,255);
	}
	#displayMessage{
		float:right;
		position:relative;
		margin-right:100px;
		border:1px solid red;
		font-size:12px;
	}
	
	#fisttab{
		background:url("images/b6.jpg");
		background-position:0px -220px;
		background-repeat: no-repeat;
        background-size: cover;
	}
	#ft{
		position:relative;
		margin-top:20px;
		margin-left:20px;
		width:1290px;
		height:505px;
		background-color:rgba(0,0,0,0.15);
	}
	#ft_surface1{
		position:relative;
		top:40px;
		left:20px;
		font-size:25px;
		width:195px;
		height:50px;
		padding-top:15px;
		padding-left:20px;
		background:lightblue;
		color:white;
	}
	</style>
	<script type="text/javascript">
		$(function(){
			var navData=[{
				text:"信息管理",
				children:[
					{text:"学生信息管理",
					attributes:{url:"studentInfoManage.jsp"}},
					
					{text:"班级信息管理",
					attributes:{url:"classInfoManage.jsp"}},
					
					{text:"课程信息管理",
					attributes:{url:"courseInfoManage.jsp"}},
					
					{text:"成绩信息管理",
					attributes:{url:"gradeInfoManage.jsp"}}
				]
			}];
			//实例化
			$("#tree").tree({
				data:navData,
				lines:true,
				onClick:function(node){
					if(node.attributes){
						openTab(node.text,node.attributes.url);
					}
				}
			});
			
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
				$("#tabs").tabs('add',{
					title:text,
					closable:true,
					content:content
				});
			}
		}
		});	
	</script>
  </head>
  
  <body class="easyui-layout">
  	<div region="north" id="s-header">
  		<div id=mainHeader>学生信息管理系统</div>
  		<div id="displayMessage">
  			<span><image src="images/home.png" alt="当前用户" width="30px" height="30px"/>当前用户：${currentUser.username }</span>
  			<span><image src="images/clock.png" alt="当前时间" width="30px" height="30px"/>当前时间：</span>
  			</div>
  	</div>
  	<div region="center" id="main-center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
  			<div title="首页" id="fisttab">
  			<div id="ft">
  				<div id="ft_surface1">欢迎进入系统...</div>
  			</div>
  			</div>
  		</div>
	</div>
  	<div region="west" id="s-nav" title="导航菜单" split="true">
  		<ul id="tree"></ul>
  	</div>
  	<div region="south" id="s-footer">版权所有：gan_jinan@qq.com</div>
  </body>
</html>
