<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>存储位置管理</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>

<body>
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>存储位置查询</h3>
			</div>
			<form method="post" id="searchForm">
				<div class="t_oneblock_c pr">
					<div class="nobortable pt10 pl10">
						<table width="100%" border="0">
							<tr>
								<td class="tr" width="100"><span class="t_span01">存储名称：</span></td>
								<td><select id="storageName" style="width: 120" onchange="storageNameChange(this.value)">
										<option value="">请选择</option>
										<c:forEach items="${storageNameList}" var="object">
											<option value="${object.key}">${object.value}</option>
										</c:forEach>
								</select></td>
								<td class="tr" width="100"><span class="t_span01">一级显示：</span></td>
								<td><select id="storageLevel1" style="width: 120" onchange="storageLevel1Change(this.value)">
										<option value=''>请选择</option>
								</select></td>
								<td class="tr" width="100"><span class="t_span01">二级显示：</span></td>
								<td><select id="storageLevel2" style="width: 120" onchange="storageLevel2Change(this.value)">
										<option value=''>请选择</option>
								</select></td>
								<td class="tr" width="100"><span class="t_span01">三级显示：</span></td>
								<td><select id="storageLevel3" style="width: 120" onchange="storageLevel3Change(this.value)">
										<option value=''>请选择</option>
								</select></td>
							</tr>
							<tr>
								<td colspan="8" align="right">
								<input id="parentId" name="filter_and_parentId_EQ_S" type="hidden" value="0" /> 
								<input id="id" name="filter_and_id_EQ_S" type="hidden" /> 
								<input name="filter_and_isDel_EQ_I" type="hidden" value="0" /> 
								<input name="order_createTime_T" type="hidden" value="desc" /> 
								<input class="t_btnsty01" id="find" name="select" type="button" value="查询" /> 
								<input class="t_btnsty01" type="reset" value="重置" onclick="resetClick()" />&nbsp;&nbsp;&nbsp;</td>
							</tr>
						</table>
					</div>
					<!--nobortable -->
				</div>
			</form>
		</div>
	</div>
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>存储位置列表</h3>
			</div>
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr">
				<div class="pl20 pt10">
					<span><input class="t_btnsty02" name="" type="button" value="新增" onclick="toAddPage();" /></span> 
					<!-- <span><input class="t_btnsty02" name="" type="button" value="修改" onclick="toEditPage();" /></span>  -->
					<span><input class="t_btnsty02" name="" type="button" value="删除" onclick="toDelete();" /></span> 
				</div>
				<div class="hastable mt10">
					<table id="dg1" style="width: 100%; height: 350px"
						data-options="singleSelect:true,collapsible:false,
			            url:'${path}/storage/showStorage.action',
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
								<th data-options="field:'id',hidden:true" align="center">Id</th>
								<th data-options="field:'level',hidden:true" align="center">level</th>
								<!-- <th data-options="hidden:true" align="center">操作</th> -->
								<th data-options="field:'type',width:160,formatter:formatType" align="center">存储类型</th>
								<th data-options="field:'name',width:160,formatter:formatName" align="center">显示名称</th>
								<th data-options="field:'status',width:60,formatter:formatStatus" align="center">存储状态</th>
								<th data-options="field:'device',width:150" align="center">设备信息</th>
								<th data-options="field:'comment',width:150" align="center">备注</th>
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
	
	<!-- 添加弹窗 -->
	<div id="addInfo" class="easyui-window" title="新增案件信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 480px; padding: 20px;">
		<iframe id="frame_addInfo" width="520" height="404" scrolling="no" src="" frameborder="0"> </iframe>
	</div>
	<!-- 修改弹窗 -->
	<div id="editInfo" class="easyui-window" title="修改案件信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 574px; height: 480px; padding: 20px;">
		<iframe id="frame_editInfo" width="520" height="404" scrolling="no" src="" frameborder="0"> </iframe>
	</div>

	<script type="text/javascript">
		$(function() {
			createTable('dg1');
		});

		function formatType(value, rowData, rowIndex) {
			if (rowData.type == '2') {
				return "密集柜/区";
			} else {
				return "存储区";
			}
		}
		
		function formatName(value, rowData, rowIndex) {
			if(rowData.level == '1') {
				if( rowData.type == '1') {
					return "第"+rowData.name+"排";
				}else {
					return "第"+rowData.name+"列";
				}
			}
			if(rowData.level == '2') {
				return "第"+rowData.name+"组";
			}
			if(rowData.level == '3') {
				if( rowData.type == '1') {
					return "第"+rowData.name+"门";
				}else {
					return "第"+rowData.name+"层";
				}
			}else {
				return rowData.name;
			}
		}

		function formatStatus(value, rowData, rowIndex) {
			if (rowData.status == '1') {
				return "使用中";
			} else {
				return "未使用";
			}
		}

		function storageNameChange(value) {
			$("#id").attr("value", "");
			$("#parentId").attr("value", value);
			if ('' == value) {
				$("#parentId").attr("value", "0");
			}

			var lineOption = "<option value=''>请选择</option>";
			$("#storageLevel1").empty();
			$("#storageLevel1").append(lineOption);
			$("#storageLevel2").empty();
			$("#storageLevel2").append(lineOption);
			$("#storageLevel3").empty();
			$("#storageLevel3").append(lineOption);

			if ('' != value) {
				$.ajax({
					url : "${path}/storage/getLevelInfo.action",
					data : {
						'parentId' : value
					},
					dataType : "json",
					type : "post",
					success : function(result) {
						if (result.status == "success") {
							var va = result.data;
							for (var i = 0; i < va.length; i++) {
								var valine = eval(va[i]);
								var option = "<option value='"+valine.key+"'>" + valine.value + "</option>";
								$("#storageLevel1").append(option);

							}

						} else {
							alertInfo("请重新选择！");
						}
					}
				});
			}

			$("#find").click();
		}

		function storageLevel1Change(value) {
			$("#id").attr("value", "");

			var lineOption = "<option value=''>请选择</option>";
			$("#storageLevel2").empty();
			$("#storageLevel2").append(lineOption);
			$("#storageLevel3").empty();
			$("#storageLevel3").append(lineOption);

			if ('' != value) {
				$("#parentId").attr("value", value);

				$.ajax({
					url : "${path}/storage/getLevelInfo.action",
					data : {
						'parentId' : value
					},
					dataType : "json",
					type : "post",
					success : function(result) {
						if (result.status == "success") {
							var va = result.data;
							for (var i = 0; i < va.length; i++) {
								var valine = eval(va[i]);
								var option = "<option value='"+valine.key+"'>"
										+ valine.value + "</option>";
								$("#storageLevel2").append(option);

							}
						} else {
							alertInfo("请重新选择！");
						}
					}
				});
			} else {
				$("#parentId").attr("value", $("#storageName").val());
			}

			$("#find").click();
		}

		function storageLevel2Change(value) {
			$("#id").attr("value", "");

			var lineOption = "<option value=''>请选择</option>";
			$("#storageLevel3").empty();
			$("#storageLevel3").append(lineOption);

			if ('' != value) {
				$("#parentId").attr("value", value);

				$.ajax({
					url : "${path}/storage/getLevelInfo.action",
					data : {
						'parentId' : value
					},
					dataType : "json",
					type : "post",
					success : function(result) {
						if (result.status == "success") {
							var va = result.data;
							for (var i = 0; i < va.length; i++) {
								var valine = eval(va[i]);
								var option = "<option value='"+valine.key+"'>"
										+ valine.value + "</option>";
								$("#storageLevel3").append(option);

							}
						} else {
							alertInfo("请重新选择！");
						}
					}
				});
			} else {
				$("#parentId").attr("value", $("#storageLevel1").val());
			}

			$("#find").click();
		}

		function storageLevel3Change(value) {
			if ('' != value) {
				$("#id").attr("value", value);
				$("#parentId").attr("value", "");
			} else {
				$("#id").attr("value", "");
				$("#parentId").attr("value", $("#storageLevel2").val());
			}

			$("#find").click();
		}

		function resetClick() {
			$("#id").attr("value", "");
			$("#parentId").attr("value", "0");

			$("#find").click();
		}
		
		// 添加之后返回
		function afterCloseAddStorage() {
			$("#addInfo").window('close');
			reloadgrid('dg1');
		}

		// 修改之后返回
		function afterCloseEditStorage() {
			$("#editInfo").window('close');
			reloadgrid('dg1');
		}
		
		// 新增存储信息跳转
		function toAddPage() {
			// 添加iframeSrc
			$("#frame_addInfo").attr("src", "${path}/storage/toEditStorage.action");
			// 打开弹出框
			$("#addInfo").window('open');
			adjustTanboxCenter(); // 弹窗位置居中
		}
		
		// 修改存储信息跳转
		function toEditPage() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要修改的案件！");
			} else {
				// 添加iframeSrc
				$("#frame_editInfo").attr("src", "${path}/storage/toEditStorage?id=" + row.id);
				// 打开弹出框
				$("#editInfo").window('open');
			}
			adjustTanboxCenter(); // 弹窗位置居中
		}

		// 删除处理
		function toDelete() {
			var row = $('#dg1').datagrid('getSelected');
			if (null == row) {
				alertInfo("请选择要删除的存储位置!");
				return;
			}
			$.messager.confirm("确认提示", "确定删除该条存储位置信息吗？", function(r) {
				if (r) {
					// 提交删除请求
					$.ajax({
						url : "${path}/storage/delStorage.action",
						data : {
							'id' : row.id
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
	</script>
</body>
</html>
