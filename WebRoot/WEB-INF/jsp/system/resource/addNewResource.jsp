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
<title>添加新子菜单页面</title>
</head>

<body>
	<div class="w900">
		<div class="pd10">
			<span><input type="button" value="提交" name="" id="sumBtn"
				class="t_btnsty01" onclick="saveAddedResource()"></input></span>
		</div>
		<form id="resourceForm" method="post">
			<div class="nobortable pd10" align="left">
				<table width="530" border="0">
					<tr>
						<td class="tr" width="80"><span class="t_span01">上级目录：</span></td>
						<td width="120"><input class="t_text w190" id="parentResourceName" readonly="readonly" name="parentResourceName" type="text" /> 
						<input class="t_text w190" id="parentResourceId" name="parentId" type="hidden" /></td>
						<td class="tr" width="80"><span class="t_span01">模块名称：</span></td>
						<td width="120"><input
							class="{required:true,messages:{required:'请输入模块名称'}} t_text w190"
							name="name" id="name" type="text" /></td>
					</tr>
					<tr>
						<td class="tr" width="80"><span class="t_span01">是否启用：</span></td>
						<td width="120"><select id="isEnable"
							class="{required:true,messages:{required:'请选择是否启用'}} t_bor01 w123"
							name="isEnable">
								<option value="">请选择是否启用</option>
								<c:forEach items="${isEnableList}" var="obj">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select></td>
						<td class="tr" width="80"><span class="t_span01">排序：</span></td>
						<td width="120"><input name="orderCode" id="orderCode" type="text" class="t_text w190"/></td>

					</tr>
					<tr>
						<td class="tr" width="80"><span class="t_span01">是否按钮：</span></td>
						<td width="120"><select id="isBtn" width="150"
							class="{required:true,messages:{required:'请选择'}} t_bor01 w193"
							name="isButton">
								<option value=''>请选择</option>
								<c:forEach items="${isEnableList}" var="obj">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select></td>
						<td class="tr" width="80"><span class="t_span01">是否换行：</span></td>
						<td width="120"><select id="isLine" width="150" name="resource.isLine" class="{required:true,messages:{required:'请选择'}} t_bor01 w193">
								<option value=''>请选择</option>
								<c:forEach items="${isEnableList}" var="obj">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select></td>

					</tr>
					<tr>
						<td class="tr" width="80"><span class="t_span01">选择图标：</span></td>
						<td width="120"><select id="iconCls" width="150"
							name="iconCls">
								<option value=''>请选择</option>
								<option value='leafMenuIcon'>leafMenuIcon</option>
								<option value='bmModule'>bmModule</option>
						</select></td>
						<td class="tr" width="80">URL：</td>
						<td rowspan="3"><textarea cols="3" rows="3" id="url" class="{required:true,messages:{required:'请输入URL'}} t_text w190"
								name="url" style="width: 190px; height: 60px;"></textarea></td>
					</tr>

				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var validator;
		$(document).ready(function() {
			validator = $("#resourceForm").validate();
			getParentIdfromParentPage();//从父页面获取parentId
		});
		
		function getParentIdfromParentPage() {
			var parentId = parent.parentId;
			$("#parentResourceId").val(parentId);
			//如果存在刚去数据库里查询上级目录名称并显示到父类input中
			$.ajax({
				url : "${path}/system/resource/getParentResourceName.action",
				data : {
					"parentId" : parentId
				},
				type : "post",
				success : function(result) {
					var txt = $.parseJSON(result);
					$("#parentResourceName").val(decodeURI(txt.data));
				}
			});
			
		}
		//返回按钮用来关闭当前页面！
		function backBtn() {
			parent.closeTab(tabId, frmTabId);
		}
		
		//提交保存类型
		function saveAddedResource() {
			if (checkInput()) {//先对页面输入进行验证，不通过验证不会发送提交请求
				$.ajax({
					url : "${path}/system/resource/saveAddedResource.action",
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