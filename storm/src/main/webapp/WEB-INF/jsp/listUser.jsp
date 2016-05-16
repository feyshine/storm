<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<c:url value="/resources/easyui.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/icon.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/demo.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/dialog.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/jquery.easyui.min.js"/>"></script>
<script src="<c:url value="/resources/datagrid-detailview.js"/>"></script>
<script src="<c:url value="/resources/easyui-lang-zh_CN.js"/>"></script>

<script type="text/javascript">

	var toolbar = [ '-', {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add();
		}
	},'-', {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			edit();
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			dele();
		}
	}, '-'];
	
	
	var url;//请求路径
	var type;
	function add() {
		$("#fans_dlg").dialog("open").dialog('setTitle', '增加');
		$("#fm").form("clear");
		url = "${pageContext.request.contextPath}/fans/add";
		document.getElementById("hidtype").value = "submit";
	}
	
	
	function edit() {
		var row = $("#fans_list").datagrid("getSelected");
		if (row) {
			$("#fans_dlg").dialog("open").dialog('setTitle', '编辑');
			$("#fm").form("load", row);
			url = "${pageContext.request.contextPath}/fans/edit";
		}
	}
	
	
	function save() {
		$("#fm").form("submit", {
			url : url,
			onsubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var obj = eval("(" + result + ")");//转换后的JSON对象
				if (obj.result == "1") {
					$.messager.alert("提示信息", obj.msg);
					$("#fans_dlg").dialog("close");
					$("#fans_list").datagrid("load");
				} else {
					$.messager.alert("提示信息", obj.msg);
				}
			}
		});
	}
	
	function dele() {
		var row = $("#fans_list").datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '你确定要删除?', function(r) {
				if (r) {
					$.post("${pageContext.request.contextPath}/fans/delete",
							{
								id : row.openid
							}, function(data) {
								if (data.result == "1") {
									$.messager.alert("提示信息", data.msg);
									$("#fans_list").datagrid('reload');
								} else {
									$.messager.alert('错误', data.msg);
								}
							}, 'json');
				}
			});
		}
	}
	
	$(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/sex/query",
			type : "post",
			success : function(data) {
				var themecombo = [ {
					'text' : '请选择',
					'id' : ''
				} ];
				for (var i = 0; i < data.rows.length; i++) {
					themecombo.push({
						"text" : data.rows[i].name,
						"id" : data.rows[i].id
					});
				}
				$("#sex").combobox("loadData", themecombo);
			}
		});
	});
	
	
	$(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/country/query",
			type : "post",
			success : function(data) {
				var themecombo = [ {
					'text' : '请选择',
					'id' : ''
				} ];
				for (var i = 0; i < data.rows.length; i++) {
					themecombo.push({
						"text" : data.rows[i].name,
						"id" : data.rows[i].id
					});
				}
				$("#country").combobox("loadData", themecombo);
			}
		});
	});
	
	
	function loadProvince(cid) {

		$.ajax({
			url : "${pageContext.request.contextPath}/province/queryByCountry",
			type : "post",
			data : {cid:cid},
			success : function(data) {
				var themecombo = [ {
					'text' : '请选择',
					'id' : ''
				} ];
				for (var i = 0; i < data.rows.length; i++) {
					themecombo.push({
						"text" : data.rows[i].name,
						"id" : data.rows[i].id
					});
				}
				$("#province").combobox("loadData", themecombo);
			}
		});
	};
	
	$(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/language/query",
			type : "post",
			success : function(data) {
				var themecombo = [ {
					'text' : '请选择',
					'id' : ''
				} ];
				for (var i = 0; i < data.rows.length; i++) {
					themecombo.push({
						"text" : data.rows[i].name,
						"id" : data.rows[i].id
					});
				}
				$("#language").combobox("loadData", themecombo);
			}
		});
	});
	

	$(function() {
		$("#subscribe_time").datebox().datebox('calendar').calendar(
				{
					validator : function(date) {
						var now = new Date();
						var d1 = new Date(now.getFullYear(), now.getMonth(),
								now.getDate());
						var d2 = new Date(now.getFullYear() + 100, now.getMonth() + 1200,
								now.getDate() + 36500);
						return d1 <= date && date <= d2;
					}
				});
	});
