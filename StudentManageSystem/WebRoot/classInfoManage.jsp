<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'classInfoManage.jsp' starting page</title>
    
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
	function searchClassName(){
		$("#class_table").datagrid("load",{
			//传参数
			className:$("#s_className").val()
		});
	}
	
	//删除数据
	function deleteClassData(){
		//获取所有的选中行（JQuery）
		var selectedRows=$("#class_table").datagrid("getSelections");
		if(selectedRows.length==0){
			//easyui提供的弹窗提示
			$.messager.alert("系统提示","请选择要删除的数据项！");
			return;
		}
		//获取选中的数据项的ID
		var getIDs=[];
		for(var i=0;i<selectedRows.length;i++){
			getIDs.push(selectedRows[i]._id);
		}
		//alert(getIDs);
		var strIDs=getIDs.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color='red' size='5px'>"+getIDs.length+"</font>条数据吗？",function(actionResponse){
			if(actionResponse){
				//json代码，第一个参数：请求的地址；第二：传递的参数；第三：返回一个result；第四："json"（所有的请求都是json格式）
				$.post("deleteClassServlet",{deleteIDs:strIDs},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color='red' size='5px'>"+result.deleteCount+"</font>项数据！");
						//删除成功则刷新数据
						$("#class_table").datagrid("reload");
					}else{
						$.messager.alert("系统提示",selectedRows[result.errorIndex].className+result.errorMsg);
					}
				},"json");
			}else{
				$.messager.alert("系统提示","Delete cancel!");
			}
		});
	}
	
	
	//定义一个添加信息时提交的地址URL（全局变量）
	var url;
	function openClassAddDialog(){
		$("#dialog_addClassInfo").dialog("open").dialog("setTitle","添加班级信息");
		//提交的地址
		url="doSaveClassDataServlet";
	}
	
	//对话框关闭
	function closeAddDialog(){
		$("#dialog_addClassInfo").dialog("close");
		//清空输入的内容
		$("#classNo").val("");
		$("#className").val("");
	}
	
	//保存按钮，增加班级信息
	function saveAddClass(){
		//使用easyUI封装的form方法异步提交数据
		//第一个参数：submit方法，第二个：url为异步提交的地址
		$("#MsgForm").form("submit",{
			url:url,
			onSubmit:function(){//在提交之前会执行
				if($("#classNo").val().length>10){
					$.messager.alert("系统提示","班级编号长度不能超过10个字符，请重新输入！");
					return;
				}if($("#className").val().length>20){
					$.messager.alert("系统提示","班级名称长度不能超过20个字符，请重新输入！");
					return;
				}
				return $(this).form("validate");//提交之前验证
			},
			success:function(result){
				if(result.errorMsg){//存在错误，添加失败
					$.messager.alert("系统提示",errorMsg);
					return;
				}else{//添加成功
					$.messager.alert("系统提示","数据保存成功！");
					//清空输入的内容
					$("#classNo").val("");
					$("#className").val("");
					//关闭对话框
					$("#dialog_addClassInfo").dialog("close");
					//刷新数据
					$("#class_table").datagrid("reload");
				}
			}
		});
	}
	
	//修改数据
	function modifyClassData(){
		//获取选中的项（返回的是一个集合）
		var selectedModifyRow=$("#class_table").datagrid("getSelections");
		if(selectedModifyRow.length!=1){
			//easyui提供的弹窗提示
			$.messager.alert("系统提示","请选择一项要修改的数据！");
			return;
		}
		//alert(selectedModifyRow[0]._id);
		var modifyRow=selectedModifyRow[0];//modifyRow为该集合的第一个元素对象
		//alert(modifyRow._id);
		$("#dialog_addClassInfo").dialog("open").dialog("setTitle","修改班级信息");
		//使用form的load方法将选中的modifyRow的属性加载到表单上
		$("#MsgForm").form("load",modifyRow);
		//传参数_id
		url="doSaveClassDataServlet?_id="+modifyRow._id;
	}
	</script>
	<style type="text/css">
		#dialog_addClassInfo{
			width:400px;
			height:400px;
			padding:10px 20px;
		}
	</style>
  </head>
  
  <body>
    <table title="班级信息管理" class="easyui-datagrid" id="class_table" fitColumns="true" 
    pagination="true" rowNumbers="true" url="classListServlet" method="post" toolbar="#tb">
	    <thead>
	    	<tr>
	    		<th field="ckbox" checkbox="true"></th>
	    		<th field="_id" hidden="true">编号</th>
	    		<th field="classNo" align="center" width="50px">班级编号</th>
	    		<th field="className" align="center" width="70px">班级名称</th>
	    	</tr>
	    </thead>
    </table>
	<div id="tb">
    	<div>
    		<a href="javascript:openClassAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
    		<a href="javascript:modifyClassData()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    		<a href="javascript:deleteClassData()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    	</div>
    	<div>
    	&nbsp;班级名称：&nbsp;<input type="text" id="s_className" name="s_className"><a href="javascript:searchClassName()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    	</div>
    </div>
    <!-- 使用easyUI的对话框 -->
    <div id="dialog_addClassInfo" buttons="#btn_dialog" class="easyui-dialog" closed="true">
    	<form method="post" id="MsgForm">
    		<table>
    			<tr>
    				<td>班级编号：</td>
    				<td><input type="text" name="classNo" id="classNo"  class="easyui-validatebox" required="true" /></td>
    			</tr>
    			<tr>
    				<td>班级名称：</td>
    				<td><input type="text" name="className" id="className"  class="easyui-validatebox" required="true" /></td>
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
