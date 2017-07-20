<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String error = "";
	if(null!=request.getParameter("error")) {
		error = (String)request.getParameter("error");
	}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>涉案财物管理系统</title>
		<meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="-1">
		<meta charset="utf-8">
		<link href="${path}/styles/index_style/styleBase.css" rel="stylesheet" type="text/css" />
		<link href="${path}/img/icon/favicon.ico" rel="bookmark" type="image/x-icon"/>
		<link href="${path}/img/icon/favicon.ico" rel="icon" type="image/x-icon"/>
		<link href="${path}/img/icon/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
		<script src="${path}/jsLibs/jquery.js" type="text/javascript"></script>
		<script src="${path}/jsLibs/jquery.easyui.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path}/scripts/baseJs.js"></script>
	
	</head>
	<body ><!--ad 认证请求 j_security_check -->
		<img src="${path}/images/index_img/login.jpg" style=" position: absolute;width: 100%;height: 100%;left: 0px;top: 0px;z-index: -999;" />
		<form method='post' action='${path}/security/login.action' id='_form'>
		<div class="box">
		  <div class="logo"></div>
		  <div class="login">
		  	<label style="color:red;font-size:18px;text-align:right" id="error"><%=error %></label>
		    <div class="loginbg"></div>
		    <div class="logintext">
		      <h1>涉案财物管理系统</h1>
		      <div class="inputli">
		        <label class="dlzh" id="dlzh1">请输入用户名</label>
		        <input name="j_username" value="" type="text" class="input01" id="j_username" onfocus="toHide1()" onblur="blur1()" onkeydown="enterSubmit(this.form)" autocomplete="off"/>
		      </div>
		      <div class="inputli">
		        <label class="dlzh" id="dlzh2">请输入密码</label>
		        <input name="j_password" value=""  type="password" class="input01" id="j_password" onfocus="toHide2()" onblur="blur2()" onkeydown="enterSubmit(this.form)" />
		      </div>
		      <div class="inputli03">
		        <input name="input" type="button" value="" class="input03"  onclick="submitForm(this.form)"/>
		        <div class="cB cF"></div>
		      </div>
		  </div>
		</div>
		</div>
	    </form>
	    
		<script type="text/javascript">
			function submitForm() {
				var j_username = document.getElementById("j_username");
				var j_password = document.getElementById("j_password");
				if (j_username.value == '') {
					alert('用户名不能为空！');
					j_username.focus();
					return false;
				}
				if (j_password.value == '') {
					j_password.focus();
					alert('密码不能为空！');
					return false;
				}
				document.getElementById("_form").submit();	
			}
			
			function enterSubmit(_form){
				if(event.keyCode==13){ 
			      return submitForm(_form);
			    } 
			}
		</script>
	
	</body>
	
</html>