</script>

</head>

<body>
	<table id="fans_list" class="easyui-datagrid"
		style="width:100%;height:100%" cellspacing="5" cellpadding="5"
		fit="true"
		data-options="rownumbers:true,singleSelect:true,pagination:true,
				url:'${pageContext.request.contextPath}/fans/queryFansByPage',
				method:'post',border:false,toolbar:toolbar">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th data-options="field:'openid',width:100">OpenID</th>
				<th data-options="field:'nickname',width:100">昵称</th>
				<th data-options="field:'sex',width:100">性别</th>
				<th data-options="field:'country',width:100">国家</th>
				<th data-options="field:'province',width:100">省份</th>
				<th data-options="field:'language',width:100">语言</th>
				<th data-options="field:'headimgurl',width:500">头像链接</th>
				<th data-options="field:'subscribe',width:200">关注</th>
				<th data-options="field:'subscribe_time',width:200">关注时间</th>
				<th data-options="field:'remark',width:200">备注</th>
				<th data-options="field:'groupid',width:200">所属组</th>
				<th data-options="field:'tagid_list',width:200">标签ID列表</th>
			</tr>
		</thead>
	</table>

	<div id="fans_dlg" class="easyui-dialog"
		style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">信息编辑</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label>OpenID</label><input name="openid"  class="easyui-validatebox"
					required="true" />
			</div>
			<div class="fitem">
				<label>昵称</label><input name="nickname" class="easyui-validatebox"
					required="true" />
			</div>
			<div class="fitem">
				<label>性别</label><input id="sex" name="sex"
					class="easyui-combobox"
					data-options="valueField:'id', textField:'text',required:true,
							editable:false,panelHeight:'auto'" />
			</div>
			<div class="fitem">
				<label>国家</label><input id="country" name="country" class="easyui-combobox"
					data-options="valueField:'id', textField:'text',onSelect:function(data){loadProvince(data.id)},
							required:true,editable:false,panelHeight:'200px'"/>
			</div>
			<div class="fitem">
				<label>省份</label><input id="province" name="province" class="easyui-combobox"
					data-options="valueField:'id', textField:'text',
							required:true,editable:false,panelHeight:'200px'"/>
			</div>
			<div class="fitem">
				<label>语言</label><input id="language" name="language"
					class="easyui-combobox"
					data-options="valueField:'id', textField:'text',
							required:true,editable:false,panelHeight:'200px'" />
			</div>
			<div class="fitem">
				<label>头像链接</label><input name="headimgurl" class="easyui-vlidatebox"
					required="true" />
			</div>
			<div class="fitem">
				<label>关注</label><input name="subscribe"
					class="easyui-vlidatebox" required="true" />
			</div>
			<div class="fitem">
				<label>关注时间</label><input id="subscribe_time" name="subscribe_time" required="true" />
			</div>
			<div class="fitem">
				<label>备注</label><input name="remark"
					class="easyui-vlidatebox" required="true" />
			</div>
			<div class="fitem">
				<label>所属组</label><input name="groupid"
					class="easyui-vlidatebox" required="true" />
			</div>
			<div class="fitem">
				<label>标签ID列表</label><input name="tagid_list"
					class="easyui-vlidatebox" required="true" />
			</div>
			<div class="fitem">
				<input type="hidden" name="action" id="hidtype" />
			</div>
			<div class="fitem">
				<input type="hidden" name="ID" id="Nameid" />
			</div>
		</form>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="save()" iconcls="icon-save">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:$('#fans_dlg').dialog('close')"
				iconcls="icon-cancel">取消</a>
		</div>
	</div>
	
</body>
</html>
