<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%
		String path = request.getContextPath();
		request.setAttribute("path", path);
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${path}/css/default/base.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/rightpart.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/icon.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/default/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path}/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/scripts/default_grid.js"></script>
<script src="${path}/jsLibs/jquery.metadata.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.validate.js" type="text/javascript"></script>
<script src="${path}/js/default_validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/scripts/dialog.js"></script>
<title>添加新目录页面</title>
</head>

<body>
<div class="w900">
		<div class="pd10">
			<span><input type="button" value="提交" name="" id="sumBtn" class="t_btnsty01" onclick="saveAddedResource();"></input></span>
		</div>
		<form id="resourceForm" name="resourceForm" method="post">
			<div class="nobortable pd10" align="left">
				<table width="530" border="0">
					<tr>
						<td class="tr" width="80"><span class="t_must">*</span><span class="t_span01">名称：</span></td>
						<td width="120"><input class="{required:true,messages:{required:'请输入类型名称'}} t_text w190"
							name="name"	id="name" value="" type="text"/></td>
						<td class="tr" width="80"><span class="t_span01">排序：</span></td>
						<td width="120"><input name="orderCode" class="t_text w190" id = "orderCode" type="text"/></td>
					</tr>
					<tr>
						<td class="tr" width="80"><span class="t_span01">是否启用：</span></td>
						<td width="120">
							<select id="isEnable" width="150" class="{required:true,messages:{required:'请选择是否启用'}} t_bor01 w193"
							 name="isEnable" >
								<option value="">请选择是否启用</option>
								<c:forEach items="${isEnableList}" var="obj">
                        			<option value="${obj.key}">${obj.value}</option>
                        		</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="tr" width="80"><span class="t_span01">图标：</span></td>
						<td width="120">
							<select id="iconCls" width="150" 
							 name="iconCls" >
								<option value=''>请选择</option>
								<option value='leafMenuIcon'>leafMenuIcon</option>
								<option value='bmModule'>bmModule</option>
							</select>
						</td>
						<td class="tr" width="80"><span class="t_span01">URL：</span></td>
						<td rowspan="3">
						<textarea  colspan="3" rows="3" id="url" name="url" style="width: 190px; height: 60px;"></textarea>
							<input name="parentId"	id="parentId" value="${parentId}" type="hidden"/>
						</td>
					</tr>
					
				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var validator;
		$(document).ready(function() {
			validator = $("#resourceForm").validate();
		});

		//提交保存类型
		function saveAddedResource() {
			if (checkInput()) {//先对页面输入进行验证，不通过验证不会发送提交请求
				$.ajax({
					url : "${path}/system/resource/saveAddedResourceModule.action",
					data : $("#resourceForm").serialize(),
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
		function checkInput() {
			  if(validator.checkForm()==false){
			  		 validator.showErrors();
			   			return false;
			  }else{
				  validator.showSuccess();
				  return true;
			 }
		}
		
		//
		function backBtn() {
			window.close();
		}
	</script>
</body>
</html>