<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>财物详情页</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp" %>
	<style type="text/css">
		.slim_table {
			border-collapse:collapse;
			border-spacing:0;
			border:1px solid #888;
			/*border-top:1px solid #888;*/
			width:800px}
	</style>
</head>
<body>
	<input type="button" id="cancel" class="t_btnsty02" onclick="javascript:parent.afterCloseDetailWindow();" value="关闭" >
	<input type="hidden" name="id" id="id" value="${finances.id}">
	<br>
	<table border="0">
		<tr>
			<td><span class="t_span01">财物名称：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseNum" type="text" value="${finances.financeName}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">财物编号：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseName" type="text" value="${finances.financeNum}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">财物类型：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseName" type="text" value="${finances.financeType}" readonly="readonly"/>
			</td>
			 <td><span class="t_span01">财物状态：</span></td>
			 <td><span class="t_span02">${finances.financeState}</span></td>
		</tr>
		<tr>
			<td><span class="t_span01">案件名称：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseNum" type="text" value="${finances.cases.caseName}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">案件编号：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseName" type="text" value="${finances.cases.caseNum}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">案件类型：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseName" type="text" value="${finances.cases.caseType}" readonly="readonly"/>
			</td>
			<td>
				<c:if test="${finance.cases.isMurder == 1}">
					<input type="checkbox" name="isMurder" id="isMurder" checked="checked" disabled="disabled"/>
				</c:if>
				<c:if test="${empty finance.cases.isMurder || finance.cases.isMurder == 0 }">
					<input type="checkbox" name="isMurder" id="isMurder"  disabled="disabled"/>
				</c:if>
				<span class="t_span01">命案</span>
			</td>
			<td>
				<span class="t_span01">是否出库：
				<c:if test="${finances.financeState!='1'}">是</c:if>
				<c:if test="${finances.financeState=='1'}">否</c:if>
				</span>
			</td>
		</tr>
		<tr>
			<td><span class="t_span01">案发时间：</span></td>
			<td>
				<input class="easyui-datetimebox w150" data-options="" name="caseTimeStart" id="caseTimeStart"
					   type="text" value="${finances.cases.caseTimeStart}" readonly="readonly"/><span class="t_span01">起</span>
			</td>
			<td>&nbsp;</td>
			<td>
				<input class="easyui-datetimebox w150" data-options="" name="caseTimeEnd" id="caseTimeEnd"
					   type="text" value="${finances.cases.caseTimeEnd}" readonly="readonly"/><span class="t_span01">止</span>
			</td>
			<td><span class="t_span01">案发地点：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="caseName" type="text" value="${finances.cases.casePlace}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">当前时间：</span></td>
			<td><input class="easyui-validatebox t_text w140" data-options=""
					   name="caseName" type="text" value="${finances.cases.casePlace}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td><span class="t_span01">财物来源：</span></td>
			<td>
				<input type="hidden" name="financeSourceHid" id="financeSourceHid" value="${finances.financeSource}">
				<select class="w140" name="financeSource" id="financeSource" disabled="disabled">
					<option value="0">请选择</option>
					<option value="1">现场勘验</option>
					<option value="2">移交</option>
					<option value="3">……</option>
				</select>
			</td>
			<td><span class="t_span01">来源单位：</span></td>
			<td>
				<input type="hidden" name="sourceOfficeHid" id="sourceOfficeHid" value="${finances.sourceOffice}">
				<select class="w140" name="sourceOffice" id="sourceOffice" disabled="disabled">
					<option value="0">请选择</option>
					<option value="1">来源单位1</option>
					<option value="2">来源单位2</option>
					<option value="3">……</option>
				</select>
			</td>
			<td><span class="t_span01">保管单位：</span></td>
			<td>
				<input type="hidden" name="storeOfficeHid" id="storeOfficeHid" value="${finances.storeOffice}">
				<select class="w140" name="storeOffice" id="storeOffice" disabled="disabled">
					<option value="0">请选择</option>
					<option value="1">现场勘验</option>
					<option value="2">移交</option>
					<option value="3">……</option>
				</select>
			</td>
			<td><span class="t_span01">存放位置：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="storeLocation" type="text" value="${finances.storeLocation}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td><span class="t_span01">登记时间：</span></td>
			<td>
				<input class="easyui-datetimebox w150" data-options="" name="entryTime" id="entryTime"
					   type="text" value="${finances.entryTime}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">首次入库时间：</span></td>
			<td>
				<input class="easyui-datetimebox w150" data-options="" name="instockTime" id="instockTime"
					   type="text" value="${finances.instockTime}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">最终出库时间：</span></td>
			<td>
				<input class="easyui-datetimebox w150" data-options="" name="outstockTime" id="outstockTime"
						type="text" value="${finances.outstockTime}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">财物识别码：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
				name="financeCode" type="text" value="${finances.financeCode}" readonly="readonly"/>
				<span class="t_span01">打印</span>
			</td>
		</tr>
        <tr>
			<td><span class="t_span01">登记人：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="entryMan" type="text" value="${finances.entryMan}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">首次入库人：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="instockMan" type="text" value="${finances.instockMan}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">最终出库人：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="outstockMan" type="text" value="${finances.outstockMan}" readonly="readonly"/>
			</td>
			<td><span class="t_span01">电子识别码：</span></td>
			<td>
				<input class="easyui-validatebox t_text w140" data-options=""
					   name="digitalCode" type="text" value="${finances.digitalCode}" readonly="readonly"/>
				<span class="t_span01">写入</span>
			</td>

        </tr>
		<tr>
			<td><span class="t_span01">财物说明：</span></td>
			<td colspan="5">
				<input class="easyui-validatebox t_text w650" data-options="" name="financeDesc" type="text"
					   value="${finances.financeDesc}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td><span class="t_span01">备注：</span></td>
			<td colspan="5">
				<input class="easyui-validatebox t_text w650" data-options="" name="financeMemo" type="text"
					   value="${finances.financeMemo}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td><span class="t_span01">财物照片：</span></td>
			<td colspan="5">
				<textarea id="imageSign" name="caseDesc"
						  style="width:650px;height:100px" readonly="readonly">${finances.imageSign}</textarea>
			</td>
		</tr>
	</table>
	<span class="t_span03">操作记录</span>
	<table class="slim_table">
		<tr>
			<td><span class="t_span01">财物识别码</span></td>
			<td><span class="t_span01">操作时间</span></td>
			<td><span class="t_span01">操作类型</span></td>
			<td><span class="t_span01">操作人</span></td>
		</tr>

	</table>
	<span class="t_span02">异常记录</span>
	<table class="slim_table">
		<tr>
			<td><span class="t_span01">电子识别码</span></td>
			<td><span class="t_span01">异常时间</span></td>
			<td><span class="t_span01">位置</span></td>
			<td><span class="t_span01">类型</span></td>
			<td rowspan="5"><span class="t_span01">照片展示区</span></td>
		</tr>

	</table>
<script type="text/javascript">
	$(document).ready(function () {
		// 财物来源
		var financeSourceValue = $("#financeSourceHid").val();
		$("#financeSource").val(financeSourceValue);
		// 来源单位
		var sourceOfficeValue = $("#sourceOfficeHid").val();
		$("#sourceOffice").val(sourceOfficeValue);
        // 保管单位
		var storeOfficeValue = $("#storeOfficeHid").val();
		$("#storeOffice").val(storeOfficeValue);

	});
</script>
</body>
</html>