<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>存储位置选择</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
	<div >
		<div class="t_oneblock w400">
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr ">
				<div class="nobortable pt10 pl10">
					<form>
						<table width="" border="0">
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
					</form>
				</div>
				<!--nobortable -->
				<div class="pl20 pt10">
					<span><input class="t_btnsty01" name="" type="button" value="确认" onclick="selectBack()" /> </span> 
					<span><input class="t_btnsty01" name="" type="button" value="取消" onclick="parent.afterCloseSelectWindow();" /> </span>
				</div>
				<div class="hastable mt10">
					<table id="dg" style="width:390px;height:200px" data-options="singleSelect:true,collapsible:false,
			            url:'${path}/storage/showStorage.action',
			            method:'post',
			            nowrap:false,
						onDblClickRow:selectBack,
			            fitColumns:true,
			            rownumbers:false,
						autoRowHeight:true,
						pagination:true,
						pageNumber:1,
						pageSize:50,
						pageList:[10,30,50]">
							<thead>
								<tr>
									<th data-options="field:'id',hidden:true" align="center">Id</th>
									<th data-options="field:'level',hidden:true" align="center">level</th>
									<th data-options="field:'status',hidden:true" align="center">status</th>
									<th data-options="field:'type',width:160,formatter:formatType" align="center">存储类型</th>
									<th data-options="field:'name',width:160,formatter:formatName" align="center">显示名称</th>
									<th data-options="field:'status',width:60,formatter:formatStatus" align="center">存储状态</th>
								</tr>
							</thead>
					</table>
				</div>
			</div>
			<!-- t_oneblock_c-->
		</div>
	</div>
	<!--t_rightcontainer -->
	<script>
		$(document).ready(function(){
			createTable('dg');
		});
		
		//选择后确认返回
		function selectBack(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows[0].level!='3') {
				alertInfo("请重新选择最底层能存储的位置！");
				return;
			}
			if(rows[0].status==1) {
				alertInfo("该位置已使用，请重新选择！");
				return;
			}
			if(rows.length) {
				var vStorageName=$("#storageName").find("option:selected").text(); 
				var vStorageLevel1=$("#storageLevel1").find("option:selected").text(); 
				var vStorageLevel2=$("#storageLevel2").find("option:selected").text(); 
				var vName;
				if( rows[0].type=='1') {
					vName="第"+rows[0].name+"门";
				}else {
					vName="第"+rows[0].name+"层";
				}
				rows[0].name=vStorageName+vStorageLevel1+vStorageLevel2+vName;
				parent.handleSelectStorage(rows[0]);
			} else {
				alertWarning(OPRATION_PLEASE_CHOOSE_ONE);
			}
		}
		
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
								var option = "<option value='"+valine.key+"'>" + valine.value + "</option>";
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
		
	</script>
</body>
</html>