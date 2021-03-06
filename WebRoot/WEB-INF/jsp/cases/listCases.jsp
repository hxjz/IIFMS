<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>涉案财物-案件列表页</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<style type="text/css">
	*{
		margin-bottom:3px;
	}
</style>
</head>
<body>
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>案件信息查询</h3>
			</div>
			<!-- t_oneblock_h-->
			<form>
				<div class="t_oneblock_c pr">
					<div class="nobortable pt10 pl10">
						<table width="100%" border="0">
							<tr>
								<td class="tr" width="100"><span class="t_span01">案件编号：</span></td>
								<td><input class="t_text w140" name="filter_and_caseNum_LIKE_S" type="text" id="caseNum" /></td>
								<td class="tr" width="100"><span class="t_span01">案件名称：</span></td>
								<td><input class="t_text w140" name="filter_and_caseName_LIKE_S" type="text" id="caseName" /></td>
								<td class="tr" width="100"><span class="t_span01">案发地点：</span></td>
								<td><input class="t_text w220" name="filter_and_casePlace_LIKE_S" type="text" id="casePlace" /></td>
							</tr>
							<tr>
								<td class="tr" width="100"><span class="t_span01">现场勘验号：</span></td>
								<td><input class="t_text w140" name="filter_and_siteNum_LIKE_S" type="text" id="siteNum" /></td>
								<td class="tr" width="100"><span class="t_span01">案件类型：</span></td>
								<td><select name="filter_and_caseType_EQ_I" style="width: 140">
										<option value="">请选择</option>
										<c:forEach items="${caseTypeList}" var="obj">
											<option value="${obj.key}">${obj.value}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="tr" width="120"><span class="t_span01">案发时间(起)：</span></td>
								<td colspan="3" width="260">
									<input class="easyui-datebox t_text w140" editable="false" name="filter_and_caseTimeStart_GE_S" type="text" /> 
									<span class="t_span01">至</span> 
									<input class="easyui-datebox t_text w140" editable="false" name="filter_and_caseTimeStart_LE_S" type="text" /></td>
								<td class="tr" width="120"><span class="t_span01">案发时间(止)：</span></td>
								<td colspan="3" width="260">
									<input class="easyui-datebox t_text w140" editable="false" name="filter_and_caseTimeEnd_GE_S" type="text" /> 
									<span class="t_span01">至</span> 
									<input class="easyui-datebox t_text w140" editable="false" name="filter_and_caseTimeEnd_LE_S" type="text" /></td>
								<td colspan="2" width="" align="center" style="margin-left:-10px;">
									&nbsp;<input type="checkbox" id="isDel" name="filter_and_isDel_LE_I" value="0" onclick="setVal()"/> 
									<span class="t_span01">包含已删除案件</span>
								</td>
							</tr>
							<tr>
								<td class="tr"><span class="t_span01">勘验时间(起)：</span></td>
								<td colspan="3"><input class="easyui-datebox t_text w140" editable="false" name="filter_and_inspectionTimeStart_GE_S" type="text" /> 
								<span class="t_span01">至</span> 
								<input class="easyui-datebox t_text w140" editable="false" name="filter_and_inspectionTimeStart_LE_S" type="text" /></td>
								<td class="tr"><span class="t_span01">勘验时间(止)：</span></td>
								<td colspan="3">
									<input class="easyui-datebox t_text w140" editable="false" name="filter_and_inspectionTimeEnd_GE_S" type="text" /> 
									<span class="t_span01">至</span> 
									<input class="easyui-datebox t_text w140" editable="false" name="filter_and_inspectionTimeEnd_LE_S" type="text" />
								</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
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
				<h3>案件列表</h3>
			</div>
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr">
				<div class="pl20 pt10">
					<span><input class="t_btnsty02" name="" type="button" value="新增" onclick="toAddPage();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="修改" onclick="toEditPage();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="删除" onclick="toDelete();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="详细" onclick="toDetailPage();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="添加财物" onclick="toTurnFinPage();" /></span>
				</div>
				<div class="hastable mt10">
					<table id="dg1" style="width:100%; height:350px" data-options="singleSelect:true,collapsible:false,
			            url:'${path}/cases/showAll.action',
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
								<th data-options="field:'caseNum',width:80" align="center">案件编号</th>
								<th data-options="field:'caseName',width:130" align="center">案件名称</th>
								<th data-options="field:'casePlace',width:150" align="center">案发地点</th>
								<th data-options="field:'caseTimeStart',width:100,formatter:formatSingelDate" align="center">案发时间(起)</th>
								<th data-options="field:'caseTimeEnd',width:100,formatter:formatSingelDate" align="center">案发时间(止)</th>
								<th data-options="field:'siteNum',width:80" align="center">现场勘验号</th>
								<th data-options="field:'inspectionTimeStart',width:100,formatter:formatSingelDate" align="center">勘验时间(起)</th>
								<th data-options="field:'inspectionTimeEnd',width:100,formatter:formatSingelDate" align="center">勘验时间(止)</th>
								<th data-options="field:'inspectionPerson',width:80" align="center">勘验人</th>
								<th data-options="field:'evidenceNum',width:60" align="center">财物个数</th>
								<th data-options="field:'jurisdiction',width:120, formatter:formatJurisdiction" align="center">管辖单位</th>
								<th data-options="field:'updater',width:50" align="center">更新者</th>
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
	<!--t_rightcontainer -->
	<!-- 添加弹窗 -->
	<div id="addInfo" class="easyui-window" title="新增案件信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 594px; height: 480px; padding: 20px;">
		<iframe id="frame_addInfo" width="520" height="404" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 修改弹窗 -->
	<div id="editInfo" class="easyui-window" title="修改案件信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 594px; height: 480px; padding: 20px;">
		<iframe id="frame_editInfo" width="520" height="404" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 详情弹窗 -->
	<div id="detailInfo" class="easyui-window" title="案件信息详情" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 594px; height: 480px; padding: 20px;">
		<iframe id="frame_detailInfo" width="520" height="404" scrolling="no" src="" frameborder="0"></iframe>
	</div>
	<!-- 添加财物弹窗 -->
	<div id="addFinInfo" class="easyui-window" title="新增财物信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 794px; height: 500px; padding: 20px;">
		<iframe id="frame_addFinInfo" width="740" height="430" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	
	<script type="text/javascript">
		$(function() {
			createTable('dg1');
			
			// 双击行显示案件详细信息
			$('#dg1').datagrid({
				onDblClickRow: function(rowIdex,rowData){
					toDetailPage();
				}
			});
		});

		// 案件编号
		function formatLink(value, row, index) {
			return "<a href='javascript:void(0);' onclick='toDetailPage(\"" + row.id + "\")'><u>" + row.caseNum + "</u></a>";
		}
		
		// 新增案件信息跳转
		function toAddPage() {
			// 添加iframeSrc
			$("#frame_addInfo").attr("src", "${path}/cases/toEditCases.action");
			// 打开弹出框
			$("#addInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
		}
		
		// 修改案件信息跳转
		function toEditPage() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要修改的案件！");
			} else {
				// 添加iframeSrc
				$("#frame_editInfo").attr("src", "${path}/cases/toEditCases.action?casesId=" + row.id);
				// 打开弹出框
				$("#editInfo").window('open');
			}
			adjustTanboxCenter(); // 弹窗位置居中
		}

		// 删除处理
		function toDelete() {
			var row = $('#dg1').datagrid('getSelected');
			if(null == row) {
				alertInfo("请选择要删除的案件!");
				return;
			}
			$.messager.confirm("确认提示", "确定删除该条案件信息吗？", function(r) {
				if (r) {
					// 提交删除请求
					$.ajax({
						url : "${path}/cases/delCases.action",
						data : {
							'casesId' : row.id
						},
						dataType : "json",
						type : "post",
						success : function(result) {
							if (result.status == "success") {
								alertInfo(result.data);
								reloadgrid('dg1');
							} else {
								alertInfo(result.data);
							}
						}
					});
				}
			});
		}

		// 案件信息详情
		function toDetailPage() {
			var row = $('#dg1').datagrid('getSelected'); 
			if(null == row) {
				alertInfo("请选择要查看的案件!");
				return;
			}
			// 添加iframeSrc
			$("#frame_detailInfo").attr("src", "${path}/cases/toDetailCases.action?casesId=" + row.id);
			// 打开弹出框
			$("#detailInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
		}
		
		//添加财物
		function toTurnFinPage() {
			var row = $('#dg1').datagrid('getSelected');
			if(null == row) {
				alertInfo("请选择财物关联的案件!");
				return;
			}else {
				$("#frame_addFinInfo").attr("src", "${path}/finances/toEditFinances.action?caseId="+row.id+"&caseName="+row.caseName+"&caseNum="+row.caseNum);
				// 打开弹出框
				$("#addFinInfo").window('open');
				adjustTanboxCenter(); // 弹窗位置居中
			}
		}
		
		// 添加之后返回
		function afterCloseAddCases() {
			$("#addInfo").window('close');
			reloadgrid('dg1');
		}

		// 修改之后返回
		function afterCloseEditCases() {
			$("#editInfo").window('close');
			reloadgrid('dg1');
		}

		//查看详情后返回
		function afterCloseCasesDetail() {
			$("#detailInfo").window('close');
			reloadgrid('dg1');
		}
		
		//添加财物 返回
		function afterCloseFinPage() {
			$("#addFinInfo").window('close');
			reloadgrid('dg1');
		}
		
		function setVal() {
			if(true==$("#isDel").is(':checked')) {
				$("#isDel").attr("value",1);
			}else {
				$("#isDel").attr("value",0);
			}
		}
		
		function resetClick() {
			$("input[name='filter_and_caseTimeStart_GE_S']").attr("value","");
			$("input[name='filter_and_caseTimeStart_LE_S']").attr("value","");
			$("input[name='filter_and_caseTimeEnd_GE_S']").attr("value","");
			$("input[name='filter_and_caseTimeEnd_LE_S']").attr("value","");
			$("input[name='filter_and_inspectionTimeStart_GE_S']").attr("value","");
			$("input[name='filter_and_inspectionTimeStart_LE_S']").attr("value","");
			$("input[name='filter_and_inspectionTimeEnd_GE_S']").attr("value","");
			$("input[name='filter_and_inspectionTimeEnd_LE_S']").attr("value","");
			$("#isDel").attr("value",0);
		}
		
	</script>
</body>
</html>