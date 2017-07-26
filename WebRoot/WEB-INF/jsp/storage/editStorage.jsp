<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>>新增/修改存储页</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
	<style>
	*{
		margin-bottom:3px;
	}
	</style>					
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
	    		   <td class="tr" width="100"><span class="t_span01">存储类型：</span></td>
				   <td>
				   		<select class="w120" name="type" id="type" onchange="storageTypeChange(this.value)">
					   		<option value="">请选择</option>
							<c:forEach items="${storageTypeList}" var="obj">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
                 		</select>
	               </td>
	               <td class="tr" width="100"><span class="t_span01">存储名称：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w120" data-options="required:true,missingMessage:'请输入存储名称'" name="name" type="text" value="${storage.name}" /><span class="t_span02">*</span>
	               </td>
	          </tr>
	          <tr>
	          	<td class="tr" width="100"><span class="t_span01">一级显示：</span></td>
	          	<td>
                 	<select class="w120" name="storageLevel1" id="storageLevel1" onchange="storageLevel1Change(this.value)">
                 		<option value="">请选择</option>
						<c:forEach items="${storageLevelList}" var="obj">
							<option value="${obj.key}">${obj.value}</option>
						</c:forEach>
                 	</select>
                 </td>
                 <td class="tr" width="100"><span class="t_span01">二级显示：</span></td>
	          	<td>
                 	<select class="w120" name="storageLevel2" id="storageLevel2" onchange="storageLevel2Change(this.value)">
                 		<option value="">请选择</option>
						<c:forEach items="${storageLevel2List}" var="obj">
							<option value="${obj.key}">${obj.value}</option>
						</c:forEach>
                 	</select>
                 </td>
	          </tr>
	          <tr>
	               <td class="tr" width="100"><span class="t_span01">三级显示：</span></td>
		           <td>
	                   <select class="w120" name="storageLevel3" id="storageLevel3" onchange="storageLevel3Change(this.value)">
	                 		<option value="">请选择</option>
							<c:forEach items="${storageLevel3List}" var="obj">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
	                 	</select>
	                </td>
	                <td class="tr" width="100"><span class="t_span01">备注：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w120" name="comment" type="text" value="${storage.comment}"/>
	               </td>
			  </tr>
			  <tr>
	              <td class="tr" width="100"><span class="t_span01">设备信息：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w120" name="device" type="text" value="${storage.device}"/>
	               </td>
	               <td class="tr" width="100"><span class="t_span01">AB面控制：</span></td>
	               <td>
	               	  <input type="checkbox" name="abSide" id="abSide" value="1" disabled="disabled"/>
	               </td>
			  </tr>
			  <tr>
			  	  <td class="tr" width="100"><span class="t_span01">控制URL：</span></td>
	              <td colspan="3">
	               	 <input class="easyui-validatebox t_text" style="width:345px;" id="conUrl" name="conUrl" type="text" value="${storage.conUrl}" disabled="disabled" />
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
				parent.afterCloseAddStorage();
			} else if(data.status="fail"){
				alertInfo(data.data);
			} else {
				alertInfo("未知错误");
			}
		}
	});
	
	function storageTypeChange(value) {
		var lineOption = "<option value=''>请选择</option>";
		$("#storageLevel1").empty();
		$("#storageLevel1").append(lineOption);
		$("#storageLevel2").empty();
		$("#storageLevel2").append(lineOption);
		$("#storageLevel3").empty();
		$("#storageLevel3").append(lineOption);
		$("#conUrl").attr("disabled","disabled");
		$("#abSide").attr("disabled","disabled");
		
		if(''!=value) {
			if('1'==value) {
				for(var i=1; i<100; i++){
					var lineOption1 = "<option value='"+i+"'>"+i+"排"; + "</option>";
					$("#storageLevel1").append(lineOption1);
				}
			}else {
				for(var i=1; i<100; i++){
					var lineOption1 = "<option value='"+i+"'>"+i+"列"; + "</option>";
					$("#storageLevel1").append(lineOption1);
				}
				$('#abSide').removeAttr("disabled");
			}
		}
	}
	
	function storageLevel1Change(value) {
		var lineOption = "<option value=''>请选择</option>";
		$("#storageLevel2").empty();
		$("#storageLevel2").append(lineOption);
		$("#storageLevel3").empty();
		$("#storageLevel3").append(lineOption);
		$("#conUrl").attr("disabled","disabled");
		
		for(var i=1; i<100; i++){
			var lineOption2 = "<option value='"+i+"'>"+i+"组"; + "</option>";
			$("#storageLevel2").append(lineOption2);
		}
	}
	
	function storageLevel2Change(value) {
		var lineOption = "<option value=''>请选择</option>";
		$("#storageLevel3").empty();
		$("#storageLevel3").append(lineOption);
		$("#conUrl").attr("disabled","disabled");
		
		if(''!=value) {
			if('1'==value) {
				for(var i=1; i<100; i++){
					var lineOption3 = "<option value='"+i+"'>"+i+"门"; + "</option>";
					$("#storageLevel3").append(lineOption3);
				}
			}else {
				for(var i=1; i<100; i++){
					var lineOption3 = "<option value='"+i+"'>"+i+"层"; + "</option>";
					$("#storageLevel3").append(lineOption3);
				}
			}
		}
	}
	
	function storageLevel3Change(value) {
		if(''!=value) {
			$('#conUrl').removeAttr("disabled");
		}
	}
	
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