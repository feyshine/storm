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
		$('#form').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			}
		});
		
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
	<div style="margin:20px 0;">
	<div class="easyui-panel" title="登录" align = "center" style="width:50%;">
		<div style="padding:10px 60px 20px 60px">
	    <form id="form" class="easyui-form"  data-options="novalidate:true">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>账号:</td>
	    			<td><input class="easyui-textbox" type="text" name="username" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-textbox" type="password" name="password" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="login()">登录</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="regist()">注册</a>
	    </div>
	    </div>
	</div>
	</div>
    
	
  </body>
</html>
