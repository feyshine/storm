<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'findpwd.jsp' starting page</title>
    
	<link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">
  	
  	<script src="<c:url value="/resources/jquery.min.js"/>"></script>
  	<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>

  </head>

<body style="height:100%;width:100%;overflow:hidden;border:none;">
	<div class="easyui-window" title="Layout Window" icon="icon-help"
		style="width:100%;height:100%;padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="west" split="true" style="width:120px;">
				<ul class="easyui-tree">
					<li><span>信息管理</span>
						<ul>
							<li><span>客户信息</span>
								<ul>
									<li><span><a href="http://www.google.com"
											onclick="addTab('google','http://www.google.com')">查询</a></span></li>
									<li><span><a href="http://www.google.com"
											onclick="addTab('jquery','http://jquery.com/')">新增</a></span></li>
									<li><span><a href="http://www.google.com"
											onclick="addTab('easyui','http://jeasyui.com/')">编辑</a></span></li>
								</ul></li>
							<li><span>数据分析</span></li>
						</ul></li>
					<li><span>系统管理</span></li>
				</ul>
			</div>
			<div region="center" border="false" border="false">
				<div class="easyui-tabs" fit="true">
					<div title="Home" style="padding:10px;">
						<div id="tb" style="padding:5px;height:auto">
							<div style="margin-bottom:5px">
								<a href="#" class="easyui-linkbutton" iconCls="icon-add"
									plain="true"></a> <a href="#" class="easyui-linkbutton"
									iconCls="icon-remove" plain="true"></a> <a href="#"
									class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
								<a href="#" class="easyui-linkbutton" iconCls="icon-save"
									plain="true"></a> <a href="#" class="easyui-linkbutton"
									iconCls="icon-cut" plain="true"></a>
							</div>
							<div>
								日期: <input class="easyui-datebox" style="width:100px">
								To: <input class="easyui-datebox" style="width:100px">
								手机号: <input class="easyui-combobox" style="width:100px" url=""
									valueField="id" textField="text"> <a href="#"
									class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</div>
						</div>


						<table id="tt" class="easyui-datagrid"
							style="width:100%;height:100%;" url="" title="查询"
							iconCls="icon-search" toolbar="#tb" rownumbers="true"
							pagination="true" singleSelect="true" idField="itemid"
							fitColumns="true">
							<thead>
								<tr>
									<th field="ck" checkbox="true" width="80" align="center"></th>
									<th field="id" width="80" align="center">ID</th>
									<th field="username" width="80" align="center">姓名</th>
									<th field="password" width="80" align="center">密码</th>
									<th field="sex" width="80" align="center">性别</th>
									<th field="age" width="80" align="center">年龄</th>
									<th field="action" width="80" align="center"
										formatter="formatAction">操作</th>
								</tr>
							</thead>
						</table>
					</div>
					<div title="Contacts">No contact data.</div>
				</div>
			</div>
			<div region="south" border="false"
				style="text-align:right;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)">Ok</a> <a class="easyui-linkbutton"
					icon="icon-cancel" href="javascript:void(0)">Cancel</a>
			</div>
		</div>
	</div>

</body>
</html>
