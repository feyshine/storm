<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div >
     	共${userNum}条记录
     	<c:if test="${currentPage == 1}">
     		<span class="disabled"><< 前一页</span>
     	</c:if>
     	<c:if test="${currentPage != 1}">
     		<a href="<%=request.getContextPath()%>/user/listUser?page=${currentPage-1}"><< 前一页</a>
     	</c:if>
     	 <%
     	 int pageTimes = (Integer)session.getAttribute("pageTimes");
     	for(int i= 0;i<3;i++){
     	 %>
        <c:if test="${currentPage == page}">
         	<span class="current"><%=i+1%></span>
         </c:if>
         <c:if test="${currentPage != page}">
         	<a href="<%=request.getContextPath()%>/user/listUser?page=<%=i+1%>"><%=i+1%></a>
         </c:if>
         <% 
     	}
     	 %>
     	 <c:if test="${currentPage == pageTimes}">
         	<span class="disabled">后一页 >></span>
         </c:if>
         <c:if test="${currentPage != pageTimes}">
         	<a href="<%=request.getContextPath()%>/user/listUser?page=${currentPage+1}">后一页 >></a>
         </c:if> 
    </div>
  </body>
</html>
