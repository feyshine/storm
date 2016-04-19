<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 	<link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">
  	
  	<script src="<c:url value="/resources/jquery.min.js"/>"></script>
  	<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>
	<meta http-equiv="refresh" content="30">
	<base href="<%=basePath%>">
	<title>登录</title>
<script type="text/javascript">  
    
    function login(){
		var form = document.forms[0];  
        form.action = "${pageContext.request.contextPath}/user/login";  
        form.method = "post";  
        form.submit();
	}
    

    
	function regist(){
		var form = document.forms[0];  
        form.action = "${pageContext.request.contextPath}/user/regist";  
        form.method = "post";  
        form.submit();
	}
	
	
 
 
</script>


</head>
<body >
	<div align="center">
	<div class="easyui-panel" title="登录"  style="width:40%;">
		<div style="padding:20px 20px 20px 20px">
	    <form id="form" class="easyui-form" >
	    	<table cellpadding="5" align = "center">
	    		<tr>
	    			<td>账号:</td>
	    			<td><input id = "username" class="easyui-textbox" validType="text" name="username" data-options="required:true" missingMessage="不能为空" invalidMessage="无效账户" tipPosition="right"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input id = "password" class="easyui-textbox" validType="password" name="password" data-options="required:true" missingMessage="不能为空" invalidMessage="无效密码" tipPosition="right"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<input type = "submit" name="登录" value="登录" onclick="login()">
	    	<input type= "button" name="注册" value="注册" onclick="regist()">
	    </div>
	    </div>
	</div>
	</div>
	
  </body>
</html>
