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
		<div data-options="region:'north',border:false" style="height:10%;background:#B3DFDA; padding:25px;">
			<div agin="center">
				<h2>微信公众号后台管理系统</h2>
			</div>
		</div>
	    <jsp:include page="home_menu.jsp"></jsp:include>
		<jsp:include page="home_content.jsp"></jsp:include>
			
		</div>
	
	<form action="<%=request.getContextPath()%>/user/listUser" method="get">
		<input type="submit" name="list" value="列表">
	</form>
</body>




</html>
