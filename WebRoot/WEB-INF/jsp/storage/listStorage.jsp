<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>存储位置管理</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>

<body>
	<form method="post" id="searchForm">
		<div class="t_oneblock_h">
			<h3>存储位置查询</h3>
		</div>		
		<div class="t_oneblock_c pr">
			<div class="nobortable pt10 pl10">
				<table width="100%" border="0">
					<tr>
						<td class="tr" width="100"><span class="t_span01">存储名称：</span></td>
						<td>
							<select id="storageName" style="width: 120" onchange="storageNameChange(this.value)">
								<option value="">请选择</option>
								<c:forEach items="${storageNameList}" var="object">
									<option value="${object.key}">${object.value}</option>
								</c:forEach>
							</select>
						</td>
						<td class="tr" width="100"><span class="t_span01">一级显示：</span></td>
						<td>
							<select id="storageLevel1" style="width: 120" onchange="storageLevel1Change(this.value)">
								<option value="">请选择</option>
							</select>
						</td>
						<td class="tr" width="100"><span class="t_span01">二级显示：</span></td>
						<td>
							<select id="storageLevel2" style="width: 120" onchange="storageLevel2Change(this.value)">
								<option value="">请选择</option>
							</select>
						</td>
						<td class="tr" width="100"><span class="t_span01">三级显示：</span></td>
						<td>
							<select id="storageLevel3" style="width: 120" onchange="storageLevel3Change(this.value)">
								<option value="">请选择</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<input id="parentId" name="filter_and_parentId_EQ_S" type="hidden" value="0"/>
							<input name="filter_and_isDel_EQ_I" type="hidden" value="0"/>
							<input name="order_createTime_T" type="hidden" value="desc"/>
							<input class="t_btnsty01" id="find" name="select" type="button" value="查询"/>
							<input class="t_btnsty01" type="reset" value="重置"/>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<!--nobortable -->
		</div>
	</form>
	<table id="dg1" title="存储位置详细信息" style="width:100%;height:350px" fitColumns='true'
		toolbar='#toolbar' data-options="singleSelect:true,collapsible:false,
            url:'${path }/storage/showStorage.action',
            method:'post',
            rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageNumber:1,
			pageSize:50,
			pageList:[10,30,50] ">
       <thead>
           <tr>
               <th data-options="field:'id',hidden:true" align="center">Id</th>
               <!-- <th data-options="hidden:true" align="center">操作</th> -->
               <th data-options="field:'type',width:160,formatter:formatType" align="center">存储类型</th>
               <th data-options="field:'name',width:160" align="center">显示名称</th>
               <th data-options="field:'status',width:60,formatter:formatStatus" align="center">存储状态</th>
               <th data-options="field:'device',width:150" align="center">设备信息</th>
               <th data-options="field:'comment',width:150" align="center">备注</th>
           </tr>
       </thead>
   </table>
   <!-- 按钮 -->
    
   <script type="text/javascript">
    	$(function(){
    		createTable('dg1');
    	});
    	
    	//表单提交  
	    $('#form1').form({  
	        url:'${path}/system/addStorage.action',  
	        onSubmit:function(){
	            return $(this).form('validate');
	        },
	        success:function(data){  
	        	alert("成功");
	        	$('#dicAdd').window('close');
	        	reloadgrid('dg1');  // 重新加载datagrid
	        }
	    });
    	
    	function formatType(value, rowData,rowIndex) {
    		if(rowData.type=='2') {
    			return "密集柜/区";
    		}else {
    			return "存储区";
    		}
    	}
    	
    	function formatStatus(value, rowData,rowIndex) {
    		if(rowData.status=='1') {
				return "使用中";
			}else {
				return "未使用";
			}
		}
    	
    	function storageNameChange(value) {
    		$("parentId").val=value;
    		$("storageLevel1").empty();
    		$("storageLevel2").empty();
    		$("storageLevel3").empty();
    		
    		if(''!=value) {
    			$.ajax({
					url : "${path}/storage/getLevelInfo.action",
					data : {
						'parentId' : value
					},
					dataType : "json",
					type : "post",
					success : function(result) {
						if (result.status == "success") {
							var va= result.data;
							for(var i=0; i<va.length; i++) {
								var valine = eval(va[i]);
								alert(valine.key);
							}
							reloadgrid('dg1');
						} else {
							alertInfo("请重新选择！");
						}
					}
				});
    		}
    		
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
						url : "${path}/system/delStorage.action",
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
	 	
		function toChooseStorage(){
			// 提交打印请求
			$.ajax({
				url : "${path}/storage/toChooseStorage.action",
				data : {
				},
				dataType : "json",
				type : "post",
				success : function(result) {
					if (result.status == "success") {
						var me = result.data.storage[0];
						request.setAttribute("storageList",result.data.storage);
						//alertInfo(JSON.stringify(result.data.storage));
						//data: "storage1=" + JSON.stringify(result.data);
						alertInfo(me.showName);
						//$("input[name = choose_test]").attr("value",storage1.id);
					} else {
						alertInfo(result.data);
					}
				}
			});
		}
   </script>
</body>
</html>
