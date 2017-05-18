<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%
		String path = request.getContextPath();
		request.setAttribute("path", path);
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="${path}/css/default/base.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/rightpart.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/icon.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script src="${path}/jsLibs/jquery.metadata.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.validate.js" type="text/javascript"></script>
<script src="${path}/js/default_validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/scripts/dialog.js"></script>
<title>添加新角色页面</title>
</head>

<body>
	<div class="w900">
		<form id="roleForm" method="post">
		<div class="pd10">
			<span><input type="button" value="提交" name="" id="sumBtn" class="t_btnsty01" onclick="saveAddedRole()"></input></span>
			<span><input class="t_btnsty01" name="" type="reset" value="重置"/></span>
		</div>
			<div class="nobortable pd10" align="left">
				<table width="530" border="0">
					<tr>
						<td class="tr" width="80"><span class="t_span01">角色名称：</span></td>
						<td width="120"><input
							class="{required:true,messages:{required:'请输入角色名称'}} t_text w190"
							name="name" id="name" type="text" /></td>
						<td class="tr" width="80"><span class="t_span01">是否启用：</span></td>
						<td width="120"><select id="isEnable"
							class="{required:true,messages:{required:'请选择是否启用'}} t_bor01 w123"
							name="isEnable">
								<option value="">请选择是否启用</option>
								<c:forEach items="${isEnableList}" var="obj">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var validator;
		$(document).ready(function() {
			validator = $("#roleForm").validate();
		});
		
		//提交保存类型
		function saveAddedRole() {
			if (checkInput()) {//先对页面输入进行验证，不通过验证不会发送提交请求
				$.ajax({
					url : "${path}/orgMgt/role/saveAddedRole.action",
					data : $("#roleForm").serialize(),
					type : "post",
					success : function(result) {
						var txt = $.parseJSON(result);
						if (txt.status == "success") {
							alertInfo(OPRATION_SUCCESS);
							parent.closeEasyuiWindowBoxAfterEdit();					
						}else{
							alertWarning(OPRATION_FAILED);
						}
					}
				});
			}
		}

		//验证类型输入 
		function checkInput(){
			  if(validator.checkForm()==false){
				   validator.showErrors();
				   return false;
			  }
			  validator.showSuccess();
			  return true;
		}
	</script>
</body>
</html>