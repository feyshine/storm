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
    
    <title>消息管理</title>
    
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
	<script src="<c:url value="/resources/easyui-lang-zh_CN.js"/>"></script>

	<script type="text/javascript">
		$(function() {
			$('#list_message').datagrid({
				title : '应用系统列表',
				iconCls : 'icon-edit',//图标 
				width : 'auto',
				height : 'auto',
				nowrap : true,
				striped : true,
				border : true,
				collapsible : false,//是否可折叠的 
				fit : true,//自动大小 
				//sortName: 'code', 
				//sortOrder: 'desc', 
				remoteSort : false,
				idField : 'fldId',
				singleSelect : true,//是否单选 
				pagination : true,//分页控件 
				rownumbers : true,//行号 
				url : '${pageContext.request.contextPath}/message/queryByPageSize',
				method : 'POST',
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
	
			});
	
			//设置分页控件 
			var p = $('#list_message').datagrid('getPager');
			$(p).pagination({
				pageSize : 10,//每页显示的记录条数，默认为10 
				pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表 
				beforePageText : '第',//页数文本框前显示的汉字 
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			/*onBeforeRefresh:function(){
			    $(this).pagination('loading');
			    alert('before refresh');
			    $(this).pagination('loaded');
			}*/
			});
		});
	
		var url;
		var type;
		function add() {
			$("#dlg").dialog("open").dialog('setTitle', '增加');
			;
			$("#fm").form("clear");
			url = "${pageContext.request.contextPath}/message/add";
			document.getElementById("hidtype").value = "submit";
		}
		function edit() {
			var row = $("#list_message").datagrid("getSelected");
			if (row) {
				$("#dlg").dialog("open").dialog('setTitle', '编辑');
				$("#fm").form("load", row);
				url = "${pageContext.request.contextPath}/message/edit?MsgId="
						+ row.msgid;
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
						$("#dlg").dialog("close");
						$("#list_message").datagrid("load");
					} else {
						$.messager.alert("提示信息", obj.msg);
					}
				}
			});
		}
		function dele() {
			var row = $('#list_message').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你确定要删除?', function(r) {
					if (r) {
						$.post(
							'${pageContext.request.contextPath}/message/delete',
							{MsgId : row.msgid},
							function(data) {
								if (data.result == "1") {
									$.messager.alert("提示信息", data.msg);
									$('#list_message').datagrid('reload');
								} else {
									$.messager.alert('错误',data.msg);
								}
							},
							'json'
						);
					}
				});
			}
		}
	</script>
</head>
	<body>
		<table id="list_message" class="easyui-datagrid" cellspacing="5" toolbar="#toolbar"
			cellpadding="5" border="fasle">
			<thead>
				<tr>
					<th field="msgid" width="200">消息ID</th>
					<th field="tousername" width="200">接收账号</th>
					<th field="fromusername" width="250">发送账号</th>
					<th field="createtime" width="200">消息创建时间</th>
					<th field="msgtype" width="200">消息类型</th>
					<th field="content" width="300">消息内容</th>
					<th field="picurl" width="300">图片链接</th>
					<th field="mediaid" width="300">图片/语音消息媒体</th>
					<th field="format" width="200">语音格式</th>
					<th field="thumbmediaid" width="200">视频消息缩略图</th>
					<th field="locationX" width="200">地理位置维度</th>
					<th field="locationY" width="200">地理位置经度</th>
					<th field="scale" width="200">地图缩放大小</th>
					<th field="title" width="300">消息标题</th>
					<th field="description" width="500">消息描述</th>
					<th field="url" width="300">消息链接</th>
				</tr>
			</thead>
	
		</table>
		<div id="toolbar">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="add()" plain="true">添加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" onclick="edit()" plain="true">修改</a> 
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" onclick="dele()" plain="true">删除</a>
	    </div>
	    
	    <div id="dlg" class="easyui-dialog" style="width: 400px; height:auto; padding: 10px 20px;" closed="true" buttons="#dlg-buttons"> 
	       <div class="ftitle">信息编辑 </div> 
	       		<form id="fm" method="post">
	       			<div class="fitem"> 
	           			<label>消息ID</label><input name="msgid" class="easyui-validatebox" required="true" /> </div> 
	       			<div class="fitem"> 
	           			<label>接收账号 </label><input name="tousername" class="easyui-validatebox" required="true" /> </div> 
	       			<div class="fitem">
	       				<label>发送账号</label><input name="fromusername" class="easyui-validatebox" required="true" /> </div> 
	       			<div class="fitem">
	       				<label>消息创建时间</label><input name="createtime" class="easyui-validatebox" required="true" /> </div> 
	       			<div class="fitem"> 
	           			<label>消息类型</label><input name="msgtype" class="easyui-vlidatebox" required="true" /> </div> 
	           		<div class="fitem"> 
	           			<label>消息内容</label><input name="content" class="easyui-vlidatebox" required="true"/> </div>
	       			<div class="fitem"> 
	           			<label>图片链接</label><input name="picurl" class="easyui-vlidatebox" required="true"/> </div>
	           		<div class="fitem"> 
	           			<label>图片/语音消息媒体</label><input name="mediaid" class="easyui-vlidatebox" required="true"/> </div> 
	           		<div class="fitem"> 
	           			<label>语音格式</label><input name="format" class="easyui-vlidatebox" required="true"/> </div> 
	           		<div class="fitem"> 
	           			<label>视频消息缩略图</label><input name="thumbmediaid" class="easyui-vlidatebox" required="true"/> </div> 
	           		<div class="fitem"> 
	           			<label>地理位置维度</label><input name="locationX" class="easyui-vlidatebox" required="true"/> </div>
	           		<div class="fitem"> 
	           			<label>地理位置经度</label><input name="locationY" class="easyui-vlidatebox" required="true"/> </div>
	           		<div class="fitem"> 
	           			<label>地图缩放大小</label><input name="scale" class="easyui-vlidatebox" required="true"/> </div>    
	           		<div class="fitem"> 
	           			<label>消息标题</label><input name="title" class="easyui-vlidatebox" required="true"/> </div>  
	           		<div class="fitem"> 
	           			<label>消息描述</label><input name="description" class="easyui-vlidatebox" required="true"/> </div>
	           		<div class="fitem"> 
	           			<label>消息链接</label><input name="url" class="easyui-vlidatebox" required="true"/> </div>    
	       			<div class="fitem"><input type="hidden" name="action" id="hidtype" /></div> 
	       			<div class="fitem"><input type="hidden" name="ID" id="Nameid" /></div> 
	       	</form>
	       	<div id="dlg-buttons"> 
	        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
	        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
	    	</div> 
	   </div>
	</body>
</html>
