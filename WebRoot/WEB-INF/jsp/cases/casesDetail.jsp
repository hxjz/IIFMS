<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>案件详情页</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
	<style type="text/css">
	*{
		margin-bottom:5px;
	}
	</style>
</head>
<body>
	
	<input type="button" id="cancel" class="t_btnsty02" onclick="javascript:parent.afterCloseCasesDetail();" value="关闭" >
	<input type="hidden" id="id" name="id" value="${cases.id}">
	<br>
    	<table border="0">
          <tr>
    		   <td><span class="t_span01">案件编号：</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="caseNum" type="text" value="${cases.caseNum}" readonly="readonly" /> 
               </td>
               <td><span class="t_span01">案件名称：</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="caseName" type="text" value="${cases.caseName}" readonly="readonly" /> 
               </td>
          </tr>
          <tr>
               <td><span class="t_span01">案件类型：</span></td>
               <td>
	          	    <input type="hidden" name="caseTypeHid" id="caseTypeHid" value="${cases.caseType}">
                 	<select class="w140" name="caseType" id="caseType" disabled="disabled">
                 		<option value="">无</option>
						<c:forEach items="${caseTypeList}" var="obj">
							<option value="${obj.key}" <c:if test="${cases.caseType==obj.key}">selected="true"</c:if>>${obj.value}</option>
						</c:forEach>
                 	</select>
                 	<span class="t_span02">*</span>
                 </td>
               <td><span class="t_span01" style="margin-left:25px;">命案：</span></td>
               <td>
                	<c:if test="${cases.isMurder == 1}">
                		<input type="checkbox" name="isMurder" id="isMurder" checked="checked" disabled="disabled"/>
                	</c:if>
                	<c:if test="${empty cases.isMurder || cases.isMurder == 0 }">
                		<input type="checkbox" name="isMurder" id="isMurder"  disabled="disabled"/>
                	</c:if>
                 </td>
		  </tr>
          <tr>
          	   <td><span class="t_span01">案发时间：</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="caseTimeStart" type="text" value="${cases.caseTimeStart}" readonly="readonly" /><span class="t_span01">起</span>
               </td>
               <td><span class="t_span01">&nbsp;</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="caseTimeEnd" type="text" value="${cases.caseTimeEnd}" readonly="readonly" /><span class="t_span01">止</span>
               </td>
          </tr>
          <tr>
          	   <td><span class="t_span01">案发地点：</span></td>
               <td colspan="3">
               	  <input class="easyui-validatebox t_text w310" data-options="" name="casePlace" type="text" value="${cases.casePlace}" readonly="readonly" /> 
               </td>
		  </tr>
          <tr>
          	   <td><span class="t_span01">现场勘验号：</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="siteNum" type="text" value="${cases.siteNum }" readonly="readonly" />
               </td>
              <td><span class="t_span01" style="margin-left:15px;">勘验人：</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="inspectionPerson" type="text" value="${cases.inspectionPerson }" readonly="readonly" /> 
               </td>
		  </tr>
		  <tr>
          	   <td><span class="t_span01">勘验时间：</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="inspectionTimeStart" type="text" value="${cases.inspectionTimeStart}" readonly="readonly" /><span class="t_span01">起</span>
               </td>
               <td><span class="t_span01">&nbsp;</span></td>
               <td>
               	  <input class="easyui-validatebox t_text w140" data-options="" name="inspectionTimeEnd" type="text" value="${cases.inspectionTimeEnd}" readonly="readonly" /><span class="t_span01">止</span>
               </td>
          </tr>
		  <tr>
		  	   <td><span class="t_span01">管辖单位：</span></td>
               <td colspan="3">
               	  <select class="w140" name="jurisdiction" disabled="disabled">
                 		 <option value="">无</option>
						 <c:forEach items="${jurisdictionList}" var="obj">
							<option value="${obj.key}" <c:if test="${cases.jurisdiction==obj.key}">selected="true"</c:if>>${obj.value}</option>
						 </c:forEach>
                 	  </select>
               </td>
		  </tr>
		  <tr>
		  	   <td><span class="t_span01">案件描述：</span></td>
               <td colspan="3">
               	<textarea id="caseDesc" name="caseDesc" style="width:400;height:200;" disabled="disabled">${cases.caseDesc}</textarea>
               </td>
		  </tr>
		  <tr>
		  	<td><span class="t_span01">最后修改人：</span></td>
            <td>
            	  <input class="easyui-validatebox t_text w140" data-options="" name="caseNum" type="text" value="${cases.updater}" readonly="readonly" /> 
            </td>
            <td><span class="t_span01">最后修改时间：</span></td>
            <td>
            	  <input class="easyui-validatebox t_text w140" data-options="" name="updateTime" type="text" value="${cases.updateTime}" readonly="readonly" /> 
            </td>
		  </tr>
	</table>
	<script type="text/javascript">
		$(document).ready(function(){
		   //Temp Start
		   var caseTypev = $("#caseTypeHid").val();
		   $("#caseType").val(caseTypev);
		   //Temp End
		});
	</script>
</body>
</html>
