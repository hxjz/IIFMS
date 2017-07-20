<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>异常财物列表页</title>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>

<body>
	<div class="t_rightcontainer">
		<div class="t_oneblock">
			<div class="t_oneblock_h">
				<h3>异常财物信息</h3>
			</div>
			<!-- t_oneblock_h-->
			<form id="upload" name="uploadForm" enctype="multipart/form-data" method="post">
				<div class="t_oneblock_c pr">
					<div class="nobortable pt10 pl10">
						<table width="100%" border="0">
							<tr>
								<td><input class="t_btnsty02" name="" type="button" value="导出" onclick="exportList2Excel();" /></td>
								<td>
									<input class="t_file01" name="uploadFile" type="file" id="uploadFile" value="${uploadFile}"/>
									<input class="t_btnsty04" id="upload" name="upload" type="submit" value="上传文件"/>
								</td>
								<td><span><input class="t_btnsty02" name="" type="button" value="详细信息" onclick="toDetailPage();" /></span></td>
							</tr>
						</table>
					</div>
					<div class="nobortable pt10 pl10">
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
				<h3>异常财物列表</h3>
			</div>
			<!-- t_oneblock_h-->
			<div class="t_oneblock_c pr">
					<table id="dg1" style="width:100%; height:350px" data-options="singleSelect:true,collapsible:false,
			            url:'${path}/inventory/showInventory.action',
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
								<th data-options="field:'financeType',width:150" align=" center">财物类型</th>
								<th data-options="field:'financeState',width:100,formatter:formatFinanceState" align="center">财物状态</th>
								<th data-options="field:'row.cases.caseNum',width:100,formatter:formatCaseNum" align="center">案件编号</th>
								<th data-options="field:'row.cases.caseName',width:80,formatter:formatCaseName" align="center">案件名称</th>
								<th data-options="field:'row.cases.caseType',width:100,formatter:formatCaseType" align="center">案件类型</th>
								<th data-options="field:'row.cases.caseTime',width:100,formatter:formatCaseTime" align="center">案发时间(起-止)</th>
								<th data-options="field:'row.cases.siteNum',width:80,formatter:formatSiteNum" align="center">现场勘验号</th>
								<th data-options="field:'row.cases.inspectionTime',width:60,formatter:formatInspectionTime" align="center">勘验时间(起-止)</th>
								<th data-options="field:'storeLocation',width:120" align="center">存放位置</th>
							</tr>
						</thead>
					</table>
			</div>
			<!-- t_oneblock_c-->
		</div>
		<!--t_oneblock -->
	</div>
	<!--t_rightcontainer -->
	<!-- 详情弹窗 -->
	<div id="detailInfo" class="easyui-window" title="财物信息详情" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 1000px; height: 500px; padding: 20px;">
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

		//表单提交
		$('#upload').form({
			url:'${path}/inventory/doInventory.action',
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
				alertInfo(result.status);
				reloadgrid('dg1');
			}
		});

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

		// 导出
		function exportList2Excel() {
			$.ajax({
				url : "${path}/inventory/doExportList.action",
				dataType : "json",
				type : "post",
				success : function(result) {
					if (result.status != "success") {
						alertInfo(result.data);
					}
				}
			});
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


        // 财物状态
        function formatFinanceState(value,row,index) {
            if(""!=row.financeState && "2"==row.financeState) {
                return "出库";
            }else if(""!=row.financeState && "1"==row.financeState){
                return "在库";
            }else {
                return "登记";
            }
        }

        // 案件名称
        function formatCaseName(value,row,index){
            if(row.cases.id){
                return row.cases.caseName;
            }
        }

       // 案件编号
        function formatCaseNum(value,row,index){
            if(row.cases.id){
                return row.cases.caseNum;
            }
        }

        //案件类型
        function formatCaseType(value,row,index){
            if(row.cases.id){
                return row.cases.caseType;
            }
        }

        //案发时间(起-止)
//        function formatCaseTime(value,row,index){
//            if(row.cases.id){
//                return row.cases.caseTimeStart +' - '+ row.cases.caseTimeEnd;
//            }
//        }

        //现场勘验号
        function formatSiteNum(value,row,index){
            if(row.cases.id){
                return row.cases.siteNum;
            }
        }
        //勘验时间(起-止)
//        function formatInspectionTime(value,row,index){
//            if(row.cases.id){
//                return row.cases.inspectionTimeStart +' - '+  row.cases.inspectionTimeEnd;
//            }
//        }
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

	</script>
</body>
</html>