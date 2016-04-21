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

<title>My JSP 'manger_menus.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>

<script type="text/javascript">
//datagrid初始化 
$(function(){
	$('#list_data').datagrid({
	title:'微信菜单列表',
	iconCls:'icon-edit',
	width:'100%',
	height:'100%',
	nowrap:'false',
	striped:'true',
	border:'false',
	collasible:'false',
	fit:'true',
	url:'${pageContext.request.contextPath}/menu/queryByPageSize',
	method:'POST',
	contentType:'application/json; charset=utf-8',
	dataType: 'json',
	loadMsg:'数据加载中，请稍后......', 
	//queryParams:{'startRow':0,'pageSize':10},
	//sortOrder:'desc',
	remoteSort:'false',
	idFeild:'id',
	singleSelect:'true',
	pagination:'true',
	rownumber:'true',
	showFooter: false,
	frozenColumns:[[{field:'ck',check:'true',width:'50'}]],
	toolbar:[{
		text:'添加',
		iconCls:'icon-add',
		handler:function(){
			openDialog('add-dialog','add');
		}},'-',{
		text:'编辑',
		iconCls:'icon-edit',
		handler:function(){
			openDialog('edit-dialog','edit');
		}},'-',{
		text:'删除',
		iconCls:'icon-delete',
		handler:function(){
			deleAppInfo();
		}
		}],
	});

	//设置分页控件 
	var pg = $('#list_data').datagrid('getPager');
		$(pg).pagination({
			pagePosition:'bottom',
			pageSize:10,
			pageList:[10,20,30],
			beforePageText:'第',
			afterPageText:'页',
			dispalyMsg:'共 {total} 条记录',
			onBeforeRefresh: function () {  
    		},
    		onSelectPage: function (pageNumber, pageSize) {//分页触发  
            //find(pageNumber, pageSize);
    		} 
	});
});
</script>

</head>

<body>
	<table id="list_data" class="easyui-datagrid" cellspacing="5"
		cellpadding="5" border="fasle" split="false">
		<thead>
			<tr>
				<th field="name" width="100">菜单名称</th>
				<th field="xkey" width="100">菜单KEY值</th>
				<th field="type" width="150">菜单的响应动作类型</th>
				<th field="url" width="100">菜单链接</th>
				<th field="media_id" width="100">素材接口</th>
				<th field="parent" width="100">父菜单</th>
			</tr>
		</thead>

	</table>
</body>
</html>
