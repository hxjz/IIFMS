<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	
	String userName="";
	if(null!=request.getAttribute("userName")) {
		userName = (String)request.getAttribute("userName");
	}
	
	String sectionName="";
	if(null!=request.getAttribute("sectionName")) {
		sectionName = (String)request.getAttribute("sectionName");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="${path}/css/default/base.css" rel="stylesheet" type="text/css"/>
<link href="${path}/css/default/layout.css" rel="stylesheet" type="text/css"/>
<link href="${path}/css/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${path}/css/default/icon.css" rel="stylesheet" type="text/css"/>
<link href="${path}/img/icon/favicon.ico" rel="bookmark" type="image/x-icon"/>
<link href="${path}/img/icon/favicon.ico" rel="icon" type="image/x-icon"/>
<link href="${path}/img/icon/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
<script src="${path}/jsLibs/jquery.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.adjustSize.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.controlLeftNav.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.tabsView.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.accordion.js" type="text/javascript"></script>
<script src="${path}/jsLibs/jquery.messager_push.js"></script>
<script src="${path}/js/index.js" type="text/javascript"></script>
</head>

<body>
	<div class="t_topbox">
		<div class="t_topbox_posilogo"></div>
		<!--t_topbox_posilogo -->
		<div class="t_topbox_posia"><font color="white" size="4"><%=sectionName %></font>&nbsp;<font color="white" size="2">[ <%=userName %> ]</font>&nbsp;<font color="white" size="6">欢迎您</font></div>
		<!--t_topbox_posia -->
		<div class="t_topbox_posib">
			<div class="t_topbox_b ">
				<div class="index_nav">
					<ul id="mainUl">
					</ul>
				</div>
				<!--index_nav -->
			</div>
			<!--t_topbox_b -->
		</div>
		<!--t_topbox_posib -->

	</div>
	<!-- t_topbox-->
	<div id="t_btntopbox" class="t_btntopbox"></div>
	<!--t_btntopbox -->
	<div class="t_mainbox">
		<div class="t_leftbox">
			<div class="t_lefttitle">
				<h3>功能菜单</h3>
			</div>
			<!-- -->
			<div class="leftBox">
				<div class="slideNav">
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
								onclick="loadUrl('content_case','${path}/cases/listCases.action','案件维护');" id='left_case' title="案件管理">案件管理</a>
						</dt>
					</dl>
					<dl>
						<dt> 
							<a href="javascript:void(0)" style="color: #648da2;"
						   	onclick="loadUrl('content_finance','${path}/finances/listFinances.action','财物管理');" id='left_finance' title="财物管理">财物管理</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
							   onclick="loadUrl('content_statistics','${path}/finances/toStatistics.action','财物统计');" id='left_statistics' title="财物统计">财物统计</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
								onclick="loadUrl('content_dictionary','${path}/system/tolistDictionary.action','数据字典');" id='left_dictionary' title="数据字典">数据字典</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
								onclick="loadUrl('content_stock','${path}/stock/listStock.action','出入库管理');" id='left_stock' title="出入库管理">出入库管理</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
								onclick="loadUrl('content_operateLogs','${path}/stock/listOperateLogs.action','财物操作记录查询');" id='left_operateLogs' title="财物操作记录查询">财物操作记录查询</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
								onclick="loadUrl('content_storage','${path}/storage/tolistStorage.action','存储位置管理');" id='left_storage' title="存储位置管理">存储位置管理</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
							   onclick="loadUrl('content_monitor','${path}/monitor/listMonitor.action','移动监控管理');" id='left_monitor' title="移动监控管理">移动监控管理</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
							   onclick="loadUrl('content_inventory','${path}/inventory/listInventory.action','盘库');" id='left_inventory' title="盘库">盘库</a>
						</dt>
					</dl>
				</div>
			</div>
		</div>
		<!-- t_leftbox-->
		<div id="t_btnleftbox" class="t_btnleftbox"></div>
		<!--t_btnleftbox -->
		<div class="t_rightbox clearfix">
			<div class="t_rightboxin">
				<div id="tt" class="easyui-tabs t_righttabContant" data-options="tools:'#tab-tools'">
					<c:if test="${requestScope.isProvider}">
						<div style="height: 100%; border: 0px solid #f00;"
							class="iif_frame_tab_content" id="main_tab" title="工作台">
							<iframe src="${path}/stage/homeStage!providerIndex.action"
								width="100%" height="100%" frameborder="0" scrolling="auto">
							</iframe>
						</div>
					</c:if>
				</div>
			</div>
			<!-- t_rightboxin-->
		</div>
		<!-- t_rightbox-->
	</div>
</body>
</html>

