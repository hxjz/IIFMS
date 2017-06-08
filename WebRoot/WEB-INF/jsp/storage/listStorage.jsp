<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>存储位置管理</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>

<body>
	<form method="post" id="searchForm">
		<div class="t_oneblock_h">
			<h3>存储位置查询</h3>
		</div>		
		<div class="t_oneblock_c pr">
			<div class="nobortable pt10 pl10">
				<table width="100%" border="0">
					<tr>
						<td class="tr" width="100"><span class="t_span01">存储区/室：</span></td>
						<td>
							<select name="room" style="width: 120">
								<option value="">请选择</option>
								<%--<option value="1">手迹痕迹</option>--%>
								<%--<option value="2">足迹痕迹</option>--%>
								<%--<option value="3">其他</option>--%>
								<c:forEach items="${financeTypeList}" var="object">
									<option value="${object.key}">${object.value}</option>
								</c:forEach>
							</select>
						</td>
						<td class="tr" width="100"><span class="t_span01">排：</span></td>
						<td>
							<select name="filter_and_financeState_EQ_I" style="width: 120">
							<option value="">请选择</option>
							<option value="1">登记</option>
							<option value="2">在库</option>
							<option value="3">出库</option>
						</td>
						<td class="tr" width="100"><span class="t_span01">列：</span></td>
						<td>
							<select name="filter_and_financeState_EQ_I" style="width: 120">
							<option value="">请选择</option>
							<option value="1">登记</option>
							<option value="2">在库</option>
							<option value="3">出库</option>
						</td>
						<td clazss="tr" width="100"><span class="t_span01">门：</span></td>
						<td>
							<select name="filter_and_financeState_EQ_I" style="width: 120">
							<option value="">请选择</option>
							<option value="1">登记</option>
							<option value="2">在库</option>
							<option value="3">出库</option>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<input class="t_text w120" name="filter_and_isDel_EQ_I" type="hidden" value="0"/>
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
			title="存储位置详细信息"
			style="width:100%;height:350px"
			fitColumns='true'
			toolbar='#toolbar'
            data-options="singleSelect:true,collapsible:false,
            url:'${path }/storage/showStorage.action',
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
                <th data-options="field:'key',width:120" align="center">显示名</th>
                <th data-options="field:'showName',width:120" align="center">显示名</th>
                <th data-options="field:'storageStatus',width:200" align="center">存储状态</th>
                <th data-options="field:'isAvail',width:200" align="center">存储状态</th>
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
    <script type="text/javascript">
    	$(function(){
    		createTable('dg1');
    	});
    	
    	//表单提交  
	    $('#form1').form({  
	        url:'${path}/system/addStorage.action',  
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
						url : "${path}/system/delStorage.action",
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
	 	
		function toChooseStorage(){
			// 提交打印请求
			$.ajax({
				url : "${path}/storage/toChooseStorage.action",
				data : {
				},
				dataType : "json",
				type : "post",
				success : function(result) {
					if (result.status == "success") {
						var me = result.data.storage[0];
						request.setAttribute("storageList",result.data.storage);
						//alertInfo(JSON.stringify(result.data.storage));
						//data: "storage1=" + JSON.stringify(result.data);
						alertInfo(me.showName);
						//$("input[name = choose_test]").attr("value",storage1.id);
					} else {
						alertInfo(result.data);
					}
				}
			});
		}
    </script>
</body>
</html>
