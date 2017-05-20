<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>涉案财物-财务列表页</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>

<body>
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>财务信息查询</h3>
			</div>
			<!-- t_oneblock_h-->
			<form>
				<div class="t_oneblock_c pr">
					<div class="nobortable pt10 pl10">
						<table width="100%" border="0">
							<tr>
								<td class="tr" width="100"><span class="t_span01">财务编号：</span></td>
								<td><input class="t_text w100" name="filter_and_financeNum_LIKE_S" type="text" id="financeNum" /></td>
								<td class="tr" width="100"><span class="t_span01">财务名称：</span></td>
								<td><input class="t_text w120" name="filter_and_financeName_LIKE_S" type="text" id="financeName" /></td>
								<td class="tr" width="100"><span class="t_span01">财务类型：</span></td>
								<td>
									<select name="filter_and_financeType_EQ_I" style="width: 120">
										<option value="">请选择</option>
										<option value="1">手迹痕迹</option>
										<option value="2">足迹痕迹</option>
										<option value="3">其他</option>
									</select>
								</td>
								<td class="tr" width="100"><span class="t_span01">财务状态：</span></td>
								<td>
									<select name="filter_and_financeState_EQ_I" style="width: 120">
									<option value="">请选择</option>
									<option value="1">登记</option>
									<option value="2">在库</option>
									<option value="3">出库</option>
								</select>
								</td>
								<td class="tr" width="100"><span class="t_span01">财务识别：</span></td>
								<td><input class="t_text w100" name="filter_and_financeCode_LIKE_S" type="text" id="financeCode" /></td>
							</tr>
							<tr>
								<td class="tr" width="100"><span class="t_span01">案件编号：</span></td>
								<td><input class="t_text w100" name="filter_and_caseNum_LIKE_S" type="text" id="caseNum" /></td>
								<td class="tr" width="100"><span class="t_span01">案件名称：</span></td>
								<td><input class="t_text w120" name="filter_and_caseName_LIKE_S" type="text" id="caseName" /></td>
								<td class="tr" width="100"><span class="t_span01">案发地点：</span></td>
								<td><input class="t_text w180" name="filter_and_casePlace_LIKE_S" type="text" id="casePlace" /></td>
								<td class="tr" width="100"><span class="t_span01">现场勘验号：</span></td>
								<td><input class="t_text w100" name="filter_and_siteNum_LIKE_S" type="text" id="siteNum" /></td>
								<td class="tr" width="100"><span class="t_span01">电子识别：</span></td>
								<td><input class="t_text w100" name="filter_and_digitalCode_LIKE_S" type="text" id="digitalCode" /></td>
							</tr>
							<tr>
								<td class="tr" width="120"><span class="t_span01">案发时间(起)：</span></td>
								<td colspan="3" width="260">
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_financeTimeStart_GE_T" type="text" />
									<span class="t_span01">至</span>
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_financeTimeStart_LE_T" type="text" /></td>
								<td class="tr" width="120"><span class="t_span01">案发时间(止)：</span></td>
								<td colspan="3" width="260">
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_financeTimeEnd_GE_T" type="text" />
									<span class="t_span01">至</span>
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_financeTimeEnd_LE_T" type="text" /></td>
								<td class="tr" width="120"><span class="t_span01">电子识别：</span></td>
								<td>
									<input class="t_text w100" name="filter_and_digitalCode_LIKE_S" type="text" id="digitalCodeNew" />
									<input type="submit" class="t_btnsty01" id="selectBtn" value="识别">
								</td>
							</tr>
							<tr>
								<td class="tr"><span class="t_span01">勘验时间(起)：</span></td>
								<td colspan="3"><input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeStart_GE_T" type="text" />
								<span class="t_span01">至</span> 
								<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeStart_LE_T" type="text" /></td>
								<td class="tr"><span class="t_span01">勘验时间(止)：</span></td>
								<td colspan="3">
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeEnd_GE_T" type="text" />
									<span class="t_span01">至</span> 
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeEnd_LE_T" type="text" />
								</td>
								<td class="tr" width="120"><input type="checkbox" id="isDel" name="filter_and_isDel_LE_I" value="0" onclick="setVal()"/></td>
								<td ><span class="t_span01">包含已删除财务</span></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>
									<input type="hidden" name="order_updateTime_T" value="desc"/>
									<input class="t_btnsty01" id="find" name="select" type="button" value="查询" />
								</td>
								<td><input class="t_btnsty01" type="reset" value="重置" /></td>
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
				<h3>财务列表</h3>
			</div>
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr">
				<div class="pl20 pt10">
					<span><input class="t_btnsty02" name="" type="button" value="新增" onclick="toAddPage();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="修改" onclick="toEditPage();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="删除" onclick="toDelete();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="详细" onclick="toDetailPage();" /></span>
				</div>
				<div class="hastable mt10">
					<table id="dg1" style="width:100%; height:350px" data-options="singleSelect:true,collapsible:false,
			            url:'${path}/finances/showAll.action',
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
								<th data-options="field:'financeNum',width:80" align="center">财务编号</th>
								<th data-options="field:'financeName',width:130" align="center">财务名称</th>
								<th data-options="field:'financeType',width:150" align=" center">财务类型</th>
								<th data-options="field:'financeState',width:100" align="center">财务状态</th>
								<th data-options="field:'financeTimeEnd',width:100" align="center">案件编号</th>
								<th data-options="field:'cases[caseName]',width:80" align="center">案件名称</th>
								<%--<th data-options="valueField:'cases.caseType',width:100" align="center">案件类型</th>--%>
								<th data-options="field:'cases_caseName',width:100" align="center">案发时间(起-止)</th>
								<th data-options="field:'inspectionPerson',width:80" align="center">现场勘验号</th>
								<th data-options="field:'evidenceNum',width:60" align="center">勘验时间(起-止)</th>
								<th data-options="field:'storeLocation',width:120" align="center">存放位置</th>
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
	<div id="addInfo" class="easyui-window" title="新增财务信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 500px; padding: 20px;">
		<iframe id="frame_addInfo" width="520" height="430" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 修改弹窗 -->
	<div id="editInfo" class="easyui-window" title="修改财务信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 500px; padding: 20px;">
		<iframe id="frame_editInfo" width="520" height="430" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 详情弹窗 -->
	<div id="detailInfo" class="easyui-window" title="财务信息详情" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 480px; padding: 20px;">
		<iframe id="frame_detailInfo" width="520" height="404" scrolling="no" src="" frameborder="0"></iframe>
	</div>
	
	<script type="text/javascript">
		$(function() {
			createTable('dg1');
			
			// 双击行显示财务详细信息
			$('#dg1').datagrid({
				onDblClickRow: function(rowIdex,rowData){
					toDetailPage();
				}
			});
		});


		// 案件编号
		function formatLink() {
			return value.caseName;
		}

		// 财务编号
