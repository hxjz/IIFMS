<%@ page language="java" contentType="text/html; charset=UTF-8"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String picPath = path;
	request.setAttribute("path", path);
	request.setAttribute("picPath", picPath);
%>
<script language="javascript">  
var path = "${path}";
var uploadPicUrl ="${uploadPicUrl}";
</script>
<script src="${picPath}/jsLibs/jquery.js" type="text/javascript"></script>
<script src="${picPath}/jsLibs/jquery.fixdatagridsize.js"
	type="text/javascript"></script>
<script type="text/javascript" language="javascript"
	src="${picPath}/js/common.js">
	
</script>

<script type="text/javascript" language="javascript"
	src="${picPath}/js/CheckForm.js">
</script>
<script type="text/javascript" language="javascript"
	src="${picPath}/css/facebox/facebox.js">
</script>

<script src="${picPath}/jsLibs/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="${picPath}/jsLibs/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="${picPath}/jsLibs/datagrid-detailview.js"
	type="text/javascript"></script>
<script src="${picPath}/jsLibs/WdatePicker.js" type="text/javascript"></script>
<script src="${picPath}/jsLibs/json2.js" type="text/javascript"></script>
<script src="${picPath}/jsLibs/date.js" type="text/javascript"></script>
<script src="${picPath}/js/dialog.js" type="text/javascript"></script>
<script src="${picPath}/js/pageLoad.js" type="text/javascript"></script>
<script type="text/javascript">
	contextPath = '${path}';
	if (top.location.href.indexOf("${path}/security/index.action") < 0
			&& top.location.href.indexOf(".action") >= 0) {
		top.location.href = "${path}/security/index.action";
	}
</script>
<script src="${picPath}/jsscript/common.js" type="text/javascript"></script>
<title>自如系统</title>