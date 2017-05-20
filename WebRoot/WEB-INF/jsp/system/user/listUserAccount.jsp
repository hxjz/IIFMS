<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录账户管理</title>
<link href="${picPath}/css/default/base.css" rel="stylesheet" type="text/css" />
<link href="${picPath}/css/default/rightpart.css" rel="stylesheet" type="text/css" />
<link href="${picPath}/css/default/tab.css" rel="stylesheet" type="text/css" />
<link href="${picPath}/css/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${picPath}/css/default/icon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="${path }/scripts/validatebox-ruls.js"></script>
<script type="text/javascript" src="${path }/scripts/date.js"></script>
<script type="text/javascript" src="${path }/scripts/dialog.js"></script>
<script src="${path }/jsLibs/dtree/wtree.js" type="text/javascript"></script>
<script src="${path }/jsLibs/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" >
if(parent.getCurrentTabId) {
	tabId = parent.getCurrentTabId();
}
if(parent.getfrmTabId){
	frmTabId = parent.getfrmTabId();
}
</script>
</head>
<body>
	<div class="t_rightcontainer">
		<div class="t_seartablebox">
			<div class="t_seartablebox_c">
				<form>
					<div class="t_searbox">
						<div class="nobortable pd10">
							<table width="800" border="0">
								<tr>
									<td width="100" class="tr"><span class="t_span01">账户名称：</span></td>
									<td width="120">
									<input class="t_text w100" name="filter_and_userAccount_LIKE_S" type="text" />
									</td>
									<td width="100" class="tr"><span class="t_span01">用户名称：</span></td>
									<td width="120"><input class="t_text w100"
										name="filter_and_linkEmployeeId__name_LIKE_S" type="text" /></td>
									<!-- <td width="100" class="tr"><span class="t_span01">账户状态：</span></td>
									<td width="120"><select class="w100" id="supplierSel"
										name="filter_and_acctStatus_EQ_I">
											<option value=''>请选择</option>
											<option value="0">有效</option>
											<option value="1">锁定</option>
											<option value="2">失效</option>
									</select></td> -->
								</tr>
							</table>
						</div>
						<!--nobortable -->
						<div class="t_btnbox01">
							<span>
							<input class="t_btnsty01" id="find" type="button" value="查询" />
							</span> 
							<span>
							<input class="t_btnsty01" name="" type="reset" value="重置" /></span>
						</div>
					</div>
					<!--t_searbox -->
					<!-- <div class="pl20 pt10 pb10">
						<span><input class="t_btnsty01" name="" type="button"
							value="新增" onclick="toAdd()" /></span>
					</div> -->
				</form>
				<div class="hastable hgt_sty01">
					<table id="dg"
						data-options="
						rownumbers:true,
                        fit:true,
						autoRowHeight:true,
						pagination:true,
						pageNumber:1,
						nowrap:false,
						pageSize:50,
						url:'${path}/orgMgt/userAccount/showAll.action',
						pageList:[10,30,50]">
						<thead>
							<tr>
								<th field="handle" width="80" align="center" data-options="formatter:formatHandle">操作</th>
								<th field="name" width="200" align="center">名称</th>
								<th field="account" width="150" align="center">账户</th>
								<th field="dept" width="150" align="center">部门</th>
								<th field="roles" width="150" align="center">角色</th>
								<th field="id" data-options="hidden:true"></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!--t_seartablebox_c -->
		</div>
		<!--t_seartablebox -->
		<div id="easyuiWindowBox" class="easyui-window"
			data-options="modal:true,closed:true,minimizable:false,maximizable:false,collapsible:false"
			style="padding: 10px; overflow: hidden;">
			<iframe id="frame_content" src="" scrolling="no" frameborder="0" width="900" height="300"></iframe>
		</div>
	</div>
	<!--t_rightcontainer -->

	<script type="text/javascript">
		function formatHandle(value, rowData, rowIndex) {
			var str = '<a href="javascript:void(0)"   onclick="toEdit(\'' + rowData.id + '\')">修改角色</a>&nbsp;'
			return str;
		};

		// 编辑用户、授权
		function toEdit(id) {
			$("#frame_content").attr('width', '446');
			$("#frame_content").attr('height', '420');
			$("#frame_content").attr("src", "${path}/orgMgt/userAccount/toEditUserAccount.action?userId="+id);
			$('#easyuiWindowBox').window({
				width : 476,
				title : "用户授权"
			});
			$('#easyuiWindowBox').window('open');
			adjustTanboxCenter();
		};
		
		$(document).ready(function() {
			createTable('dg');
		});
	</script>
</body>
</html>

