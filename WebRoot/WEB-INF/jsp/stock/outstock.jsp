<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>财物出库</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
	<form id="outstock" name="outstockForm" method="post">
		<input type="button" class="t_btnsty02" id="printBtn" onclick="outstockPrint();" value="打印出库表">
		<input type="submit" class="t_btnsty02" id="saveBtn" value="出库">
		<input type="button" class="t_btnsty02" id="cancel" onclick="outstockClose();" value="取消">
		<input type="hidden" name="id" id="id" value="${finances.id}">
		<input type="hidden" name="casesid" id="casesid" value="${cases.id}">
		<%--  0登记  1在库  2不在库 --%>
		<input type="hidden" name="financeState" id="financeState" value="${finances.financeState}">
		<br>
    	<table border="0">
			<tr>
				<td><span class="t_span01 w140">财物名称：</span></td>
				<td colspan="2">
					<input disabled class="easyui-validatebox t_text"  data-options="required:true,missingMessage:'请输入财物名称'" name="financeName" type="text" value="${finances.financeName}" />
				</td>
	          	<td><span class="t_span01 w140">财物编号：</span></td>
				<td colspan="2">
					<input disabled class="easyui-validatebox t_text"  data-options="" name="financeNum" type="text" value="${finances.financeNum}" />
				</td>
			</tr>
			<tr>
				<td><span class="t_span01 w140">所属案件：</span></td>
				<td colspan="2">
					<input disabled class="easyui-validatebox t_text"  data-options="" name="caseName" type="text" value="${cases.caseName}" />
				</td>
                <td><span class="t_span01 w140">案件编号：</span></td>
                <td colspan="2">
                   <input disabled class="easyui-validatebox t_text"  data-options="" name="caseNum" type="text" value="${cases.caseNum}" />
				</td>
			</tr>
			<tr>
				<td><span class="t_span01 w160">财物识别码：</span></td>
				<td colspan="2">
					<input disabled class="easyui-validatebox t_text"  data-options="" name="financeCode" type="text" value="${finances.financeCode}"/>
				</td>
	          	<td><span class="t_span01 w160">电子识别码：</span></td>
				<td colspan="2">
					<input disabled class="easyui-validatebox t_text"  data-options="" name="digitalCode" type="text" value="${finances.digitalCode}"/>
				</td>
			</tr>
			<tr>
				<td><span class="t_span01 w120">取物人：</span></td>
				<td colspan="2">
					<input class="easyui-validatebox t_text"  data-options="required:true,missingMessage:'请输入取物人'" name="fetchMan" type="text" value="${stock.fetchMan}"/><span class="t_span02">*</span>
				</td>
	          	<td><span class="t_span01 w120">经办人：</span></td>
				<td colspan="2">
					<input class="easyui-validatebox t_text"  data-options="required:true,missingMessage:'请输入经办人'" name="operator" type="text" value="${stock.operator}"/><span class="t_span02">*</span>
				</td>
			</tr>
			<tr>
                <td><span class="t_span01 w140">取物单位：</span></td>
               	<td colspan="2">
                   <input type="hidden" name="departmentHid" id="departmentHid" value="${stock.department}">
                   <select name="department" id="department">
                       <option value="0">请选择</option>
                       <option value="1">县局</option>
                       <option value="2">……</option>
                   </select>
               	</td>
                <td><span class="t_span01 w140">出库原因：</span></td>
               	<td colspan="2">
                   <input type="hidden" name="reasonHid" id="reasonHid" value="${stock.reason}">
                   <select name="reason" id="reason">
                       <option value="0">请选择</option>
                       <option value="1">移交</option>
                       <option value="2">销毁</option>
                       <option value="3">……</option>
                   </select>
               	</td>
			</tr>
               <tr>
                   <td><span class="t_span01 w140">存储位置：</span></td>
                   <td colspan="5">
                       <input disabled class="easyui-validatebox w280 t_text" data-options="" name="storeLocation" type="text" value="${finances.storeLocation}"/>
                   </td>
               </tr>
               <tr>
                   <td><span class="t_span01 w100">备注：</span></td>
                   <td colspan="5">
                   	   <textarea id="financeMemo" name="financeMemo" style="width:330;height:100">${finances.financeMemo}</textarea>
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
		$('#outstock').form({
			url:'${path}/stock/saveStock.action',     //保存出库信息
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(returnData){
				data = JSON.parse(returnData); // 转换成json对象
				if(data.status == "success"){
					parent.alertInfo(data.data);
					parent.afterCloseOutstock();
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

		//取消出库操作
		function outstockClose(){
			parent.afterCloseOutstock();
		}
		//打印
		function outstockPrint(){
			parent.afterCloseOutstock();
		}
	</script>
</body>
</html>