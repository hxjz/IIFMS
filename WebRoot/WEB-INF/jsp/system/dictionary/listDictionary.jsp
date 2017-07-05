<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>数据字典管理</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>

<body>
	<form method="post" id="searchForm">
		<div class="t_oneblock_h">
			<h3>数据字典查询</h3>
		</div>		<div class="t_oneblock_c pr">
			<div class="nobortable pt10 pl10">
				<table width="100%" border="0">
					<tr>
						<td class="tr" width="100"><span class="t_span01">下拉菜单类型：</span></td>
						<td>
							<select class="w140" name="filter_and_enumName_EQ_S" >
								<option value="">请选择</option>
								<c:forEach items="${systemTypeList}" var="system">
									<option value="${system.key}">${system.value}</option>
								</c:forEach>
							</select>
						</td>
						<td class="tr" width="100"><span class="t_span01">下拉菜单值：</span></td>
						<td><input class="t_text w120" name="filter_and_value_LIKE_S" type="text"/></td>
						<td clazss="tr" width="100"><span class="t_span01">创建时间：</span></td>
						<td colspan="3" width="260">
							<input class="easyui-datebox t_text w100" editable="false" name="filter_and_createTime_GE_T" type="text" /> 
							<span class="t_span01">至</span> 
							<input class="easyui-datebox t_text w100" editable="false" name="filter_and_createTime_LT_T" type="text" /></td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<input class="t_text w120" name="filter_and_isDel_EQ_I" type="hidden" value="0"/>
							<input class="t_text w120" name="filter_and_enumName_NEQ_S" type="hidden" value="SystemTypeEnum"/>
							<input class="t_btnsty01" id="find" name="select" type="button" value="查询"/>
							<input class="t_btnsty01" type="reset" value="重置"/>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<!--nobortable -->
		</div>
	</form>
	
		<table 	id="dg1"
			title="下拉菜单详细信息"
			style="width:100%;height:350px"
			fitColumns='true'
			toolbar='#toolbar'
            data-options="singleSelect:true,collapsible:false,
            url:'${path }/system/showDictionary.action',
            method:'post',
            rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageNumber:1,
			pageSize:50,
			pageList:[10,30,50] ">
        <thead>
            <tr>
                <th data-options="hidden:true" align="center">ID</th>
                <th data-options="field:'key',width:120" align="center">下拉菜单键</th>
                <th data-options="field:'value',width:200" align="center">下拉菜单值</th>
                <th data-options="field:'enumName',formatter:formatEnumType,width:200" align="center">下拉菜单类型</th>
                <th data-options="field:'isDel',width:100" align="center">删除标示</th>
                <th data-options="field:'index',width:100" align="center">排序号</th>
                <th data-options="field:'parentId',width:100" align="center">父级Id</th>
            </tr>
        </thead>
    </table>
    <!-- 按钮 -->
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#dicAdd').window('open');">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="toDelete();">删除</a>
    </div>
    <!-- 添加弹窗 -->
    <div id="dicAdd" class="easyui-window" title="添加数据字典" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:380px;height:400px;padding:10px;">
        <form id="form1" method="post">
	        <table border="0">
	        	<tr>
	        		<td>下拉菜单键</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="key" data-options="required:true,missingMessage:'key不能为空'"></td>
	        	</tr>
	        	<tr>
	        		<td>下拉菜单值</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="value" data-options="required:true"></td>
	        	</tr>
	        	<tr>
	        		<td>下拉菜单类型</td>
	        		<td>
						<%--<input type="text" class="easyui-validatebox textbox" name="enumName" data-options="required:true">--%>
						<select class="easyui-validatebox w170" name="enumName" data-options="required:true" >
							<option value="">请选择</option>
							<c:forEach items="${systemTypeList}" var="system">
								<option value="${system.key}">${system.value}</option>
							</c:forEach>
						</select>
					</td>
	        	</tr>
	        	<tr>
	        		<td>删除标示</td>
	        		<td><input type="text" value="0" class="easyui-validatebox textbox" name="idDel"></td>
	        	</tr>
	        	<tr>
	        		<td>排序号</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="index" data-options="required:true,validType:'integer'"></td>
	        	</tr>
	        	<tr>
	        		<td>父级Id</td>
	        		<td><input type="text" class="easyui-validatebox textbox" name="parentId"></td>
	        	</tr>
	        	<tr>
	        		<td><input class="t_btnsty02" type="submit" value="确认添加"></td>
	        		<td><input class="t_btnsty01" type="reset" value="重置"></td>
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
	        url:'${path}/system/addDictionary.action',  
	        onSubmit:function(){
	            return $(this).form('validate');
	        },
	        success:function(data){  
	        	alert("成功");
	        	$('#dicAdd').window('close');
	        	reloadgrid('dg1');  // 重新加载datagrid
	        }
	    });
    	
	 	// 删除处理
		function toDelete() {
			var row = $('#dg1').datagrid('getSelected');
			if(null == row) {
				alertInfo("请选择要删除的案件!");
				return;
			}
			$.messager.confirm("确认提示", "确定删除该条案件信息吗？", function(r) {
				if (r) {
					// 提交删除请求
					$.ajax({
						url : "${path}/system/delDictionary.action",
						data : {
							'id' : row.id
						},
						dataType : "json",
						type : "post",
						success : function(result) {
							if (result.status == "success") {
								alertInfo(result.data);
								reloadgrid('dg1');
							} else {
								alertInfo(result.data);
							}
						}
					});
				}
			});
		}
    </script>
</body>
</html>
