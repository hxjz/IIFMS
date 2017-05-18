<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%
		String path = request.getContextPath();
		request.setAttribute("path", path);
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单管理</title>
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/icon.css">
<link href="${path }/css/default/base.css" rel="stylesheet" type="text/css"/>
<link href="${path }/css/default/rightpart.css" rel="stylesheet" type="text/css"/>
<link href="${path}/jsLibs/dtree/dtree.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="${path }/scripts/validatebox-ruls.js"></script>
<script type="text/javascript" src="${path }/scripts/date.js"></script>
<script type="text/javascript" src="${path }/scripts/dialog.js"></script>
<script src="${path }/jsLibs/dtree/wtree.js" type="text/javascript"></script>
<script src="${path }/jsLibs/jquery.validate.js" type="text/javascript"></script>
</head>
<body>
	<!--定义iframe,新增加目录时候用到！ -->
	<div id="easyuiWindowBox" class="easyui-window"
		data-options="modal:true,closed:true,minimizable:false,maximizable:false,collapsible:false"
		style="padding: 10px; overflow: hidden;">
		<iframe id="frame_content" src="" scrolling="no" frameborder="0" width="900" height="300"></iframe>
	</div>
	<center>
		<!-- <div id="main"> -->
		<div id="leftDiv" align="left">
			<span style="color: #FF0000">*</span><span class="t_span01">系统菜单管理</span> <span id="deptServiceItem"></span><br />
			<div id="systree" style="width: 158px; float: left">
				<script type="text/javascript">
					var parentId;
					d = new dTree('d', '${path}/jsLibs/dtree/images/system/menu/');
					d.config.folderLinks = true;
					d.config.useCookies = false;
					d.add('1', -1, '系统菜单树', "javascript:queryChildren('1');",
							'');
					<c:forEach  items="${elementListForTree}" var="obj2">
						d.add(
							'${obj2.id}',
							'${obj2.parentId}',
							'${obj2.name}',
							"javascript:queryChildren('${obj2.id}');",
							'');
					</c:forEach>
					
					document.getElementById('systree').innerHTML = d;
					d.clearCookie();//清空cookie  

					function queryChildren(nodeId) { //添加节点点击事件   
						$("#parentId").val(nodeId);
						//直接调用方法
						findByCondition('dg');
						//并给parentId赋值
						parentId = nodeId;
						$("#resource.parentId").text(parentId);
					}
				</script>
				<script>
					// 				//是否启用
					var isEnableData = [ {
						'id' : '1',
						'text' : '启用'
					}, {
						'id' : '0',
						'text' : '禁用'
					} ];

					//图示
					var iconData = [ {
						'id' : 'leafMenuIcon',
						'text' : 'leafMenuIcon'
					}, {
						'id' : 'bmModule',
						'text' : 'bmModule'
					} ];
				</script>
			</div>

			<div id="rightDiv">
				<form id="ajaxForm" method="post">
					<input type="hidden" name="filter_and_parentId_EQ_L" id="parentId" />
					<input type="hidden" name="order_orderCode" value="asc" />
				</form>

				<div class="t_rightcontainer">
					<span><input class="t_btnsty01" id="addNewItem"
						type="button" value="添加" onclick="addNewItem()" /> </span> <span><input
						class="t_btnsty01" name="" type="button" value="保存"
						onclick="save()" /> </span> <span><input class="t_btnsty01"
						name="" type="button" value="删除" onclick="deleteItem()" /> </span>
				</div>
				<div class="t_rightcontainer">
					<table class="navigation">
						<tr>
							<td class="location"><web:location value="系统菜单管理-菜单模块" /></td>
						</tr>
					</table>

					<div class="content">
						<div class="tabscontnet" id="tab_contents1">
							<div class="info clearfix">
								<div class="hastable hgt_sty01">
									<table id="dg"
										data-options="
					                    	fit:true,
											rownumbers:true,
											autoRowHeight:true,
											singleSelect:true,
											pagination:true,
											pageNumber:1,
											pageSize:50,
											nowrap:false,
											onDblClickRow:updateItem,
											url:'${path}/system/resource/showAll.action',
											pageList:[10,30,50]">
										<thead data-options="frozen:true">
											<thead>
												<tr>
													<th field="orderCode" width="60" align="center"
														hidden="true">orderCode</th>
													<th field="id" width="60" align="center" hidden>ID</th>
													<th field="name" width="180" align="center"
														data-options="editor:'text'">名称</th>
													<th field="url" width="320" align="center"
														data-options="editor:'text'">URL</th>
													<th field="isEnable" width="100" align="center"
														editor="{type:'combobox',options:{editable:false,data:isEnableData,valueField:'id',textField:'text'}}">启用状态</th>
													<th field="order" width="60" align="center"
														data-options="editor:'text'">排序</th>
													<th field="iconCls" width="120" align="left"
														editor="{type:'combobox',options:{editable:false,data:iconData,valueField:'id',textField:'text'}}">图示</th>
													<!-- <th field="parentId" width="60" align="center">parentId</th> -->
												</tr>
											</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</center>

	<script type="text/javascript">
		var editFlag = undefined;
		//添加一条记录
		function addNewItem() {
			if (parentId && parentId != 1) {
				$("#frame_content").attr('width', '900');
				$("#frame_content").attr('height', '220');
				$("#frame_content").attr("src", "${path}/system/resource/addNewResource.action?parentId=" + parentId);
				$('#easyuiWindowBox').window({
					width : 600,
					title : "添加子菜单"
				});
				$('#easyuiWindowBox').window('open');
				adjustTanboxCenter();
			} else {
				$("#frame_content").attr('width', '900');
				$("#frame_content").attr('height', '220');
				$("#frame_content").attr("src", "${path}/system/resource/addNewResourceModule.action");
				$('#easyuiWindowBox').window({
					width : 600,
					title : "添加新模块"
				});
				$('#easyuiWindowBox').window('open');
				adjustTanboxCenter();
			}
		}
		//结束编辑
		function endEdit() {
			var rows = $('#dg').datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$('#dg').datagrid('endEdit', i);
			}
			editFlag = undefined;
		}
		//双击修改记录
		function updateItem() {
			var rows = $("#dg").datagrid('getSelections');
			if (rows.length == 1) {//选中一行的话触发事件
				var index = $("#dg").datagrid('getRowIndex', rows[0]);
				if (editFlag != undefined) {
					$("#dg").datagrid('endEdit', editFlag);
				}
				$("#dg").datagrid('beginEdit', index);
			}

		}

		//保存按钮
		function save() {
			endEdit();
			//修改的行
			var updatedRow = $('#dg').datagrid('getChanges', 'updated');
			var updateItem = JSON.stringify(updatedRow);
			$.ajax({
				url : "${path}/system/resource/saveUpdatedItems.action",
				data : {
					"updatedRow" : updateItem
				},
				type : "post",
				success : function(result) {
					var txt = $.parseJSON(result);
					if (txt.status=="success") {
						alertInfo(OPRATION_SUCCESS);
						$('#dg').datagrid('reload');
					} else {
						alertWarning(OPRATION_FAILED);
					}
				}

			});
		}
		//验证提交
		function checkForm() {
			var validator = $("#form").validate();
			if (validator.checkForm() == true) {
				saveKey();
			}
			validator.showErrors();
			validator.showSuccess();
		}

		//关于前台显示的转换
		function formatRtValue(value, rowData, rowIndex) {
			if (isEnableData && isEnableData.length >= 0) {
				for ( var i = 0; i < isEnableData.length; i++) {
					if (isEnableData[i].id == value) {
						return isEnableData[i].text;
					}
				}
			}
			return rowData.isReturn;
		}
		
		//关闭子页面并刷新,这个方法代码必须要有
		function closeEasyuiWindowBoxAfterEdit() {
			alertInfo(OPRATION_SUCCESS);
			$('#easyuiWindowBox').window('close');
			$('#dg').datagrid('reload');
		}
		
		$(document).ready(function() {
			$('#dg').datagrid();
			$("#form").validate();
		});

		/**获取选中的资源节点值*/
		function getCheckedResourceItem() {
			var Items = d.getCheckedNodes();
			var ResourceItems = new Array();
			for ( var n = 0; n < Items.length; n++) {
				if (selids[n] != '1') {
					ResourceItems.push(Items[n]);
				}
			}
			return ResourceItems;
		}

		/*删除当前选中目录*/
		function deleteItem() {
			var rowDatas = $('#dg').datagrid('getSelections');
			if (rowDatas.length == 0) {
				alertWarning(OPRATION_PLEASE_CHOOSE_ONE);
				return false;
			}
			var param = [];
			//遍历选中列
			$.each(rowDatas, function(i, o) {
				if (!rowDatas.id) {
					param.push(o.id);
				}
				if (typeof o.id == "undefined") {
					$('.datagrid-row-selected', '#dg').remove();
				}
			});

			if (param.length == 0) {
				alertWarning(OPRATION_PLEASE_CHOOSE_ONE);
				return;
			}

			var ids = param.join(",");
			alertConfirm("确定更改当前选中内容的状态?", function() {
				$.ajax({
					url : "${path}/system/resource/deleteItemByIDs.action",
					data : {
						"selectedIDs" : ids
					},
					type : "post",
					success : function(result) {
						var txt = $.parseJSON(result);
						if (txt.status == "success") {
							alertInfo(OPRATION_SUCCESS);
							$('#dg').datagrid('reload');
						} else {
							alertWarning(OPRATION_FAILED);
						}
					}
				});
			});
		}
	</script>
</body>
</html>