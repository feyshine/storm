<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>

<base href="<%=basePath%>">

<title>系统管理主页</title>
</head>

<body style="height:100%;width:100%;overflow:hidden;border:none;">
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north',border:false" style="height:10%;background:#B3DFDA; padding:25px;">
			<div agin="center">
				<h2>微信公众号后台管理系统</h2>
			</div>
		</div>
	    <jsp:include page="home_menu.jsp"></jsp:include>
		<jsp:include page="home_content.jsp"></jsp:include>
	</div>
</body>




</html>
