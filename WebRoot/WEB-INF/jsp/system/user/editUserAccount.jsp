<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户授权</title>
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/css/default/base.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/css/default/rightpart.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/jsLibs/dtree/dtree.css"></link>

<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script type="text/javascript" src="${path }/scripts/dialog.js"></script>
<script type="text/javascript" src="${path }/jsLibs/jquery.jstree.js"></script>
<script type="text/javascript">
/*
 * ajax提交Check菜单
 */
function ajaxAddRole(roleId){
	
	//封装参数
	var userId = $('#userId').val();

	//请求后台处理
	$.ajax({
		url:"${path}/orgMgt/userAccount/addRole.action",
		data:{"roleId":roleId,"userId":userId},
		dataType:"json",
		type:"post",
		success:function(result) {
			if(SystemConfigure.SUCCESS===result.status) {
				//正常不处理
			}else {
				Dialog.alert(result.message);
			}
		}
	});
};

/*
 * ajax提交Uncheck菜单
 */
function ajaxDelRole(roleId){
	
	var userId = $('#userId').val();
	$.ajax({
		url:"${path}/orgMgt/userAccount/delRole.action",
		data:{"roleId":roleId,"userId":userId},
		dataType:"json",
		type:"post",
		success:function(result) {
			if(SystemConfigure.SUCCESS===result.status) {
				//正常不处理
			}else {
				Dialog.alert(result.message);
			}
		}
	});
};

$(function () {
	//定义jstree主题路径
	$.jstree._themes = "${path}/css/jtree/themes/";
	//初始化jsTree
	var tree = $("#menu_edit").jstree({
		"themes" : {
			"theme" : "apple",
			"dots" : true,
			"icons" : false
		},
		"json_data" : {
			//ajax数据加载定义
			"async" : false,
			"ajax" : {
				"url" : "${path}/orgMgt/userAccount/loadRoles.action?userId="+$('#userId').val()+"&nocache="+(new Date()).getTime()
			}
		},
		checkbox : {
			two_state : true,
			real_checkboxes : true
		},
		"plugins" : [ "themes", "json_data","checkbox", "ui" ]
	}).bind("before.jstree", function (e, data) {
		 if(data.func === "check_node") {
			//判断上级是否被保存了
			var $parent = $(event.srcElement ? event.srcElement : event.target).parents('li:first').parents('li:first');
			if('undefined' === typeof ($parent.attr('id'))) {
				return;
			} else {
				if($("#menu_edit").jstree('is_checked',$parent)) {
					return;
				} else {
					Dialog.warn(Message.author.PARENT_NODE_NOT_CHECK);
					return false;
				}
			}
		 }
    }).bind("check_node.jstree", function (e, data) {
		var $node = $(event.srcElement ? event.srcElement : event.target).parents('li:first');
		ajaxAddRole($node.attr('id'));
    }).bind("uncheck_node.jstree", function (e, data) {
    	var $node = $(event.srcElement ? event.srcElement : event.target).parents('li:first');
		ajaxDelRole($node.attr('id'));
    })
});

</script>
</head>
<body>
<input type='hidden' id='userId' value="${userId}"></input>
<div class="t_rightcen">
	<!-- 页面内容开始 -->
	<div class="t_rightblock">
		<div class="t_rightblock_c" style='height:400px'>
			<div id="menu_edit" class="demo"></div>
		</div><!--t_rightblock_c -->
	</div><!--t_rightblock -->
<!-- 页面内容结束 -->
</div>
</body>
</HTML>