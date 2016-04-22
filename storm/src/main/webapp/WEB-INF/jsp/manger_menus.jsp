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

<title></title>

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

<script type="text/javascript">
 
$(function(){
	$('#list_data').datagrid({ 
        title:'应用系统列表', 
        iconCls:'icon-edit',//图标 
        width: 700, 
        height: 'auto', 
        nowrap: false, 
        striped: true, 
        border: true, 
        collapsible:false,//是否可折叠的 
        fit: false,//自动大小 
        //sortName: 'code', 
        //sortOrder: 'desc', 
        remoteSort:false,  
        idField:'fldId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        url:'${pageContext.request.contextPath}/menu/queryByPageSize',
		method:'POST',
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
 
    }); 

	//设置分页控件 
    var p = $('#list_data').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5,10,15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
    }); 
});

		var url;
        var type;
        function newuser() {
            $("#dlg").dialog("open").dialog('setTitle', '增加菜单'); ;
            $("#fm").form("clear");
            url = "${pageContext.request.contextPath}/menu/add";
            document.getElementById("hidtype").value="submit";
        }
        function edituser() {
            var row = $("#list_data").datagrid("getSelected");
            if (row) {
                $("#dlg").dialog("open").dialog('setTitle', '编辑菜单');
                $("#fm").form("load", row);
                url = "${pageContext.request.contextPath}/menu/edit?id=" + row.ID;
            }
        }
        function saveuser() {
            $("#fm").form("submit", {
                url: url,
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                	var obj = eval( "(" + result + ")" );//转换后的JSON对象
                    if (obj == "1") {
                        $.messager.alert("提示信息", "操作成功");
                        $("#dlg").dialog("close");
                        $("#list_data").datagrid("load");
                    }
                    else {
                        $.messager.alert("提示信息", "操作失败");
                    }
                }
            });
        }
        function destroyUser() {
            var row = $('#list_data').datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认', '你确定要删除?', function (r) {
                    if (r) {
                        $.post('${pageContext.request.contextPath}/menu/delete', { id: row.id }, function (result) {
                        	var obj = eval( "(" + result + ")" );//转换后的JSON对象
                            if (obj == "1") {
                                $('#list_data').datagrid('reload');    // reload the user data  
                            } else {
                                $.messager.show({   // show error message  
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        }, 'json');
                    }
                });
            }
        }  
</script>

</head>

<body style="width:100%;height:100%;">
	<table id="list_data" class="easyui-datagrid" cellspacing="5" toolbar="#toolbar"
		cellpadding="5" border="fasle" split="false" pagination="true"  style="width:600px;height:400px">
		<thead>
			<tr>
				<th field="name" width="100">菜单名称</th>
				<th field="xkey" width="100">菜单KEY值</th>
				<th field="type" width="150">菜单的响应动作类型</th>
				<th field="url" width="100">菜单链接</th>
				<th field="mediaId" width="100">素材接口</th>
				<th field="parent" width="100">父菜单</th>
			</tr>
		</thead>

	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="newuser()" plain="true">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" onclick="edituser()" plain="true">修改</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" onclick="destroyUser()" plain="true">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width: 400px; height:auto; padding: 10px 20px;" closed="true" buttons="#dlg-buttons"> 
       <div class="ftitle">信息编辑 </div> 
       		<form id="fm" method="post"> 
       			<div class="fitem"> 
           			<label>编号 </label><input name="id" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem">
       				<label>菜单名称</label><input name="name" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem">
       				<label>菜单KEY值</label><input name="xkey" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem"> 
           			<label>动作类型</label><input name="type" class="easyui-vlidatebox" required="true" /> </div> 
       			<div class="fitem"> 
           			<label>菜单链接</label><input name="url" class="easyui-vlidatebox" required="true"/> </div>
           		<div class="fitem"> 
           			<label>素材接口</label><input name="mediaId" class="easyui-vlidatebox" required="true"/> </div> 
           		<div class="fitem"> 
           			<label>父菜单</label><input name="parent" class="easyui-vlidatebox" required="true"/> </div>  
       			<div class="fitem"><input type="hidden" name="action" id="hidtype" /></div> 
       			<div class="fitem"><input type="hidden" name="ID" id="Nameid" /></div> 
       	</form>
       	<div id="dlg-buttons"> 
        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveuser()" iconcls="icon-save">保存</a> 
        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
    	</div> 
   </div>
</body>
</html>
