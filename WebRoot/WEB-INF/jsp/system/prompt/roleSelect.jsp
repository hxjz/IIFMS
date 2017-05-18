<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色选择</title>
<link href="${path}/css/default/base.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/css/default/rightpart.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/css/default/tab.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/css/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/css/default/icon.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${path}/js/default_grid.js"></script>
</head>
<body>
	<div >
		<div class="t_oneblock w400">
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr ">
				<div class="nobortable pt10 pl10">
					<form>
						<table width="" border="0">
							<tr>
								<td class="tr"><span class="t_span01">角色名称：</span>
								</td>
								<td class='w110'>
								<input class="t_text w100" name="filter_and_name_LIKE_S" type="text" />
								<input class="t_text w100" name="filter_and_isDel_EQ_I" value="0" type="hidden"/>
								</td>
								<td class="tr">
									<input class="t_btnsty01" name="" id="find"
										type="button" value="查询" />
									
									<input class="t_btnsty01" name="" id="reset"
										type="reset" value="重置" />
																		
								</td>
							</tr>
						</table>
					</form>
				</div>
				<!--nobortable -->
				<div class="pl20 pt10">
					<span><input class="t_btnsty01" name="" type="button" value="确认" onclick="selectBack()" /> </span> 
					<span><input class="t_btnsty01" name="" type="button" value="返回" onclick="parent.closeEasyuiWindowBoxForRole();" /> </span>
				</div>
				<div class="hastable mt10">
					<table id="dg" style="width:400px;height:300px" data-options="
						rownumbers:false,
						singleSelect:true,
						autoRowHeight:true,
						nowrap:false,
						onDblClickRow:selectBack,
						url:'${path}/orgMgt/smsRole/listAllRole.action'">
							<thead>
								<tr>
									<th field="name" width="398" align="center">角色名称</th>
								</tr>
							</thead>
					</table>
				</div>
			</div>
			<!-- t_oneblock_c-->
		</div>
	</div>
	<!--t_rightcontainer -->
	<script>

	//选择后确认返回
	function selectBack(){
		var rows = $('#dg').datagrid('getSelections');
		if(rows.length) {
			parent.closeEasyuiWindowBoxForRoleAfterSelect(rows[0]);
		} else {
			alertWarning(OPRATION_PLEASE_CHOOSE_ONE);
		}
	}

	$(document).ready(function(){
		createTable('dg');
	});
	</script>
</body>
</html>