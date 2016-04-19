<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>

<base href="<%=basePath%>">

<title>系统管理主页</title>
<script type="text/javascript">
	function addTab(title, url) {
		if ($('#content').tabs('exists', title)) {
			$('#content').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			$('#content').tabs('add', {
				title : title,
				content : content,
				closable : true
			});
		}
	}

	

	function doSearch() {
		$('#search').datagrid('load', {
			username : $('#username').val()
		});
	}

	function formatAction(value, row, index) {
		if (row.editing) {
			var s = '<a href="#" onclick="saverow(this)">保存</a> ';
			var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
			return s + c;
		} else {
			var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
			var d = '<a href="#" onclick="deleterow(this)">删除</a>';
			return e + d;
		}
	}

	$.extend($.fn.datagrid.defaults.editors, {
		numberspinner : {
			init : function(container, options) {
				var input = $('<input type="text">').appendTo(container);
				return input.numberspinner(options);
			},
			destroy : function(target) {
				$(target).numberspinner('destroy');
			},
			getValue : function(target) {
				return $(target).numberspinner('getValue');
			},
			setValue : function(target, value) {
				$(target).numberspinner('setValue', value);
			},
			resize : function(target, width) {
				$(target).numberspinner('resize', width);
			}
		}
	});
	$(function() {
		$('#tt').datagrid({
			onBeforeEdit : function(index, row) {
				row.editing = true;
				updateActions(index);
			},
			onAfterEdit : function(index, row) {
				row.editing = false;
				updateActions(index);
			},
			onCancelEdit : function(index, row) {
				row.editing = false;
				updateActions(index);
			}
		});
	});
	function updateActions(index) {
		$('#tt').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function editrow(target) {
		$('#tt').datagrid('beginEdit', getRowIndex(target));
	}
	function deleterow(target) {
		$.messager.confirm('Confirm', 'Are you sure?', function(r) {
			if (r) {
				$('#tt').datagrid('deleteRow', getRowIndex(target));
			}
		});
	}
	function saverow(target) {
		$('#tt').datagrid('endEdit', getRowIndex(target));
	}
	function cancelrow(target) {
		$('#tt').datagrid('cancelEdit', getRowIndex(target));
	}
</script>
</head>

<body style="height:100%;width:100%;overflow:hidden;border:none;">
		<div class="easyui-layout" fit="true">
		<div data-options="region:'north',border:false"
			style="height:60px;background:#B3DFDA;padding:10px">
			<div agin="center">
				<h2>微信公众号后台管理系统</h2>
			</div>
		</div>

		<div region="west" split="true" title = "菜单导航" style="width:15%;">
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
			<div region="center" border="false" >
				<div class="easyui-tabs" fit="true">
					<div title="Home" style="padding:0px;">
						<div id="toolbar" style="padding:5px;height:auto">
							
								日期: <input class="easyui-datebox" style="width:100px">
								到:  <input class="easyui-datebox" style="width:100px">
								账号: <input id="username" class="easyui-textbox" style="width:100px" url="<%=request.getContextPath()%>/user/listUser">
									
								<a id="search" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
								<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
								<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a> 
								<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
								<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a> 
								<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
							
						</div>


						<table id="gridtable" class="easyui-datagrid"
							style="width:100%;height:100%;" url=""
							toolbar="#tb" rownumbers="true"
							pagination="true" singleSelect="true" idField="itemid"
							fitColumns="true">
							<thead>
								<tr>
									<th field="ck" checkbox="true" width="40" align="left"></th>
									<th field="id" width="80" align="left">编码</th>
									<th field="username" width="80" align="left">姓名</th>
									<th field="password" width="80" align="left">密码</th>
									<th field="sex" width="80" align="left">性别</th>
									<th field="age" width="80" align="left">年龄</th>
									<th field="action" width="80" align="left" formatter="formatAction">操作</th>
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
			</div>
			
		</div>
	
	<form action="<%=request.getContextPath()%>/user/listUser" method="get">
		<input type="submit" name="list" value="列表">
	</form>
</body>




</html>
