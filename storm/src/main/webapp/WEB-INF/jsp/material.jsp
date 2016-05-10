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
<title>素材管理</title>
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
	var articletoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("articles_dlg","${pageContext.request.contextPath}/material/add");
		}
	}, {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			edit("articles_list","${pageContext.request.contextPath}/material/edit?MsgId=");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			dele("articles_list","${pageContext.request.contextPath}/material/delete");
		}
	} ];
	
	var imgtoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("img_dlg","${pageContext.request.contextPath}/material/loadimg");
		}
	}, {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			edit("img_dlg","${pageContext.request.contextPath}/material/editimg?img_id=");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			dele("img_dlg","${pageContext.request.contextPath}/material/deleteimg");
		}
	} ];
	
	var voicetoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("voice_dlg","${pageContext.request.contextPath}/material/addvoice");
		}
	}, {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			edit("voice_dlg","${pageContext.request.contextPath}/material/editvoice?voice_id=");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			dele("voice_dlg","${pageContext.request.contextPath}/material/deletevoice");
		}
	} ];
	
	var videotoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("video_dlg","${pageContext.request.contextPath}/material/addvideo");
		}
	}, {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			edit("video_dlg","${pageContext.request.contextPath}/material/editvideo?video_id=");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			dele("video_dlg","${pageContext.request.contextPath}/material/deletevideo");
		}
	} ];

	var url;
	var type;
	var vdlg;
	function add(dlg,urls) {
		$("#" + dlg).dialog("open").dialog('setTitle', '增加');
		vdlg = dlg;
		$("#fm").form("clear");
		url = urls;
		document.getElementById("hidtype").value = "submit";
	}
	function edit(dlg,urls) {
		vdlg = dlg;
		var row = $("#" + dlg).datagrid("getSelected");
		if (row) {
			$("#" + dlg).dialog("open").dialog('setTitle', '编辑');
			$("#fm").form("load", row);
			url = urls + row.msgid;
		}
	}
	function save(table) {
		$("#fm").form("submit", {
			url : url,
			onsubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var obj = eval("(" + result + ")");//转换后的JSON对象
				if (obj.result == "1") {
					$.messager.alert("提示信息", obj.msg);
					$("#" + vdlg).dialog("close");
					$("#" + table).datagrid("load");
				} else {
					$.messager.alert("提示信息", obj.msg);
				}
			}
		});
	}
	function dele(table,urls) {
		var row = $("#" + table).datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '你确定要删除?', function(r) {
				if (r) {
					$.post('urls',
							{
								MsgId : row.msgid
							}, function(data) {
								if (data.result == "1") {
									$.messager.alert("提示信息", data.msg);
									$("#" + table).datagrid('reload');
								} else {
									$.messager.alert('错误', data.msg);
								}
							}, 'json');
				}
			});
		}
	}
	
	

	
</script>

</head>

