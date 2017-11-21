<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		//定义一个全局变量url
		var url;
		function searchStudentInfo(){
		/*
			alert($("#s_stuNo").val());
			alert($("#s_stuName").val());
			alert($("#s_sex option:selected").val());
			alert($("#s_prebirth").datebox("getValue"));
			alert($("#s_laterbirth").datebox("getValue"));
			alert($("#s_classNo").combobox("getValue"));
			alert($("#s_className").combobox("getValue"));
		*/
			//获取各个输入框、下拉框的值并传送到后台
			$("#student_table").datagrid('load',{
				stuNo:$('#s_stuNo').val(),
				stuName:$('#s_stuName').val(),
				sex:$('#s_sex option:selected').val(),
				prebirth:$('#s_prebirth').datebox("getValue"),
				laterbirth:$('#s_laterbirth').datebox("getValue"),
				classNo:$('#s_classNo').combobox("getValue"),
				className:$('#s_className').combobox("getValue")
			});
		}
		//删除数据
		function deleteStudentData(){
			//获取所有的选中行（JQuery）
			var selectedRows=$("#student_table").datagrid("getSelections");
			if(selectedRows.length==0){
				//easyui提供的弹窗提示
				$.messager.alert("系统提示","请选择要删除的数据项！");
				return;
			}
			//获取选中的数据项的ID
			var getIDs=[];
			for(var i=0;i<selectedRows.length;i++){
				getIDs.push(selectedRows[i].S_id);
			}
			//alert(getIDs);
			var strIDs=getIDs.join(",");
			$.messager.confirm("系统提示","您确定要删除这<font color='red' size='5px'>"+getIDs.length+"</font>条数据吗？",function(actionResponse){
				if(actionResponse){
					//json代码，第一个参数：请求的地址；第二：传递的参数；第三：返回一个result；第四："json"（所有的请求都是json格式）
					$.post("deleteStudentServlet",{deleteIDs:strIDs},function(result){
						if(result.success){
							$.messager.alert("系统提示","您已成功删除<font color='red' size='5px'>"+result.deleteCount+"</font>项数据！");
							//删除成功则刷新数据
							$("#student_table").datagrid("reload");
						}else{
							$.messager.alert("系统提示",result.errorMsg);
						}
					},"json");
				}else{
					$.messager.alert("系统提示","Delete cancel!");
				}
			});
		}
		//打开添加或修改对话框
		function openStudentAddDialog(){
			$("#dialog_addStudentInfo").dialog("open").dialog("setTitle","添加学生信息");
			//提交的地址
			url="doSaveStudentServlet";
		}
		
		//保存按钮，增加学生信息
		function saveAddClass(){
			//使用easyUI封装的form方法异步提交数据
			//第一个参数：submit方法，第二个：url为异步提交的地址
			$("#MsgForm").form("submit",{
				url:url,
				onSubmit:function(){//在提交之前会执行
					validDataConfirm();
					return $(this).form("validate");//提交之前验证
				},
				success:function(result){
					if(result.errorMsg){//存在错误，添加失败
						$.messager.alert("系统提示",errorMsg);
						return;
					}else{//添加成功
						$.messager.alert("系统提示","数据保存成功！");
						//清空输入的内容
						cleanValue();
						//关闭对话框
						$("#dialog_addStudentInfo").dialog("close");
						//刷新数据
						$("#student_table").datagrid("reload");
					}
				}
			});
		}
		
		//对话框关闭
		function closeAddDialog(){
			$("#dialog_addStudentInfo").dialog("close");
			//清空输入的内容
			cleanValue();
		}
		function cleanValue(){
			//清空输入的内容
			$("#Sno").val("");
			$("#Sname").val("");
			$("#sex").combobox("setValue","");
			$("#className").combobox("setValue","");
			$("#birth").datebox("setValue","");
			$("#email").val("");
			$("#phone").val("");
		}
		//修改数据
		function modifyStudentData(){
			//获取选中的项（返回的是一个集合）
			var selectedModifyRow=$("#student_table").datagrid("getSelections");
			if(selectedModifyRow.length!=1){
				//easyui提供的弹窗提示
				$.messager.alert("系统提示","请选择一项要修改的数据！");
				return;
			}
			//alert(selectedModifyRow[0]._id);
			var modifyRow=selectedModifyRow[0];//modifyRow为该集合的第一个元素对象
			//alert(modifyRow.S_id);
			$("#dialog_addStudentInfo").dialog("open").dialog("setTitle","修改学生信息");
			//使用form的load方法将选中的modifyRow的属性加载到表单上
			$("#MsgForm").form("load",modifyRow);
			//传参数_id
			url="doSaveStudentServlet?S_id="+modifyRow.S_id;
		}
		function validDataConfirm(){
			if($("#Sno").val().length!=4){
				$.messager.alert("系统提示","学号长度只能为4个字符，请重新输入！");
				return;
			}else if($("#Sname").val().length>20){
				$.messager.alert("系统提示","姓名长度不能超过20个字符，请重新输入！");
				return;
			}else if($("#email").val().length>40){
				$.messager.alert("系统提示","电子邮箱长度不能超过40个字符，请重新输入！");
				return;
			}else if($("#phone").val().length>20){
				$.messager.alert("系统提示","号码长度不能超过20个字符，请重新输入！");
				return;
			}
		}
	</script>
		<style type="text/css">
		#dialog_addStudentInfo{
			width:500px;
			height:500px;
			padding:10px 20px;
		}
		#student_table{
			position:relative;
			margin:15px;
		}
	</style>

  </head>
  
  <body>
   <table title="学生信息管理" class="easyui-datagrid" id="student_table" fitColumns="true" 
    pagination="true" rowNumbers="true" url="studentListServlet" method="post" toolbar="#tb">
	    <thead>
	    	<tr>
	    		<th field="ckbox" checkbox="true" width="25px"></th>
	    		<th field="S_id" align="center" hidden="true">编号</th>
	    		<th field="Sno" align="center" width="45px">学号</th>
	    		<th field="Sname" align="center" width="45px">姓名</th>
	    		<th field="sex" align="center" width="25px">性别</th>
	    		<th field="classNo" align="center" width="45px">班级编号</th>
	    		<th field="className" align="center" width="75px">班级名称</th>
	    		<th field="birth" align="center" width="75px">出生日期</th>
	    		<th field="email" align="center" width="85px">电子邮箱</th>
	    		<th field="phone" align="center" width="85px">电话号码</th>
	    	</tr>
	    </thead>
    </table>
	<div id="tb">
    	<div>
    		<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
    		<a href="javascript:modifyStudentData()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    		<a href="javascript:deleteStudentData()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    	</div>
    	<div>
    	&nbsp;学号：<input type="text" id="s_stuNo" name="s_stuNo" size="5">
    	姓名：<input type="text"  id="s_stuName" name="s_stuName" size="7">
    	性别：<select editable="false" id="s_sex" name="s_sex" ><option value="">请选择</option><option value="男">男</option><option value="女">女</option></select>
    	出生日期：<input class="easyui-datebox" name="s_prebirth" id="s_prebirth" size="10" editable="false"/>至<input class="easyui-datebox" name="s_laterbirth" id="s_laterbirth" size="10" editable="false"/>
    	<!-- 取异步数据 -->
    	年级：<input class="easyui-combobox" size="6" name="s_classNo" id="s_classNo" data-options="editable:false,valueField:'classNo',textField:'classNo',url:'asyReadDataServlet'"/>
    	班级名称：<input class="easyui-combobox" size="15" name="s_className" id="s_className" data-options="editable:false,valueField:'className',textField:'className',url:'asyReadDataServlet'"/>
    	<a href="javascript:searchStudentInfo()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    	</div>
    </div>
    <!-- 使用easyUI的对话框 -->
    <div id="dialog_addStudentInfo" buttons="#btn_dialog" class="easyui-dialog" closed="true">
    	<form method="post" id="MsgForm">
    		<table>
    			<tr>
    				<td>&nbsp;学&nbsp;&nbsp;号：&nbsp;<input type="text" name="Sno" id="Sno"  class="easyui-validatebox" required="true" /></td>
    				<td>&nbsp;</td>
    				<td>&nbsp;姓&nbsp;&nbsp;名：&nbsp;<input type="text" name="Sname" id="Sname"  class="easyui-validatebox" required="true" /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;性&nbsp;&nbsp;别：&nbsp;<select class="easyui-combobox" editable="false" id="sex" name="sex" panelHeight="auto" style="width:155px;"><option value="">请选择</option><option value="男">男</option><option value="女">女</option></select></td>
    				<td>&nbsp;</td>
    				<td>出生日期：<input class="easyui-datebox" name="birth" id="birth" editable="false" style="width:155px;"/></td>
    			</tr>
    			<tr>
    				<td>班级名称：<input class="easyui-combobox" name="className" id="className" data-options="editable:false,valueField:'className',textField:'className',url:'asyReadDataServlet'" style="width:155px;"/></td>
    				<td>&nbsp;</td>
    				<td>电子邮箱：<input type="text" name="email" id="email" class="easyui-validatebox" validType="email"/></td>
    			</tr>
    			<tr>
    				<td>电话号码：<input type="text" name="phone" id="phone"  class="easyui-validatebox"/></td>
    				<td>&nbsp;</td>
    				<td></td>
    			</tr>
    		</table>
    	</form>
    </div>
    <div id="btn_dialog">
    	<a href="javascript:saveAddClass()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
    	<a href="javascript:closeAddDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">退出</a>
    </div>

  </body>
</html>
