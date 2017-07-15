<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>涉案财物-财物列表页</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
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
								<td><input class="t_text w100" name="filter_and_financeNum_LIKE_S" type="text" id="financeNum" /></td>
								<td class="tr" width="100"><span class="t_span01">财物名称：</span></td>
								<td><input class="t_text w120" name="filter_and_financeName_LIKE_S" type="text" id="financeName" /></td>
								<td class="tr" width="100"><span class="t_span01">财物类型：</span></td>
								<td>
									<select name="filter_and_financeType_EQ_I" style="width: 120">
										<option value="">请选择</option>
										<c:forEach items="${financeTypeList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
									</select>
								</td>
								<td class="tr" width="100"><span class="t_span01">财物状态：</span></td>
								<td>
									<select name="filter_and_financeState_EQ_I" style="width: 120">
									<option value="">请选择</option>
										<c:forEach items="${financeStateList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
									</select>
								</td>
								<td class="tr" width="100"><span class="t_span01">财物识别：</span></td>
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
								<td colspan="3">
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeStart_GE_T" type="text" />
									<span class="t_span01">至</span> 
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeStart_LE_T" type="text" /></td>
								<td class="tr"><span class="t_span01">勘验时间(止)：</span></td>
								<td colspan="3">
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeEnd_GE_T" type="text" />
									<span class="t_span01">至</span> 
									<input class="easyui-datebox t_text w150" editable="false" name="filter_and_inspectionTimeEnd_LE_T" type="text" />
								</td>
								<td class="tr" width="120"><input type="checkbox" id="isDel" name="filter_and_isDel_LE_I" value="0" onclick="setVal()"/></td>
								<td ><span class="t_span01">包含已删除财物</span></td>
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
				<h3>财物列表</h3>
			</div>
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr">
				<div class="pl20 pt10">
					<span><input class="t_btnsty02" name="" type="button" value="入库" onclick="toInstock();" /></span>
					<span><input class="t_btnsty02" name="" type="button" value="出库" onclick="toOutstock();" /></span>
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
								<th data-options="field:'financeName',width:130" align="center">财物名称</th>
								<th data-options="field:'financeType',width:80" align=" center">财物类型</th>
								<th data-options="field:'financeState',width:100,formatter:formatFinanceState" align="center">财物状态</th>
								<th data-options="field:'row.cases.caseNum',width:100,formatter:formatCaseNum" align="center">案件编号</th>
								<th data-options="field:'row.cases.caseName',width:80,formatter:formatCaseName" align="center">案件名称</th>
								<th data-options="field:'row.cases.caseType',width:100,formatter:formatCaseType" align="center">案件类型</th>
								<th data-options="field:'row.cases.caseTime',width:150,formatter:formatCaseTime" align="center">案发时间(起-止)</th>
								<th data-options="field:'row.cases.siteNum',width:80,formatter:formatSiteNum" align="center">现场勘验号</th>
								<th data-options="field:'row.cases.inspectionTime',width:150,formatter:formatInspectionTime" align="center">勘验时间(起-止)</th>
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
	<!-- 入库弹窗 -->
	<div id="instockInfo" class="easyui-window" title="财物入库" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 568px; height: 390px; padding: 20px;">
		<iframe id="frame_instock" width="510" height="300" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 出库弹窗 -->
	<div id="outstockInfo" class="easyui-window" title="财物出库" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 500px; padding: 20px;">
		<iframe id="frame_outstock" width="510" height="300" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 详情弹窗 -->
	<div id="detailInfo" class="easyui-window" title="财物信息详情" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 480px; padding: 20px;">
		<iframe id="frame_detailInfo" width="520" height="404" scrolling="no" src="" frameborder="0"></iframe>
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


		// 财物状态
		function formatFinanceState(value,row,index) {
			if(""!=row.financeState && "3"==row.financeState) {
				return "出库";
			}else if(""!=row.financeState && "2"==row.financeState){
				return "入库";
			}else {
				return "登记";
			}
		}

		// 案件编号
		function formatCaseNum(value,row,index){
            if(row.cases.id){
                return row.cases.caseNum;
            }
        }

		//案件名称		
		function formatCaseName(value,row,index){
            if(row.cases.id){
                return row.cases.caseName;
            }
        }
		
		//案件类型
		function formatCaseType(value,row,index){
            if(row.cases.id){
                return row.cases.caseType;
            }
        }
		
		//案发时间(起-止)
//		function formatCaseTime(value,row,index){
//            if(row.cases.id){
//                return row.cases.caseTimeStart + row.cases.caseTimeEnd;
//            }
//        }
		
		//现场勘验号
		function formatSiteNum(value,row,index){
            if(row.cases.id){
                return row.cases.siteNum;
            }
        }
		//勘验时间(起-止)
//		function formatInspectionTime(value,row,index){
//            if(row.cases.id){
//                return row.cases.inspectionTimeStart + row.cases.inspectionTimeEnd;
//            }
//        }

		// 财物编号
//		function formatLink(value, row, index) {
//			return "<a href='javascript:void(0);' onclick='toDetailPage(\"" + row.id + "\")'><u>" + row.financeNum + "</u></a>";
//		}
		
		// 入库信息跳转
		function toInstock() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要入库的财物！");
			} else if(1 == row.isDel){
				alertInfo("该财物已删除！不能进行入库操作！");
			} else if(2 == row.financeState) {   //1登记  2在库  3不在库
					alertInfo("该财物已在库！");
			} else {
				// 添加iframeSrc
				$("#frame_instock").attr("src", "${path}/stock/toInstock.action?financesId=" + row.id);
				// 打开弹出框
				$("#instockInfo").window('open');
			}
			adjustTanboxCenter(); // 弹窗位置居中
		}
		
		// 出库信息跳转
		function toOutstock() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要出库的财物！");
			} else if(1 == row.isDel){
				alertInfo("该财物已删除！不能进行出库操作！");
			} else if(2 != row.financeState) {  //1登记  2在库  3不在库
				alertInfo("该财物不在库！");
			}  else {
				// 添加iframeSrc
				$("#frame_outstock").attr("src", "${path}/stock/toOutstock.action?financesId=" + row.id);
				// 打开弹出框
				$("#outstockInfo").window('open');
			}
			adjustTanboxCenter(); // 弹窗位置居中
		}

		
		// 添加之后返回
		function afterCloseInstock() {
			$("#instockInfo").window('close');
			reloadgrid('dg1');
		}

		// 修改之后返回
		function afterCloseOutstock() {
			$("#outstockInfo").window('close');
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