<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String url = request.getParameter("url");
	request.setAttribute("url", url);
%>
<script>
// top.location.href = "http://test.cas.ziroom.com/CAS/logout?service=http://smsapi.test.ziroom.com/SMS/";
//  top.location.href = "http://cas.ziroom.com/CAS/logout?service=http://test.me:9000/SMS/";
//  top.location.href = "http://cas.ziroom.com/CAS/logout?service=http://sms2.ziroom.com/SMS/";
	top.location.href = "${path}/login.jsp";
</script>
