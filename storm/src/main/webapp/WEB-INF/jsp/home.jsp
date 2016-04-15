<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统管理主页</title>
  
  </head>
  
  <body>
    <form action = "<%=request.getContextPath()%>/user/listUser" method = "get">
    <input type = "submit" name = "list" value = "列表" >
    </form>
    
  
  </body>
</html>
