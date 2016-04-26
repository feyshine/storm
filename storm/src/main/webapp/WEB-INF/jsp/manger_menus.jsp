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
<script src="<c:url value="/resources/datagrid-detailview.js"/>"></script>

<script type="text/javascript">
 
	$(function(){
		$('#list_data').datagrid({ 
	        title:'应用系统列表', 
	        iconCls:'icon-edit',//图标 
	        width: 'auto', 
	        height: 'auto', 
	        nowrap: true, 
	        striped: true, 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
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
	        pageList: [5,10],//可以设置每页记录条数的列表 
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
        function add() {
            $("#dlg").dialog("open").dialog('setTitle', '增加菜单'); ;
            $("#fm").form("clear");
            url = "${pageContext.request.contextPath}/menu/add";
            document.getElementById("hidtype").value="submit";
        }
        function edit() {
            var row = $("#list_data").datagrid("getSelected");
            if (row) {
                $("#dlg").dialog("open").dialog('setTitle', '编辑菜单');
                $("#fm").form("load", row);
                url = "${pageContext.request.contextPath}/menu/edit?id=" + row.id;
                document.getElementById("hidtype").value="submit";
            }
        }
        function save() {
            $("#fm").form("submit", {
                url: url,
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                	var obj = eval( "(" + result + ")" );//转换后的JSON对象
                    if (obj.result == "1") {
                        $.messager.alert("提示信息", obj.msg);
                        $("#dlg").dialog("close");
                        $("#list_data").datagrid("load");
                    }
                    else {
                        $.messager.alert("提示信息",obj.msg);
                    }
                }
            });
        }
        function dele() {
            var row = $('#list_data').datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认', '你确定要删除?', function (r) {
                    if (r) {
                        $.post('${pageContext.request.contextPath}/menu/delete', { id: row.id }, function (result) {
                        	var obj = eval( "(" + result + ")" );//转换后的JSON对象
                            if (obj.result == "1") {
                            	$.messager.alert("提示信息",obj.msg);
                                $('#list_data').datagrid('reload');    // reload the user data  
                            } else {
                                $.messager.show({   // show error message  
                                    title: '提示信息',
                                    msg: obj.msg
                                });
                            }
                        }, 'json');
                    }
                });
            }
        }  
</script>

</head>

<body>
	<table id="list_data" class="easyui-datagrid" cellspacing="5" toolbar="#toolbar"
		cellpadding="5" border="fasle"  pagination="true" >
		<thead>
			<tr>
				<th field="id" width="400">编码</th>
				<th field="name" width="500">菜单名称</th>
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
           			<label>编号 </label><input name="id" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem">
       				<label>菜单名称</label><input name="name" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem"><input type="hidden" name="action" id="hidtype" /></div> 
       			<div class="fitem"><input type="hidden" name="ID" id="Nameid" /></div> 
       	</form>
       	<div id="dlg-buttons"> 
        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
    	</div> 
   </div>
   
   <script type="text/javascript">
        $(function(){
            $('#list_data').datagrid({
                view: detailview,
                detailFormatter:function(index,row){
                    return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
                },
                onExpandRow: function(index,row){
					$('#ddv-'+index).datagrid({
						url:'${pageContext.request.contextPath}/menu/queryChildrenByParent?id='+row.id,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'子菜单加载中...',
						height:'auto',
						columns:[[
							{field:'id',title:'id',width:100,align:'center'},
							{field:'name',title:'name',width:100,align:'center',editor:'text'},
							{field:'bkey',title:'key',width:100,align:'center',editor:'numberbox'},
							{field:'type',title:'type',width:100,align:'center',editor:'numberbox'},
							{field:'url',title:'url',width:100,align:'center',editor:'numberbox'},
							{field:'mediaId',title:'mediaId',width:100,align:'center',editor:'numberbox'},
							{field:'parentid',title:'parentid',width:100,align:'center',editor:'numberbox'},
							{field:'status',title:'状态',width:100,align:'center',
            					editor:{
                						type:'checkbox',
                						options:{
                    								on: 'P',
                    								off: ''
                						}
            					}
        					},
							{field:'action',title:'操作',width:100,align:'center',
            					formatter:function(value,row,index){
                						if (row.editing){
                    							var s = '<a href="javascript:void(0)" onclick="saverow('+index+')">保存</a> ';
                    							var c = '<a href="javascript:void(0)" onclick="cancelrow('+index+')">取消</a>';
                    							return s+c;
                							} else {
                    							var e = '<a href="javascript:void(0)" onclick="editrow('+index+')">编辑</a> ';
                    							var d = '<a href="javascript:void(0)" onclick="deleterow('+index+')">删除</a>';
                    							return e+d;
                							}
            							}
        					}
							
						]],
						onBeforeEdit:function(index,row){
        					row.editing = true;
        					updateActions(index);
        					//$('#list_data').datagrid('refreshRow', index);
    					},
    					onAfterEdit:function(index,row){
        					row.editing = false;
        					updateActions(index);
        					//$('#list_data').datagrid('refreshRow', index);
    						},
    					onCancelEdit:function(index,row){
        					row.editing = false;
        					updateActions(index);
        					//$('#list_data').datagrid('refreshRow', index);
    					},
						onResize:function(){
							$('#list_data').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#list_data').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
					$('#list_data').datagrid('fixDetailRowHeight',index);
                }
            });
        });
        
        function updateActions(index){
			$('#list_data').datagrid('updateRow',{
				index: index,
				row:{}
			});
		}
		
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		
		function editrow(target){
			$('#list_data').datagrid('beginEdit', getRowIndex(target));
		}
		
		function deleterow(target){
			$.messager.confirm('操作提示','你确定删除吗?',function(r){
				if (r){
					$('#list_data').datagrid('deleteRow', getRowIndex(target));
				}
			});
		}
		
		function saverow(target){
			$('#list_data').datagrid('endEdit', getRowIndex(target));
		}
		
		function cancelrow(target){
			$('#list_data').datagrid('cancelEdit', getRowIndex(target));
		}
    </script>
</body>
</html>
