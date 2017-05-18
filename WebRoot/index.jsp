<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<script>
self.moveTo(0, 0);
self.resizeTo(screen.availWidth, screen.availHeight);
window.location.href = "${path}/security/index.action";
</script>