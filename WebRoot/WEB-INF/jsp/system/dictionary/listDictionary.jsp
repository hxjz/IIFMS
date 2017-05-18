<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>枚举信息管理</title>
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>

<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="${path }/scripts/validatebox-ruls.js"></script>

<script type="text/javascript" src="${path }/scripts/date.js"></script>
</head>

<body>
	<form method="post" id="searchForm">
		<div class="easyui-panel" title="查询" style="width:100%;padding:10px 60px 20px 60px" >
			enum_name:<input type="text" name="filter_and_enumName_LIKE_S" class="textbox" >
			value:<input type="text" name="filter_and_value_LIKE_S" class="textbox" >
			createTime:<input class="easyui-datebox" editable="false" type="text" name="filter_and_createTime_GT_T" >to<input class="easyui-datebox" editable="false" type="text" name="filter_and_createTime_LT_T">
			<input type="button" class="searchbox-button" id="find" >
		</div>
	</form>
	
		<table 	id="dg1"
			title="枚举详细信息"
			style="width:100%;height:350px"
			fitColumns='true'
			toolbar='#toolbar'
            data-options="singleSelect:true,collapsible:false,
            url:'${path }/system/dictionary.action?method=showDictionary',
            method:'post',
            rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageNumber:1,
			pageSize:50,
			pageList:[10,30,50] ">
        <thead>
            <tr>
                <th data-options="field:'id',width:320" align="center">ID</th>
                <th data-options="field:'key',width:100" align="center">key</th>
                <th data-options="field:'value',width:200" align="center">value</th>
                <th data-options="field:'enumName',width:200" align="center">enumName</th>
                <th data-options="field:'isDel',width:200" align="center">isDel</th>
                <th data-options="field:'index',width:200" align="center">index</th>
                <th data-options="field:'createTime',width:200" align="center">createTime</th>
                <th data-options="field:'updateTime',width:200" align="center">updateTime</th>
                <th data-options="field:'parentId',width:320" align="center">parentId</th>
            </tr>
        </thead>
    </table>
    <!-- 按钮 -->
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#dicAdd').window('open');">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroy()">删除</a>
    </div>
    
    <!-- 添加弹窗 -->
    <div id="dicAdd" class="easyui-window" title="添加枚举" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:380px;height:400px;padding:10px;">
        <form id="form1" method="post">
	        <table border="0">
	        	<tr>
	        		<td>key</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="key" data-options="required:true,missingMessage:'key不能为空'">*</td>
	        	</tr>
	        	<tr>
	        		<td>value</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="value" data-options="required:true"></td>
	        	</tr>
	        	<tr>
	        		<td>enumName</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="enumName" data-options="required:true"></td>
	        	</tr>
	        	<tr>
	        		<td>isDel</td>
	        		<td><input type="text" value="0" class="easyui-validatebox textbox" name="idDel"></td>
	        	</tr>
	        	<tr>
	        		<td>index</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="index" data-options="required:true,validType:'integer'"></td>
	        	</tr>
	        	<tr>
	        		<td>parentId</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="parentId"></td>
	        	</tr>
	        	<tr>
	        		<td><input type="submit" value="确认添加"></td>
	        		<td><input type="reset" value="重置"></td>
	        	</tr>
	        </table>
        </form>
    </div>
    <script type="text/javascript">
    	$(function(){
    		createTable('dg1');
    	});
    	
    	//表单提交  
	    $('#form1').form({  
	        url:'${path}/system/dictionary.action?method=addDictionary',  
	        onSubmit:function(){  
	            return $(this).form('validate');
	        },
	        success:function(data){  
	        	alert("成功");
	        	$('#dicAdd').window('close');
	        	reloadgrid('dg1');  // 重新加载datagrid
	        }
	    });
    </script>
</body>
</html>
