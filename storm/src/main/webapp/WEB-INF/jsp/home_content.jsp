<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
   <div region="center" border="true" title ="" split="true" fit="true">
			<div id="tab" class="easyui-tabs" fit="true" border="false" split="false" style="width: 500px; height: 250px;">
				<div title="首页" data-options="closeable:true" style="padding: 20px; display: none;">
						
				</div>
					
			</div>
	</div>
  </body>
</html>
