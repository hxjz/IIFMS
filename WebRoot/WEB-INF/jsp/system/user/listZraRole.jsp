<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
		String path = request.getContextPath();
		request.setAttribute("path", path);
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/icon.css">
<link href="${path }/css/default/base.css" rel="stylesheet" type="text/css"/>
<link href="${path }/css/default/rightpart.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/plugins/jquery.validatebox.js"></script>
<script src="${path }/jsLibs/jquery.fixdatagridsize.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/scripts/validatebox-ruls.js"></script>
<script src="${path }/jsLibs/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script type="text/javascript" src="${path }/scripts/date.js"></script>
<script type="text/javascript" src="${path }/scripts/dialog.js"></script>
<script type="text/javascript" >
	if(parent.getCurrentTabId) {
		tabId = parent.getCurrentTabId();
	}
	if(parent.getfrmTabId){
		frmTabId = parent.getfrmTabId();
	}
</script>
</head>
<body>
<div class="t_rightcontainer">
	<div class="t_seartablebox">
        <div class="t_seartablebox_c">
        	<form>
            <div class="t_searbox">
                <div class="nobortable pd10">
                    <table width="500" border="0">
                        <tr>
                            <td width="100" class="tr"><span class="t_span01">角色名称：</span></td>
                            <td width="150"><input class="t_text w100" name="filter_and_name_LIKE_S" type="text" /></td>
                        </tr>
                    </table>
                </div><!--nobortable -->
                <div class="t_btnbox01">
                    <span><input class="t_btnsty01" id="find" type="button" value="查询" /></span>
                    <span><input class="t_btnsty01" name="" type="reset" value="重置"/></span>
                </div>
            </div><!--t_searbox -->
            <div class="pl20 pt10 pb10">
                <span><input class="t_btnsty01" name="" type="button" value="新增" onclick="addNewRole()"/></span>
           	</div>
            </form>
            <div class="hastable hgt_sty01">
					<table id="dg"  data-options="
						rownumbers:true,
                        fit:true,
						autoRowHeight:true,
						pagination:true,
						pageNumber:1,
						nowrap:false,
						pageSize:50,
						url:'${path}/orgMgt/role/showAll.action',
						pageList:[10,30,50]">
						<thead>
							<tr> 
								<th field="handle" width="80" align="center"  data-options="formatter:formatHandle">操作</th>
								<th field="name" width="110" align="center" >名称</th>
								<th field="valid" width="110" align="center" data-options="formatter:formatStatus">是否启用</th>
								<th field="modifyDate" width="150" align="center" >修改时间</th>
								<th field="desc" width="500" align="left">备注</th>
							</tr>
						</thead>
					</table>
				</div>
        </div><!--t_seartablebox_c -->
    </div><!--t_seartablebox -->
    <div id="easyuiWindowBox" class="easyui-window"
		data-options="modal:true,closed:true,minimizable:false,maximizable:false,collapsible:false"
		style="padding: 10px; overflow: hidden;">
		<iframe id="frame_content" src="" scrolling="no" frameborder="0" width="900" height="300"></iframe>
	</div>
</div><!--t_rightcontainer -->
<script type="text/javascript">
$(document).ready(function() {
	createTable('dg');
});

//关闭子页面并刷新,这个方法代码必须要有
function closeEasyuiWindowBoxAfterEdit() {
	alertInfo(OPRATION_SUCCESS);
	$('#easyuiWindowBox').window('close');
	$('#dg').datagrid('reload');
}

$(function() {
	 var datagridId = 'dg';
	 $('#' + datagridId).resizeDataGrid(76, 12, 0, 0);
	 $(window).resize(function() {
	  $('#' + datagridId).resizeDataGrid(76, 12, 0, 0);
	 });
});

function formatHandle(value,rowData,rowIndex) {
	var str =  '<a href="javascript:void(0)"   onclick="addNewAuthor(\''+rowData.id+'\')">授权</a>&nbsp;'
	if("1" == rowData.valid) {
		str += '<a href="javascript:void(0)"   onclick="ajaxChangeState(\''+rowData.id+'\')">启用</a>';
	} else {
		str += '<a href="javascript:void(0)"   onclick="ajaxChangeState(\''+rowData.id+'\')">禁用</a>';
	}
	return str;
};

function formatStatus(value,rowData,rowIndex) {
	if("1" == rowData.valid) {
		return "否";
	} else {
		return "是";
	}
};

//初始化新增加
function addNewRole() {
	$("#frame_content").attr('width', '800');
	$("#frame_content").attr('height', '200');
	$("#frame_content").attr("src", "${path}/orgMgt/role/toAddRole.action");
	$('#easyuiWindowBox').window({
		width : 600,
		title : "添加新角色"
	});
	$('#easyuiWindowBox').window('open');
	adjustTanboxCenter();
}

//初始化授权
function addNewAuthor(id) {
	$("#frame_content").attr('width', '800');
	$("#frame_content").attr('height', '400');
	$("#frame_content").attr("src", "${path}/orgMgt/role/toEditRole.action?roleId="+id);
	$('#easyuiWindowBox').window({
		width : 600,
		title : "角色授权"
	});
	$('#easyuiWindowBox').window('open');
	adjustTanboxCenter();
}

//修改状态
function ajaxChangeState(id) {
	$.ajax({
		url:"${path}/orgMgt/role/changeState.action",
		data:{"roleId":id},
		type:"post",
		success:function(result){
			var txt=$.parseJSON(result);
			if (txt.status == "success") {
				 alertInfo(OPRATION_SUCCESS);
				 $('#dg').datagrid('reload');
			} else {
				alertWarning(OPRATION_DELETE_FAILED);
			}
		}
	});
}
</script>
</body>
</html>

