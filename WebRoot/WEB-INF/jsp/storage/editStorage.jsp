<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>>新增/修改存储页</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
	<form id="chooseStorage" name="chooseStorageForm" method="post">
	
		<input type="submit" class="t_btnsty02" id="saveBtn" value="保存"/>
		<input type="button" id="cancel" class="t_btnsty02" onclick="closeStorage();" value="关闭"/>
		<input type="hidden" name="id" id="id" value="${storage.id}"/>
		<input type="hidden" name="level" id="level" value="${storage.level}"/>
		<br>
		
		<table border="0">
	          <tr>
	    		   <td><span class="t_span01">存储类型：</span></td>
				   <td>
				   		<select class="w120" name="type" id="type">
					   		<option value="">请选择</option>
							<c:forEach items="${storageTypeList}" var="obj">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
                 		</select>
	               </td>
	               <td><span class="t_span01">存储名称：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w140" data-options="required:true,missingMessage:'请输入存储名称'" name="name" type="text" value="${storage.name}" /><span class="t_span02">*</span>
	               </td>
	          </tr>
	          <tr>
	          	<td><span class="t_span01">一级显示：</span></td>
	          	<td>
                 	<select class="w120" name="caseType" id="caseType">
                 		<option value="">请选择</option>
						<c:forEach items="${storageLevelList}" var="obj">
							<option value="${obj.key}">${obj.value}</option>
						</c:forEach>
                 	</select>
                 </td>
                 <td><span class="t_span01">二级显示：</span></td>
	          	<td>
                 	<select class="w120" name="caseType" id="caseType">
                 		<option value="">请选择</option>
						<c:forEach items="${storageLeve2List}" var="obj">
							<option value="${obj.key}">${obj.value}</option>
						</c:forEach>
                 	</select>
                 </td>
	          </tr>
	          <tr>
	               <td><span class="t_span01">三级显示：</span></td>
		           <td>
	                   <select class="w120" name="caseType" id="caseType">
	                 		<option value="">请选择</option>
							<c:forEach items="${storageLeve3List}" var="obj">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
	                 	</select>
	                </td>
	                <td><span class="t_span01">备注：</span></td>
	               <td>
	               	<input class="easyui-validatebox t_text w310" name="casePlace" type="text" value="${storage.comment}"/>
	               </td>
			  </tr>
			  <tr>
	              <td><span class="t_span01">设备信息：</span></td>
	               <td>
	               	<input class="easyui-validatebox t_text w310" name="casePlace" type="text" value="${storage.device}"/>
	               </td>
	               <td><span class="t_span01">关联设备：</span></td>
	               <td>
	               	<input class="easyui-validatebox t_text w310" name="casePlace" type="text" value="${storage.device}"/>
	               </td>
			  </tr>
			  <tr>
	              <td><span class="t_span01">AB面控制：</span></td>
	               <td colspan="3">
	               	<input class="easyui-validatebox t_text w310" name="casePlace" type="text" value="${storage.abSide}"/>
	               </td>
			  </tr>
		</table>
	</form>
	
<script type="text/javascript">

	//表单提交
	$('#chooseStorage').form({
		url:'${path}/storage/saveStorage.action',
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(returnData){
			data = JSON.parse(returnData); // 转换成json对象
			if(data.status == "success"){
				parent.alertInfo(data.data);
				parent.afterCloseInstock();
			} else if(data.status="fail"){
				alertInfo(data.data);
			} else {
				alertInfo("未知错误");
			}
		}
	});
	
	//取消新增或修改操作
	function closeStorage(){
		if($("#id").val() == ''){
			parent.afterCloseAddStorage();
		}else{
			parent.afterCloseEditStorage();
		}
	}
</script>
</body>
</html>