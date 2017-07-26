<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>涉案财物-财物操作记录</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
*{
	margin-bottom:3px;
}
</style>
</head>

<body>
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>财物操作记录查询</h3>
			</div>
			<!-- t_oneblock_h-->
			<form>
				<div class="t_oneblock_c pr">
					<div class="nobortable pt10 pl10">
						<table width="100%" border="0">
							<tr>
								<td class="tr" width="100"><span class="t_span01">财物名称：</span></td>
								<td><input class="t_text w140" name="filter_and_finances__financeName_LIKE_S" type="text" id="financeName" /></td>
								<td class="tr" width="120"><span class="t_span01">财物编号：</span></td>
								<td><input class="t_text w140" name="filter_and_finances__financeNum_LIKE_S" type="text" id="financeNum" /></td>
								<td class="tr" width="120"><span class="t_span01">经办人：</span></td>
								<td><input class="t_text w140" name="filter_and_operator_LIKE_S" type="text" id="operator" /></td>
								<td class="tr" width="100"><span class="t_span01">操作人：</span></td>
								<td><input class="t_text w140" name="filter_and_creator_LIKE_S" type="text" id="creator" /></td>
							</tr>
							<tr>
								<td class="tr" width="100"><span class="t_span01">出入库类型：</span></td>
								<td>
									<select name="filter_and_flag_EQ_I" style="width: 140">
									<option value="">请选择</option>
										<c:forEach items="${financeStateList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
									</select>
								</td>
								<td class="tr" width="100"><span class="t_span01">出入库原因：</span></td>
				               	<td>
									<select name="filter_and_reason_EQ_I" style="width: 140">
										<option value="">请选择</option>
										<c:forEach items="${outstockReasonTypeList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
				                   </select>
				               	</td>
								<td class="tr" width="120"><span class="t_span01">送物/取物人：</span></td>
								<td><input class="t_text w140" name="filter_and_fetchMan_LIKE_S" type="text" id="fetchMan" /></td>
								<td class="tr" width="120"><span class="t_span01">报送/取物单位：</span></td>
								<td>
									<select name="filter_and_department_EQ_I" style="width: 140">
									<option value="">请选择</option>
										<c:forEach items="${departmentTypeList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tr" width="120"><span class="t_span01">经办时间：</span></td>
								<td colspan="3" width="260">
									<input class="easyui-datebox t_text w160" editable="false" name="filter_and_createTime_GE_T" type="text" />
									<span class="t_span01">至</span>
									<input class="easyui-datebox t_text w160" editable="false" name="filter_and_createTime_LE_T" type="text" />
								</td>
							</tr>
							<tr>
								<td colspan="10" align="right">
									<input type="hidden" name="order_updateTime_T" value="desc"/>
									<input class="t_btnsty01" id="find" name="select" type="button" value="查询"/>
									<input class="t_btnsty01" type="reset" onclick="resetClick()" value="重置"/>&nbsp;&nbsp;&nbsp;
									
								</td>
							</tr>
						</table>
					</div>
					<!--nobortable -->
				</div>
			</form>
			<!-- t_oneblock_c-->
		</div>
		<!--t_oneblock -->
	</div>
	<!--t_rightcontainer -->
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>财物操作记录列表</h3>
			</div>
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr">
				<div class="hastable mt10">
					<table id="dg1" style="width:100%; height:350px" data-options="singleSelect:true,collapsible:false,
			            url:'${path}/stock/showOperateLogs.action',
			            method:'post',
			            fitColumns:true,
			            rownumbers:true,
						autoRowHeight:true,
						pagination:true,
						pageNumber:1,
						pageSize:50,
						pageList:[10,30,50]">
						<thead>
							<tr>
								<th data-options="field:'id',hidden:true"></th>
								<th data-options="field:'row.finances.financeCode',width:130,formatter:formatFinanceCode" align="center">电子识别码</th>
								<th data-options="field:'row.finances.financeName',width:130,formatter:formatFinanceName" align="center">财物名称</th>
								<th data-options="field:'row.finances.financeNum',width:80,formatter:formatFinanceNum" align="center">财物编号</th>
								<th data-options="field:'operator',width:80" align="center">经办人</th>
								<th data-options="field:'createTime',width:130,formatter:formatCreateTime" align="center">经办时间</th>
								<th data-options="field:'creator',width:80" align="center">操作人</th>
								<th data-options="field:'fetchMan',width:80" align="center">送物/取物人</th>
								<th data-options="field:'department',width:130,formatter:formatDepartmentType" align="center">报送/取物单位</th>
								<th data-options="field:'flag',width:60,formatter:formatFlag" align="center">出入库类型</th>
								<th data-options="field:'reason',width:100,formatter:formatReason" align="center">出库原因</th>								
							</tr>
						</thead>
					</table>
				</div>
				<!--hastable -->
			</div>
			<!-- t_oneblock_c-->
		</div>
		<!--t_oneblock -->
	</div>
	
	<script type="text/javascript">
	
		$(function() {
			createTable('dg1');
		});
		
		// 出入库操作
		function formatFlag(value,row,index) {
			row.financeState = row.flag;
			return formatFinanceState(value,row,index);
		}

		// 电子识别码
		function formatFinanceCode(value,row,index){
            if(row.finances&&row.finances.id){
                return row.finances.financeCode;
            }
        }
		// 财务名称
		function formatFinanceName(value,row,index){
            if(row.finances&&row.finances.id){
                return row.finances.financeName;
            }
        }
		// 财务编号	
		function formatFinanceNum(value,row,index){
            if(row.finances&&row.finances.id){
                return row.finances.financeNum;
            }
        }
		
		//案件类型
		function formatDepartment(value,row,index){
            if(row.cases&&row.cases.id){
                return row.cases.caseType;
            }
        }
		
		//经办时间
		function formatCreateTime(value,row,index){
			if(row.createTime==null || row.createTime=='') {
				return "";
			}
			var date = new Date(row.createTime);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var h = date.getHours();
			var mm = date.getMinutes();
			var s = date.getSeconds();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(mm<10?('0'+mm):mm)+':'+(s<10?('0'+s):s);
		}
		
		//现场勘验号
		function formatSiteNum(value,row,index){
            if(row.cases&&row.cases.id){
                return row.cases.siteNum;
            }
        }
		//勘验时间(起-止)
		function formatInspectionTime(value,row,index){
            if(row.cases&&row.cases.id){
                return row.cases.inspectionTimeStart + row.cases.inspectionTimeEnd;
            }
        }
		
		function resetClick() {
			$("input[name='filter_and_createTime_GE_T']").attr("value","");
			$("input[name='filter_and_createTime_LE_T']").attr("value","");
		}
		
	</script>
</body>
</html>