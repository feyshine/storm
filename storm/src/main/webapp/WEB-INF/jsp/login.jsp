<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<link rel="stylesheet" href="../static/css/login.css" type="text/css">
<meta http-equiv="refresh" content="30">
<base href="<%=basePath%>">
<title>登录</title>
<script type="text/javascript">  
    //登录 
    function login() {
    	var username=document.getElementById("username").value; 
    	if(username == null||username==""){
    		alert("账户不能为空");
    		return;
    	}
    	var username=document.getElementById("username").value; 
    	if(username == null||username==""){
    		alert("账户不能为空");
    		return;
    	}
    	var form = document.forms[0];  
        form.action = "${pageContext.request.contextPath}/user/login";  
        form.method = "post";  
        form.submit();  
    }
    
    function toRegist(){
    	var form = document.forms[0];  
        form.action = "${pageContext.request.contextPath}/user/regist";  
        form.method = "post";  
        form.submit();
    }
 
 
</script>


</head>
<body >
	
    <form name = "loginform"  action = "<%=request.getContextPath()%>/user/login" method = "post">
    <table border="1"  align="center" cellspacing="0" >
    	<tr>
    		<th colspan="2" >用户登录 </th>
    	</tr>
    	
        <tr>
            <td>用户名:</td>
            <td><input id = "username" type = "text" name = "username" size = "20" maxlength="30" onblur="validateUsername()"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input id = "password" type = "password" name = "password" size = "20" maxlength="30" onblur="validatePassword()"></td>
        </tr>
        
        <tr>
        	<th colspan="2" align="center" >
        		<input type="submit" size = "20"  value="登录" onclick="login()"/>
        		<input type="button" size = "20"  value="注册"  onclick="toRegist()"/>
        	</th>
        </tr>
    </table>
	</form>
	
  </body>
</html>
