<%@ page import="com.iif.common.util.SysConstant"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/scripts/jquery-easyui-1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path }/css/default/base.css"/>
<link rel="stylesheet" type="text/css" href="${path }/css/default/rightpart.css"/>
<script type="text/javascript" src="${path }/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path }/scripts/default_grid.js"></script>
<script type="text/javascript" src="${path }/scripts/dialog.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery-easyui-1.4/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="${path }/scripts/validatebox-ruls.js"></script>
<script type="text/javascript" src="${path }/scripts/date.js"></script>
<script type="text/javascript" src="${path }/scripts/myJs.js"></script>
<script type="text/javascript" src="${path }/scripts/jquery.PrintArea.js"></script>
<script type="text/javascript" src="${path }/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path }/scripts/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${path }/js/index.js"></script>
<script type="text/javascript" >
if(parent.getCurrentTabId) {
	tabId = parent.getCurrentTabId();
}
if(parent.getfrmTabId){
	frmTabId = parent.getfrmTabId();
}
</script>