<body>
	<div id="tabs" class="easyui-tabs" style="padding:5px" data-options="fit:true,border:true" style="width:100%;height:100%">
		<div title="图文素材" data-options="tools:'#articles_list_tools'" style="padding:5px">
			<table id="articles_list" class="easyui-datagrid" title="图文素材列表"
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,url:'',method:'post',border:true,toolbar:articletoolbar">
				<thead>
					<tr>
						<th data-options="field:'id',width:50">编码</th>
						<th data-options="field:'media_id',width:50">图文消息ID</th>
						<th data-options="field:'title',width:50">标题</th>
						<th data-options="field:'thumb_media_id',width:50">封面图片ID</th>
						<th data-options="field:'author',width:50">作者</th>
						<th data-options="field:'digest',width:100">摘要</th>
						<th data-options="field:'show_cover_pic',width:20">是否显示封面</th>
						<th data-options="field:'content',width:200">具体内容</th>
						<th data-options="field:'content_source_url',width:100">原文链接</th>
					</tr>
				</thead>
			</table>
			<div id="articles_list_tools">
				<a href="javascript:void(0)" class="icon-mini-refresh"
					onclick="alert('refresh')"></a>
			</div>
			<div id="articles_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#articles-dlg-buttons">
				<div class="ftitle">信息编辑</div>
				<form id="fm" method="post">
					<div class="fitem">
						<label>编码</label><input name="id" class="easyui-validatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>图文消息ID</label><input name="media_id"
							class="easyui-combobox"  data-options="url:'',method:'get',valueField:'id',textField:'text',multiple:true,required:true,editable:false,panelHeight:'auto'"/>
					</div>
					<div class="fitem">
						<label>标题</label><input name="title" class="easyui-validatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>封面图片ID</label><input name="thumb_media_id"
							class="easyui-combobox"  data-options="url:'',method:'get',valueField:'id',textField:'text',multiple:true,required:true,editable:false,panelHeight:'auto'"/>
					</div>
					<div class="fitem">
						<label>作者</label><input name="author" class="easyui-vlidatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>具体内容</label><input name="content" class="easyui-vlidatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>是否显示封面</label><input name="show_cover_pic"
							class="easyui-combobox"  data-options="url:'',method:'get',valueField:'id',textField:'text',multiple:true,required:true,editable:false,panelHeight:'auto'" />
					</div>
					<div class="fitem">
						<label>摘要</label><input name="digest" class="easyui-vlidatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>原文链接</label><input name="content_source_url"
							class="easyui-vlidatebox" required="true" />
					</div>

					<div class="fitem">
						<input type="hidden" name="action" id="hidtype" />
					</div>
					<div class="fitem">
						<input type="hidden" name="ID" id="Nameid" />
					</div>
				</form>
				<div id="articles-dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="save('articles_list')" iconcls="icon-save">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="javascript:$('#articles_dlg').dialog('close')"
						iconcls="icon-cancel">取消</a>
				</div>
			</div>
		</div>
		<div title="图片素材" data-options="tools:'#img_list_tools'" style="padding:5px">
			<table id="img_list" class="easyui-datagrid" title="图文素材列表"
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,url:'',method:'post',border:true,toolbar:imgtoolbar">
				<thead>
					<tr>
						<th data-options="field:'id',width:50">编码</th>
						<th data-options="field:'img_id',width:50">图片ID</th>
						<th data-options="field:'img_name',width:50">名称</th>
						<th data-options="field:'img_size',width:50">大小</th>
						<th data-options="field:'img_format',width:50">格式</th>
						<th data-options="field:'img_path',width:200">路径</th>
						
					</tr>
				</thead>
			</table>
			<div id="img_list_tools">
				<a href="javascript:void(0)" class="icon-mini-refresh"
					onclick="alert('refresh')"></a>
			</div>
			<div id="img_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#img-dlg-buttons">
				<div class="ftitle">图片上传</div>
				<form id="fm" method="post" >
					<div class="fitem">
						<label>名称</label>
						<input name="img_name" class="easyui-vlidatebox" required="true" />
					</div>
					<div class="fitem">
						<label>文件</label>
						<input class="easyui-filebox" name="file" data-options="prompt:'选择文件...'"  buttonText="选择" required="true" accept="image/gif,image/jpeg,image/png"/>
					</div>
					<div class="fitem">
						<input type="hidden" name="action" id="hidtype" />
					</div>
					<div class="fitem">
						<input type="hidden" name="ID" id="Nameid" />
					</div>
				</form>
				<div id="img-dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="save()" iconcls="icon-save">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="javascript:$('#img_dlg').dialog('close')"
						iconcls="icon-cancel">取消</a>
				</div>
			</div>
		</div>
		<div title="声音素材" data-options="tools:'#voice_list_tools'" style="padding:5px">
			<table id="voice_list" class="easyui-datagrid" title="图文素材列表"
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,url:'',method:'post',border:true,toolbar:voicetoolbar">
				<thead>
					<tr>
						<th data-options="field:'id',width:50">编码</th>
						<th data-options="field:'img_size',width:50">大小</th>
						<th data-options="field:'img_format',width:50">格式</th>
						<th data-options="field:'img_path',width:200">路径</th>
						
					</tr>
				</thead>
			</table>
			<div id="voice_list_tools">
				<a href="javascript:void(0)" class="icon-mini-refresh"
					onclick="alert('refresh')"></a>
			</div>
			<div id="voice_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#voice-dlg-buttons">
				<div class="ftitle">信息编辑</div>
				<form id="fm" method="post">
					<div class="fitem">
						<label>文件</label>
						<input class="easyui-filebox" name="file" data-options="prompt:'选择文件...'"  buttonText="选择" required="true" accept="image/gif,image/jpeg,image/png"/>
					</div>
					<div class="fitem">
						<input type="hidden" name="action" id="hidtype" />
					</div>
					<div class="fitem">
						<input type="hidden" name="ID" id="Nameid" />
					</div>
				</form>
				<div id="voice-dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="save()" iconcls="icon-save">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="javascript:$('#voice_dlg').dialog('close')"
						iconcls="icon-cancel">取消</a>
				</div>
			</div>
		</div>
		<div title="视频素材" data-options="tools:'#video_list_tools'" style="padding:5px">
			<table id="video_list" class="easyui-datagrid" title="图文素材列表"
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,url:'',method:'post',border:true,toolbar:videotoolbar">
				<thead>
					<tr>
						<th data-options="field:'id',width:50">编码</th>
						<th data-options="field:'img_size',width:50">大小</th>
						<th data-options="field:'img_format',width:50">格式</th>
						<th data-options="field:'img_path',width:200">路径</th>
						
					</tr>
				</thead>
			</table>
			<div id="video_list_tools">
				<a href="javascript:void(0)" class="icon-mini-refresh"
					onclick="alert('refresh')"></a>
			</div>
			<div id="video_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#video-dlg-buttons">
				<div class="ftitle">信息编辑</div>
				<form id="fm" method="post">
					<div class="fitem">
						<label>文件</label>
						<input class="easyui-filebox" name="file" data-options="prompt:'选择文件...'"  buttonText="选择" required="true" accept="image/gif,image/jpeg,image/png"/>
					</div>
					<div class="fitem">
						<input type="hidden" name="action" id="hidtype" />
					</div>
					<div class="fitem">
						<input type="hidden" name="ID" id="Nameid" />
					</div>
				</form>
				<div id="video-dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="save()" iconcls="icon-save">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="javascript:$('#video_dlg').dialog('close')"
						iconcls="icon-cancel">取消</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
