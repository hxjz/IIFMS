<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>涉案财物-财物列表页</title>
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
				<h3>财物信息查询</h3>
			</div>
			<!-- t_oneblock_h-->
			<form>
				<div class="t_oneblock_c pr">
					<div class="nobortable pt10 pl10">
						<table width="100%" border="0">
							<tr>
								<td class="tr" width="100"><span class="t_span01">财物编号：</span></td>
								<td><input class="t_text w160" name="filter_and_financeNum_LIKE_S" type="text" id="financeNum" /></td>
								<td class="tr" width="100"><span class="t_span01">财物名称：</span></td>
								<td><input class="t_text w160" name="filter_and_financeName_LIKE_S" type="text" id="financeName" /></td>
								<td class="tr" width="100"><span class="t_span01">财物类型：</span></td>
								<td>
									<select name="filter_and_financeType_EQ_I" style="width: 160">
										<option value="">请选择</option>
										<c:forEach items="${financeTypeList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
									</select>
								</td>
								<td class="tr" width="100"><span class="t_span01">财物状态：</span></td>
								<td>
									<select name="filter_and_financeState_EQ_I" style="width: 160">
									<option value="">请选择</option>
										<c:forEach items="${financeStateList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tr" width="100"><span class="t_span01">财物识别：</span></td>
								<td><input class="t_text w160" name="filter_and_financeCode_LIKE_S" type="text" id="financeCode" /></td>
								<td class="tr" width="100"><span class="t_span01">案件编号：</span></td>
								<td><input class="t_text w160" name="filter_and_cases__caseNum_LIKE_S" type="text" id="caseNum" /></td>
								<td class="tr" width="100"><span class="t_span01">案件名称：</span></td>
								<td><input class="t_text w160" name="filter_and_cases__caseName_LIKE_S" type="text" id="caseName" /></td>
								<td class="tr" width="100"><span class="t_span01">案发地点：</span></td>
								<td><input class="t_text w160" name="filter_and_cases__casePlace_LIKE_S" type="text" id="casePlace" /></td>
							</tr>
							<tr>
								<td class="tr" width="100"><span class="t_span01">现场勘验号：</span></td>
								<td><input class="t_text w160" name="filter_and_cases__siteNum_LIKE_S" type="text" id="siteNum" /></td>
								<td class="tr" width="100"><span class="t_span01">电子识别：</span></td>
								<td><input class="t_text w160" name="filter_and_digitalCode_LIKE_S" type="text" id="digitalCode" /></td>
								<td class="tr" width="120"><span class="t_span01">电子识别码：</span></td>
								<td>
									<input class="t_text w100" name="filter_and_digitalCode_LIKE_S" type="text" id="digitalCodeNew" />
									<input type="submit" class="t_btnsty01" id="selectBtn" value="识别">
								</td>
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
								<td colspan="2" width="300" align="left" style="position:absolute;margin-left:-110px;">
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
				<h3>财物列表</h3>
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
								<th data-options="field:'financeNum',width:80" align="center">财物编号</th>
								<th data-options="field:'financeName',width:80" align="center">财物名称</th>
								<th data-options="field:'financeType',width:80,formatter:formatFinanceType" align=" center">财物类型</th>
								<th data-options="field:'financeState',width:60,formatter:formatFinanceState" align="center">财物状态</th>
								<th data-options="field:'row.cases.caseNum',width:100,formatter:formatCaseNum" align="center">案件编号</th>
								<th data-options="field:'row.cases.caseName',width:80,formatter:formatCaseName" align="center">案件名称</th>
								<th data-options="field:'row.cases.caseType',width:100,formatter:formateCaseType" align="center">案件类型</th>
								<th data-options="field:'row.cases.caseTime',width:160,formatter:formatCaseTime" align="center">案发时间(起-止)</th>
								<th data-options="field:'row.cases.siteNum',width:80,formatter:formatSiteNum" align="center">现场勘验号</th>
								<th data-options="field:'row.cases.inspectionTime',width:160,formatter:formatInspectionTime" align="center">勘验时间(起-止)</th>
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
	<div id="addInfo" class="easyui-window" title="新增财物信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 800px; height: 500px; padding: 20px;">
		<iframe id="frame_addInfo" width="600" height="430" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 修改弹窗 -->
	<div id="editInfo" class="easyui-window" title="修改财物信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 644px; height: 500px; padding: 20px;">
		<iframe id="frame_editInfo" width="520" height="430" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 详情弹窗 -->
	<div id="detailInfo" class="easyui-window" title="财物信息详情" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 1090px; height: 520px; padding: 20px;overflow-x:hidden">
		<iframe id="frame_detailInfo" width="1000" height="500" scrolling="no" src="" frameborder="0"></iframe>
	</div>

	<script type="text/javascript">
		$(function() {
			createTable('dg1');

			// 双击行显示财物详细信息
			$('#dg1').datagrid({
				onDblClickRow: function(rowIdex,rowData){
					toDetailPage();
				}
			});
		});


		// 新增财物信息跳转
		function toAddPage() {
			// 添加iframeSrc
			$("#frame_addInfo").attr("src", "${path}/finances/toEditFinances.action");
			// 打开弹出框
			$("#addInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
		}

		// 修改财物信息跳转
		function toEditPage() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要修改的财物！");
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
				alertInfo("请选择要删除的财物!");
				return;
			}
			$.messager.confirm("确认提示", "确定删除该条财物信息吗？", function(r) {
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

		// 财物信息详情
		function toDetailPage() {
			var row = $('#dg1').datagrid('getSelected');
			if(null == row) {
				alertInfo("请选择要查看的财物!");
				return;
			}
			// 添加iframeSrc
			$("#frame_detailInfo").attr("src", "${path}/finances/toDetailFinances.action?financesId=" + row.id);
			// 打开弹出框
			$("#detailInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
		}


		// 添加之后返回
		function afterCloseAddWindow() {
			$("#addInfo").window('close');
			reloadgrid('dg1');
		}

		// 修改之后返回
		function afterCloseEditWindow() {
			$("#editInfo").window('close');
			reloadgrid('dg1');
		}



        // 案件名称
        function formatCaseName(value,row,index){
            if(row.cases&&row.cases.id){
                return row.cases.caseName;
            }
        }

       // 案件编号
        function formatCaseNum(value,row,index){
            if(row.cases&&row.cases.id){
                return row.cases.caseNum;
            }
        }

        //案件类型
        function formateCaseType(value,row,index){
            if(row.cases.id){
                return formatCaseType(row.cases.caseType);
            }
        }



        //现场勘验号
        function formatSiteNum(value,row,index){
            if(row.cases&&row.cases.id){
                return row.cases.siteNum;
            }
        }

		//查看详情后返回
		function afterCloseDetailWindow() {
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

        function resetClick() {
            $("input[name='filter_and_cases__caseTimeStart_GE_S']").attr("value","");
            $("input[name='filter_and_cases__caseTimeStart_LE_S']").attr("value","");
            $("input[name='filter_and_cases__caseTimeEnd_GE_S']").attr("value","");
            $("input[name='filter_and_cases__caseTimeEnd_LE_S']").attr("value","");
            $("input[name='filter_and_cases__inspectionTimeStart_GE_S']").attr("value","");
            $("input[name='filter_and_cases__inspectionTimeStart_LE_S']").attr("value","");
            $("input[name='filter_and_cases__inspectionTimeEnd_GE_S']").attr("value","");
            $("input[name='filter_and_cases__inspectionTimeEnd_LE_S']").attr("value","");
            $("#isDel").attr("value",0);
        }
	</script>
</body>
</html>