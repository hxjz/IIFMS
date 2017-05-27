<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
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
		<div class="t_topbox_posia"></div>
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
				<!-- <div class="set">设置</div> -->
			</div>
			<!-- -->
			<div class="leftBox">
				<div class="slideNav">
					<c:forEach items="${userRoleList}" var="obj2">
						<c:if test="${obj2.resource.isButton != 1 }">
							<dl>
								<c:choose>
									<c:when test="${obj2.resource.url==null}">
										<dt>${obj2.resource.name}</dt>
										<c:if test="${obj2.children.size()>0}">
											<dd>
												<c:forEach items="${obj2.children}" var="obj3">
													<c:if
														test="${obj3.resource.isButton!=1 && obj3.resource.isEnable==1}">
														<dl class="sec">
															<c:choose>
																<c:when test="${obj3.resource.url==null }">
																	<dt>${obj3.resource.name}</dt>
																	<c:if test="${bj3.children.size()>0}">
																		<dd>
																			<c:forEach items="${obj3.children}" var="obj4">
																				<c:if
																					test="${obj4.resource.isButton!=1 && obj4.resource.isEnable==1}">
																					<p style='white-space: nowrap;'>
																						<a href="javascript:void(0)"
																							onclick="loadUrl('content_${obj4.resource.id}','${path}/${obj4.resource.url }','${obj4.resource.name }');"
																							id='left_${obj4.resource.id}'
																							title="${obj4.resource.name }">${obj4.resource.name }</a>
																					</p>
																				</c:if>
																			</c:forEach>
																		</dd>
																	</c:if>
																</c:when>
																<c:otherwise>
																	<dt>
																		<a href="javascript:void(0)"
																			onclick="loadUrl('content_${obj3.resource.id}','${path}/${obj3.resource.url }','${obj3.resource.name }');"
																			id='left_${obj3.resource.id}'
																			title="${obj3.resource.name }">${obj3.resource.name }</a>
																	</dt>
																</c:otherwise>
															</c:choose>
														</dl>
													</c:if>
												</c:forEach>
											</dd>
										</c:if>
									</c:when>
									<c:otherwise>
										<dt>
											<a href="javascript:void(0)" style="color: #648da2;"
												onclick="loadUrl('content_${obj2.resource.id}','${path}/${obj2.resource.url }','${obj2.resource.name }');"
												id='left_${obj2.resource.id}' title="${obj2.resource.name }">${obj2.resource.name }</a>
										</dt>
									</c:otherwise>
								</c:choose>
							</dl>
						</c:if>
					</c:forEach>
					
					<!-- Temp -->
					<dl>
						<dt>
							<a href="javascript:void(0)" style="color: #648da2;"
								onclick="loadUrl('content_case','${path}/cases/listCases.action','案件维护');" id='left_case' title="案件维护">案件管理</a>
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
					<!-- Temp -->
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
	<!-- t_mainbox-->
	<div id="mm" class="easyui-menu" data-options="">
		<div data-options="name:'closeThis'" onclick="closeThis()">关闭当前标签</div>
		<div class="menu-sep"></div>
		<div data-options="name:'closeOthers'" onclick="closeOthers()">关闭其他标签</div>
		<div data-options="name:'closeAll'" onclick="closeAll()">关闭全部标签</div>
	</div>
</body>
</html>