//		function formatLink(value, row, index) {
//			return "<a href='javascript:void(0);' onclick='toDetailPage(\"" + row.id + "\")'><u>" + row.financeNum + "</u></a>";
//		}
		
		// 新增财务信息跳转
		function toAddPage() {
			// 添加iframeSrc
			$("#frame_addInfo").attr("src", "${path}/finances/toEditFinances.action");
			// 打开弹出框
			$("#addInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
		}
		
		// 修改财务信息跳转
		function toEditPage() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要修改的财务！");
			} else {
				// 添加iframeSrc
				$("#frame_editInfo").attr("src", "${path}/finances/toEditFinances.action?financesId=" + row.id);
				// 打开弹出框
				$("#editInfo").window('open');
			}
			adjustTanboxCenter(); // 弹窗位置居中
		}

		// 删除处理
		function toDelete() {
			var row = $('#dg1').datagrid('getSelected');
			if(null == row) {
				alertInfo("请选择要删除的财务!");
				return;
			}
			$.messager.confirm("确认提示", "确定删除该条财务信息吗？", function(r) {
				if (r) {
					// 提交删除请求
					$.ajax({
						url : "${path}/finances/delFinances.action",
						data : {
							'financesId' : row.id
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

		// 财务信息详情
		function toDetailPage() {
			var row = $('#dg1').datagrid('getSelected'); 
			if(null == row) {
				alertInfo("请选择要查看的财务!");
				return;
			}
			// 添加iframeSrc
			$("#frame_detailInfo").attr("src", "${path}/finances/toDetailFinances.action?financesId=" + row.id);
			// 打开弹出框
			$("#detailInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
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
		
		function setVal() {
			if(true==$("#isDel").is(':checked')) {
				$("#isDel").attr("value",1);
			}else {
				$("#isDel").attr("value",0);
			}
		}

	</script>
</body>
</html>