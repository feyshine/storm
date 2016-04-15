<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="refresh" content="30">
    <title>注册</title>
    <script type="text/javascript">
   function regist() {  
    	var form = document.forms[0];  
        form.action = "${pageContext.request.contextPath}/user/addUser";  
        form.method = "post";  
        form.submit();  
    }
   </script>
  </head>
  
  <body>
     <form action = "<%=request.getContextPath()%>/user/addUser" method = "post">
    <table border="1"  align="center" cellspacing="0" width = "30%" height = "20%">
    	<tr>
    		<th colspan="2" >用户注册 </th>
    	</tr>
    	
        <tr>
            <td>用户名:</td>
            <td><input type = "text" name = "username" size = "20" maxlength="30"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type = "password" name = "password" size = "20" maxlength="30"></td>
        </tr>
        <tr>
            <td>年龄:</td>
            <td><input type = "text" name = "age" size = "20" maxlength="30"></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td><input type = "text" name = "sex" size = "20" maxlength="30"></td>
        </tr>
        <tr>
           <th colspan="2" align="center">
            <input type="submit" size = "20"  value="提交"/>
           </th>
        </tr>
    </table>
    
	</form>
  </body>
</html>
