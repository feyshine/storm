<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">

	<script src="<c:url value="/resources/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>
	
	<title>微信客服中心</title>
	
	<script type="text/javascript">
	function add() {
    	var form = document.forms[0];  
        form.action = "${pageContext.request.contextPath}/kfservice/add";  
        form.method = "post";  
        form.submit();
	}
	</script>
	
</head>
<body>

<div align="center">
		<div class="easyui-panel" title="添加客服" style="width:40%;">
			<div style="padding:20px 20px 20px 20px">
				<form id="form" class="easyui-form">
					<table cellpadding="5" align="center">
						<tr>
							<td>账号:</td>
							<td><input id="kfaccount" class="easyui-textbox"
								validType="text" name="kfaccount" data-options="required:true"
								missingMessage="不能为空" invalidMessage="无效账户" tipPosition="right"></input></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input id="password" class="easyui-textbox"
								validType="password" name="password"
								data-options="required:true" missingMessage="不能为空"
								invalidMessage="无效密码" tipPosition="right"></input></td>
						</tr>
						<tr>
							<td>昵称:</td>
							<td><input id="nickname" name= "nickname" class="easyui-textbox" validType="age"
								name="age" data-options="required:true" missingMessage="不能为空"
								invalidMessage="无效密码" tipPosition="right"></input></td>
						</tr>
						<tr>
							<td>头像:</td>
							<td><input class="easyui-filebox" name="file" data-options="prompt:'请选择头像'" style="width:100%"></td>
						</tr>
					</table>
				</form>
				<div style="text-align:center;padding:5px">
					<input type="submit" name="提交" value="提交" onclick="add()">
				</div>
			</div>
		</div>
	</div>

</body>
</html>
