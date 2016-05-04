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
<title>菜单管理</title>
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
	});

		var url;
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
                        $.post(
                        	'${pageContext.request.contextPath}/menu/delete',
                        	{id:row.id},
                        	function (data) {
                            	if (data.result == "1") {
                            		$.messager.alert("提示信息",data.msg);
                                	$('#list_data').datagrid('reload');
                            	} else {
                                	$.messager.alert("提示信息",data.msg);
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
	<table id="list_data"  cellspacing="5" toolbar="#toolbar"
		cellpadding="5" border="fasle" >
		<thead>
			<tr>
				<th field="id" width="200">编码</th>
				<th field="name" width="300">菜单名称</th>
				<th field="type" width="300">类型</th>
				<th field="mkey" width="300">关键字</th>
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
       			<div class="fitem">
       				<label>类型</label><input name="type" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem">
       				<label>关键字</label><input name="mkey" class="easyui-validatebox" required="true" /> </div>  
       			<div class="fitem"><input type="hidden" name="action" id="hidtype" /></div> 
       			<div class="fitem"><input type="hidden" name="ID" id="Nameid" /></div> 
       		</form>
       		<div id="dlg-buttons"> 
        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
    		</div> 
   	</div>
   	
   	
   	<div id="child_dlg" class="easyui-dialog" style="width: 400px; height:auto; padding: 10px 20px;" closed="true" buttons="#child_dlg-buttons"> 
       		<div class="ftitle">信息编辑 </div> 
       		<form id="child_fm" method="post"> 
       			<div class="fitem"> 
           			<label>编号 </label><input name="id" class="easyui-validatebox" required="true" /> </div> 
       			<div class="fitem">
       				<label>名称</label><input name="name" class="easyui-validatebox" required="true" /> </div>
       			<div class="fitem">
       				<label>关键字</label><input name="bkey" class="easyui-validatebox" required="true" /> </div>
       			<div class="fitem">
       				<label>类型</label><input name="type" class="easyui-validatebox" required="true" /> </div>
       			<div class="fitem">
       				<label>链接</label><input name="url" class="easyui-validatebox" required="true" /> </div>
       			<div class="fitem">
       				<label>音/视频</label><input name="mediaId" class="easyui-validatebox" required="true" /> </div>
       			<div class="fitem">
       				<label>父菜单</label><input name="parentid" class="easyui-validatebox" required="true" /> </div>                                
       			<div class="fitem"><input type="hidden" name="action" id="subhidtype" /></div> 
       			<div class="fitem"><input type="hidden" name="ID" id="subNameid" /></div> 
       		</form>
       		<div id="child_dlg-buttons"> 
        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveChild()" iconcls="icon-save">保存</a> 
        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#child_dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
    		</div> 
   	</div>

   <script type="text/javascript">
        $(function(){
            $('#list_data').datagrid({
                view: detailview,
                detailFormatter:function(child,row){
                    return '<div style="padding:2px"><table id="ddv-' + child + '"></table></div>';
                },
                onExpandRow: function(child,row){
					$('#ddv-'+child).datagrid({
						url:'${pageContext.request.contextPath}/menu/queryChildrenByParent?id='+row.id,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'子菜单加载中...',
						height:'auto',
						singleSelect:true,
						frozenColumns:[[ 
	            			{field:'ck',checkbox:true} 
	        			]],
						columns:[[
							{field:'id',title:'编码',width:100,align:'center'},
							{field:'name',title:'名称',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
							{field:'bkey',title:'关键字',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
							{field:'type',title:'类型',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
							{field:'url',title:'链接',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
							{field:'mediaId',title:'音/视屏',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
							{field:'parentid',title:'父菜单',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
							{field:'status',title:'状态',width:100,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}},
							{field:'action',title:'操作',width:100,align:'center',
            					formatter:function(value,row,index){
                						if (row.editing){
                    							var s = '<a href="javascript:void(0)"  onclick="saverow('+child+','+index+',this)">保存</a> ';
                    							var c = '<a href="javascript:void(0)"  onclick="cancelrow('+child+','+index+',this)">取消</a>';
                    							return s+c;
                							} else {
                    							var e = '<a href="javascript:void(0)"  onclick="editrow('+child+','+index+',this)">编辑</a> ';
                    							var d = '<a href="javascript:void(0)"  onclick="deleterow('+child+','+index+',this)">删除</a>';
                    							return e+d;
                							}
            							}
        					}
							
						]],
						toolbar: ["-", {
				            id: '',
				            text: '增加',
				            iconCls: 'icon-add',
				            handler: function () {
				            	addChild(child);
				            }
				        }, "-", {
				            id: '',
				            text: '编辑',
				            iconCls: 'icon-edit',
				            handler: function () {
				            	editChild(child);
				            }
				        }, "-", {
				            id: '',
				            text: '删除',
				            iconCls: 'icon-remove',
				            handler: function () {
				            	deleteChild(child);
				            }
				        }, "-"],
						onResize:function(){
							$('#list_data').datagrid('fixDetailRowHeight',child);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#list_data').datagrid('fixDetailRowHeight',child);
							},0);
						},
						//sub datagrid method 
						onEndEdit:function(index,row,changes){
            				var ed = $('#ddv-' + child).datagrid('getEditor', {
                							index: '#ddv-' + child,
            				});
        				},
        				onBeforeEdit:function(index,row){
            				row.editing = true;
            				$('#ddv-' + child).datagrid('refreshRow', index);
        				},
        				onAfterEdit:function(index,row,changes){
            				row.editing = false;
            				$('#ddv-' + child).datagrid('refreshRow', index);
        				},
        				onCancelEdit:function(index,row){
            				row.editing = false;
            				$('#ddv-' + child).datagrid('refreshRow', index);
        				}
					});
					$('#list_data').datagrid('fixDetailRowHeight',child);
                }
            });
        });
        
 		//获取所在行
		function getRowIndex(child,index,target){
		    var tr = $(target).closest('tr.datagrid-row');
		    return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(child,index,target){
		    $('#ddv-' + child).datagrid('beginEdit', getRowIndex(child,index,target));
		}
		function deleterow(child,index,target){
		    $.messager.confirm('操作提示','你确定删除吗?',function(r){
		        if (r){
		            $('#ddv-' + child).datagrid('deleteRow', getRowIndex(child,index,target));
		        }
		    });
		}
		/*
		*child : sub datagrid id
		*index: row id
		*/
		function saverow(child,index,target){
		    $('#ddv-' + child).datagrid('endEdit', getRowIndex(child,index,target));
		}
		function cancelrow(child,index,target){
		    $('#ddv-' + child).datagrid('cancelEdit', getRowIndex(child,index,target));
		}
		
		var ch;
		function addChild(child) {
			this.ch= child;
            $("#child_dlg").dialog("open").dialog('setTitle', '增加子菜单'); ;
            $("#child_fm").form("clear");
            url = "${pageContext.request.contextPath}/menu/addChild";
            document.getElementById("subhidtype").value="submit";
        }
        
        
        function editChild(child){
        	this.ch= child;
            var row = $('#ddv-' + child).datagrid("getSelected");
            if (row) {
                $("#child_dlg").dialog("open").dialog('setTitle', '编辑子菜单');
                $("#child_fm").form("load", row);
                url = "${pageContext.request.contextPath}/menu/editChild?id=" + row.id;
                document.getElementById("subhidtype").value="submit";
            }
        }
        
        
        function deleteChild(child){
        var row = $('#ddv-' + child).datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认', '你确定要删除?', function (r) {
                    if (r) {
                        $.post(
                        	'${pageContext.request.contextPath}/menu/deleteChild', 
                        	{id: row.id,parentId: row.parentid},
                        	function (data) {
                            	if (data.result == "1") {
                            		$.messager.alert("提示信息",data.msg);
                                	$('#ddv-' + child).datagrid('reload');    // reload the user data  
                            	} else {
                                	$.messager.alert('提示信息',data.msg);
                            	}
                        	},
                        	'json'
                       );
                    }
                });
            }
        }

        function saveChild() {
            $("#child_fm").form("submit", {
                url: url,
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                	var obj = eval( "(" + result + ")" );//转换后的JSON对象
                    if (obj.result == "1") {
                        $.messager.alert("提示信息", obj.msg);
                        $("#child_dlg").dialog("close");
                        $('#ddv-' + ch).datagrid("load");
                    }
                    else {
                        $.messager.alert("提示信息",obj.msg);
                    }
                }
            });
        }
    </script>
</body>
</html>
