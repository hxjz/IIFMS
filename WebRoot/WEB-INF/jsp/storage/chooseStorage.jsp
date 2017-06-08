<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>选择存储</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
	<form id="chooseStorage" name="chooseStorageForm" method="post">
	
		<input type="submit" class="t_btnsty02" id="saveBtn" value="入库">
		<input type="button" class="t_btnsty02" id="cancel" value="取消">
		<input type="hidden" name="storageList" id="storageList" value="${storageList}">
		<br>
		<br>
		
		<div>
			<c:forEach items="${storageList}" var="menu" varStatus="status">
			    <!-- 可展开的一级菜单，没有parentId,有url -->
			    <c:if test="${empty menu.parentId and not empty menu.isAvail}">
			        <li>
			            <a href="#">
			                <i class="${menu.showImg } fa-fw"></i> ${menu.showName }<span class="fa arrow"></span>
			            </a>
			            <ul class="nav nav-second-level">
			                <!-- 没有url的是三级菜单，有url的直接输出到li中 -->
			                <c:forEach items="${menu.child}" var="secondChild" varStatus="status">
			                    <c:if test="${not empty secondChild.showName }">
			                        <li>
			                              <a href="<c:url value='${secondChild.showImg }'/>">${secondChild.showName }</a>
			                        </li>
			                    </c:if>
			                </c:forEach>
			            </ul>
			        </li>
			    </c:if>
			</c:forEach>
		</div>
	</form>
	
<script type="text/javascript">
	$(document).ready(function(){
	   //Temp Start
	   var caseTypev = $("#caseTypeHid").val();
	   $("#caseType").val(caseTypev);
	   //Temp End

	   // 初始化第二个隐藏时间控件的值
	    $("input[textboxname='caseTimeStart']:eq(1)").attr("value","");
		$("#test2").hide();
	   	$("#changeTimeStart1").click(function(){
	   		$("input[textboxname='caseTimeStart']").attr("value","");
	   		$("input[name='caseTimeStart']").attr("value","");
	  		$("#test1").hide();
	  		$("#test2").show();
	   	});
	   	$("#changeTimeStart2").click(function(){
	   		$("input[textboxname='caseTimeStart']").attr("value","");
	   		$("input[name='caseTimeStart']").attr("value","");
	  		$("#test2").hide();
	  		$("#test1").show();
	   	});
	});

	//表单提交
	$('#instock').form({
		url:'${path}/stock/saveStock.action',
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(returnData){
			data = JSON.parse(returnData); // 转换成json对象
			if(data.status == "success"){
				parent.alertInfo(data.data);
				parent.afterCloseInstock();
			} else if(data.status="fail"){
				alertInfo(data.data);
			} else {
				alertInfo("未知错误");
			}
		}
	});
	function setVal() {
		if(true==$("#isMurder").is(':checked')) {
			$("#isMurder").attr("value","1");
		}else {
			$("#isMurder").attr("value","0");
		}
	}
</script>
</body>
</html>