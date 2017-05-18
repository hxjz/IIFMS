<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>${title}</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/icon.css">
<link href="${path }/css/default/base.css" rel="stylesheet" type="text/css"/>
<link href="${path }/css/default/rightpart.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="${path }/scripts/validatebox-ruls.js"></script>
<script type="text/javascript" src="${path }/scripts/date.js"></script>
<script type="text/javascript" src="${path }/js/index.js"></script>

</head>

<body>

	<div id="w" class="easyui-window" title="${title}"
		data-options="iconCls:'icon-save'"
		style="width:500px;height:200px;padding:10px;">${msg}</div>

</body>
</html>
