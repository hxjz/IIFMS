<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>新增/修改案件页</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
	<style type="text/css">
	*{
		margin-bottom:5px;
	}
	</style>
</head>
<body>
	<form id="edit" name="editForm" method="post">
		<input type="submit" class="t_btnsty02" id="saveBtn" value="保存"/>
		<input type="button" id="cancel" class="t_btnsty02" onclick="closeCases();" value="关闭"/>
		<input type="hidden" name="id" id="id" value="${cases.id}"/>
		<input type="hidden" name="fromSource" id="fromSource" value="${fromSource}"/>
		<br>
	    	<table border="0">
	          <tr>
	    		   <td><span class="t_span01">案件编号：</span></td>
				   <td>
	               	  <input class="easyui-validatebox t_text w140" data-options="" name="caseNum" type="text" value="${cases.caseNum}"/>
	               </td>
	               <td><span class="t_span01">案件名称：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w140" data-options="required:true,missingMessage:'请输入案件名称'" name="caseName" type="text" value="${cases.caseName}" /><span class="t_span02">*</span>
	               </td>
	          </tr>
	          <tr>
	          	<td><span class="t_span01">案件类型：</span></td>
	          	<td>
	          	    <input type="hidden" name="caseTypeHid" id="caseTypeHid" value="${cases.caseType}">
                 	<select class="w140" name="caseType" id="caseType">
                 		<option value="">请选择</option>
						<c:forEach items="${caseTypeList}" var="obj">
							<option value="${obj.key}">${obj.value}</option>
						</c:forEach>
                 	</select>
                 	<span class="t_span02">*</span>
                 </td>
                 <td><span class="t_span01" style="margin-left:25px;">命案：</span></td>
	          	<td>
                	<c:if test="${cases.isMurder == 1}">
                		<input type="checkbox" name="isMurder" id="isMurder" checked="checked" value="1" onclick="setVal()"/>
                	</c:if>
                	<c:if test="${empty cases.isMurder || cases.isMurder == 0 }">
                		<input type="checkbox" name="isMurder" id="isMurder" value="0" onclick="setVal()"/>
                	</c:if>
                 </td>
	          </tr>
	          <tr>
	               <td><span class="t_span01">案发时间：</span></td>
	               <td>
	               	  <input type="hidden" name="oldCaseTimeStart" value="${cases.caseTimeStart}">
	               	  <div id="test1">
	               	  	<input class="easyui-datetimebox w140" data-options="" name="caseTimeStart" id="caseTimeStart" type="text" value="${cases.caseTimeStart}"/><span class="t_span01">起</span><input type="button" class="t_btnsty01" id="changeTimeStart1" value="模糊"/>
	               	  </div>
	               	  <div id="test2" hidden="true">
	               	  	<input class="easyui-datebox w140" data-options="" name="caseTimeStart" id="caseTimeStart" type="text" value="${cases.caseTimeStart}"/><span class="t_span01">起</span><input  type="button" class="t_btnsty01" id="changeTimeStart2" value="精确"/>
	               	  </div>
	               </td>
	               <td>&nbsp;</td>
	               <td>
	               	  <input class="easyui-datetimebox w140" data-options="" name="caseTimeEnd" id="caseTimeEnd" type="text" value="${cases.caseTimeEnd}"/><span class="t_span01">止</span>
	               </td>
			  </tr>
	          <tr>
	          	   <td><span class="t_span01">案发地点：</span></td>
	               <td colspan="3">
	               	<input class="easyui-validatebox t_text w310" data-options="" name="casePlace" type="text" value="${cases.casePlace}"/>
	               </td>
	          </tr>
	          <tr>
	          	   <td><span class="t_span01">现场勘验号：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w140" data-options="" name="siteNum" type="text" value="${cases.siteNum}" />
	               </td>
	               <td><span class="t_span01" style="margin-left:15px;">勘验人：</span></td>
	               <td>
	               	  <input class="easyui-validatebox t_text w140" data-options="" name="inspectionPerson" type="text" value="${cases.inspectionPerson}" />
	               </td>
			  </tr>
	          <tr>
	          	   <td><span class="t_span01">勘验时间：</span></td>
	               <td>
	               	  <input class="easyui-datetimebox w140" data-options="" name="inspectionTimeStart" id="inspectionTimeStart" type="text" value="${cases.inspectionTimeStart}"/><span class="t_span01">起</span>
	               </td>
	               <td>&nbsp;</td>
	               <td>
	               	  <input class="easyui-datetimebox w140" data-options="" name="inspectionTimeEnd" id="inspectionTimeEnd" type="text" value="${cases.inspectionTimeEnd}"/><span class="t_span01">止</span>
	               </td>
			  </tr>
			  <tr>
			  	   <td><span class="t_span01">管辖单位：</span></td>
	               <td colspan="3">
	               	  <select class="w140" name="jurisdiction">
                 		 <option value="">请选择</option>
						 <c:forEach items="${jurisdictionList}" var="obj">
							<option value="${obj.key}" <c:if test="${cases.jurisdiction==obj.key}">selected="true"</c:if>>${obj.value}</option>
						 </c:forEach>
                 	  </select>
	               </td>
			  </tr>
			  <tr>
			  	   <td><span class="t_span01">案件描述：</span></td>
	               <td colspan="3">
	               	  <textarea id="caseDesc" name="caseDesc" style="width:400;height:180">${cases.caseDesc}</textarea>
	               </td>
			  </tr>
	</table>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
		   //Temp Start
		   var caseTypev = $("#caseTypeHid").val();
		   $("#caseType").val(caseTypev);
		   //Temp End
		   
		   // 初始化第二个隐藏时间控件的值
		    $("input[textboxname='caseTimeStart']:eq(1)").attr("value","");
			$("#test2").hide();
		   	$("#changeTimeStart1").click(function(){
		   		$("input[textboxname='caseTimeStart']").attr("value","");
		   		$("input[name='caseTimeStart']").attr("value","");
		  		$("#test1").hide();
		  		$("#test2").show();
		   	});
		   	$("#changeTimeStart2").click(function(){
		   		$("input[textboxname='caseTimeStart']").attr("value","");
		   		$("input[name='caseTimeStart']").attr("value","");
		  		$("#test2").hide();
		  		$("#test1").show();
		   	});
		});
		
		//表单提交  
		$('#edit').form({
		    url:'${path}/cases/saveCases.action',
		    onSubmit:function(){
		        return $(this).form('validate');
		    },
		    success:function(returnData){
		    	data = JSON.parse(returnData); // 转换成json对象
		    	if(data.status == "success"){
		    		var vData = data.data;
		    		var vArr = new Array();
		    		vArr = vData.split(",");
		    		parent.alertInfo(vArr[0]);
		    		
		    		if(!($("#fromSource").val() == '')) {
		    			var caseId = vArr[1];
		    			var caseName = $("input[name='caseName']").val();
		    			var caseNum = $("input[name='caseNum']").val();
		    			parent.handleAddCaseBack(caseId,caseName,caseNum);
		    			return true;
		    		}
		    		
		    		if($("#id").val() == ''){
		    			parent.afterCloseAddCases();
		    		}else{
		    			parent.afterCloseEditCases();
		    		}
		    	} else if(data.status="fail"){
		    		alertInfo(data.data);
		    	} else {
		    		alertInfo("未知错误");
		    	}
		    }
		});
		
		function setVal() {
			if(true==$("#isMurder").is(':checked')) {
				$("#isMurder").attr("value","1");
			}else {
				$("#isMurder").attr("value","0");
			}
		}
		
		//取消新增或修改操作
		function closeCases(){
			if($("#id").val() == ''){
				parent.afterCloseAddCases();
			}else{
				parent.afterCloseEditCases();
			}
		}
	</script>
</body>
</html>