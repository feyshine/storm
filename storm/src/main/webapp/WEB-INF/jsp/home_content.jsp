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
   <div region="center" border="true" title ="" split="true">
			<div id="tab" class="easyui-tabs"  border="false">
				<div title="首页" data-options="closeable:true" style="position:relative;padding: 20px; display: none;">
						
				</div>
					
			</div>
	</div>
  </body>
</html>
