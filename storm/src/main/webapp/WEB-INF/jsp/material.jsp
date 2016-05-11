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
			add("articles_list","articles_dlg","${pageContext.request.contextPath}/article/add");
		}
	},'-', {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			edit("articles_list","articles_dlg","${pageContext.request.contextPath}/article/edit?id=");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var row = $("#articles_list").datagrid('getSelected');
			dele("articles_list","${pageContext.request.contextPath}/article/delete",row.id);
		}
	} ];
	
	var imgtoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("img_list","img_dlg","${pageContext.request.contextPath}/material/loadimg");
		}
	},'-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var row = $("#img_list").datagrid('getSelected');
			dele("img_list","${pageContext.request.contextPath}/material/deleteimg",row.imgId);
		}
	} ];
	
	var voicetoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("voice_list","voice_dlg","${pageContext.request.contextPath}/material/addvoice");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var row = $("#voice_list").datagrid('getSelected');
			dele("voice_list","${pageContext.request.contextPath}/material/deletevoice",row.imgId);
		}
	} ];
	
	var videotoolbar = [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			add("video_list","video_dlg","${pageContext.request.contextPath}/material/addvideo");
		}
	},'-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var row = $("#video_list").datagrid('getSelected');
			dele("video_list","${pageContext.request.contextPath}/material/deletevideo",row.imgId);
		}
	} ];

	var url;//请求路径
	var type;
	var vdlg;//dialog
	var table;//table
	function add(tab,dlg,urls) {
		table = tab;
		vdlg = dlg;
		$("#" + dlg).dialog("open").dialog('setTitle', '增加');
		
		$("#fm").form("clear");
		url = urls;
		document.getElementById("hidtype").value = "submit";
	}
	function edit(tab,dlg,urls) {
		table = tab;
		vdlg = dlg;
		var row = $("#" + tab).datagrid("getSelected");
		if (row) {
			$("#" + dlg).dialog("open").dialog('setTitle', '编辑');
			$("#fm").form("load", row);
			url = urls + row.id;
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
					$("#" + vdlg).dialog("close");
					$("#" + table).datagrid("load");
				} else {
					$.messager.alert("提示信息", obj.msg);
				}
			}
		});
	}
	function dele(tab,urls,rowId) {
		var row = $("#" + tab).datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '你确定要删除?', function(r) {
				if (r) {
					$.post(urls,
							{
								id : rowId
							}, function(data) {
								if (data.result == "1") {
									$.messager.alert("提示信息", data.msg);
									$("#" + tab).datagrid('reload');
								} else {
									$.messager.alert('错误', data.msg);
								}
							}, 'json');
				}
			});
		}
	}
	
	function reload(parent){
		$(parent).datagrid('reload');
	}
	

	$(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/material/queryall",
			type : "post",
			success : function(data) {
				var themecombo = [ {
					'text' : '请选择',
					'id' : ''
				} ];
				for (var i = 0; i < data.rows.length; i++) {
					themecombo.push({
						"text" : data.rows[i].imgName,
						"id" : data.rows[i].imgId
					});
				}
				$("#combobox1").combobox("loadData", themecombo);
			}
		});
	});
	
	$(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/state/queryall",
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
				$("#combobox2").combobox("loadData", themecombo);
			}
		});
	});
</script>

</head>

<body>
	<div id="tabs" class="easyui-tabs" style="padding:5px" data-options="fit:true,border:true" style="width:100%;height:100%">
		<div title="图文素材" data-options="tools:'#articles_list_tools'" >
			<table id="articles_list" class="easyui-datagrid" 
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,
				url:'${pageContext.request.contextPath}/article/queryArticleByPage',
				method:'post',border:false,toolbar:articletoolbar">
				<thead data-options="frozen:true">
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th data-options="field:'id',width:100">编码</th>
						<th data-options="field:'title',width:100">标题</th>
						<th data-options="field:'thumbMediaId',width:100">封面图片</th>
						<th data-options="field:'author',width:100">作者</th>
						<th data-options="field:'digest',width:100">摘要</th>
						<th data-options="field:'showCoverPic',width:100">是否显示封面</th>
						<th data-options="field:'content',width:500">具体内容</th>
						<th data-options="field:'contentSourceUrl',width:200">原文链接</th>
					</tr>
				</thead>
			</table>
			<div id="articles_list_tools">
				<a href="javascript:void(0)" class="icon-mini-refresh"
					onclick="reload('#articles_list')"></a>
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
						<label>标题</label><input name="title" class="easyui-validatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>封面图片ID</label><input id = "combobox1" name="thumbMediaId"
							class="easyui-combobox"  data-options="valueField:'id', textField:'text',required:true,
							editable:false,panelHeight:'auto'"/>
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
						<label>是否显示封面</label><input id = "combobox2" name="showCoverPic"
							class="easyui-combobox"  data-options="valueField:'id', textField:'text',
							required:true,editable:false,panelHeight:'auto'" />
					</div>
					<div class="fitem">
						<label>摘要</label><input name="digest" class="easyui-vlidatebox"
							required="true" />
					</div>
					<div class="fitem">
						<label>原文链接</label><input name="contentSourceUrl"
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
						onclick="save()" iconcls="icon-save">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="javascript:$('#articles_dlg').dialog('close')"
						iconcls="icon-cancel">取消</a>
				</div>
			</div>
		</div>
		<div title="图片素材" data-options="tools:'#img_list_tools'" >
			<table id="img_list" class="easyui-datagrid" 
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,
				url:'${pageContext.request.contextPath}/material/queryImgByPage',
				method:'post',border:false,toolbar:imgtoolbar">
				<thead data-options="frozen:true">
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th data-options="field:'imgId',width:100">图片ID</th>
						<th data-options="field:'imgName',width:100">名称</th>
						<th data-options="field:'imgSize',width:100">大小</th>
						<th data-options="field:'imgType',width:100">格式</th>
						<th data-options="field:'imgPath',width:200">路径</th>
						
					</tr>
				</thead>
			</table>
			<div id="img_list_tools">
				<a href="javascript:void(0)" class="icon-mini-refresh"
					onclick="reload('#img_list')"></a>
			</div>
			<div id="img_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#img-dlg-buttons">
				<div class="ftitle">图片上传</div>
				<form id="fm" method="post" enctype="multipart/form-data" >
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
		<div title="声音素材" data-options="tools:'#voice_list_tools'" >
			<table id="voice_list" class="easyui-datagrid" 
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,
				url:'',
				method:'post',border:false,toolbar:voicetoolbar">
				<thead data-options="frozen:true">
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
					</tr>
				</thead>
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
					onclick="reload('#voice_list')"></a>
			</div>
			<div id="voice_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#voice-dlg-buttons">
				<div class="ftitle">信息编辑</div>
				<form id="fm" method="post" enctype="multipart/form-data">
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
		<div title="视频素材" data-options="tools:'#video_list_tools'">
			<table id="video_list" class="easyui-datagrid" 
				style="width:100%;height:100%" cellspacing="5" cellpadding="5" fit="true"
				data-options="rownumbers:true,singleSelect:true,pagination:true,
				url:'',
				method:'post',border:false,toolbar:videotoolbar">
				<thead data-options="frozen:true">
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
					</tr>
				</thead>
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
					onclick="reload('#video_list')"></a>
			</div>
			<div id="video_dlg" class="easyui-dialog"
				style="width: 400px; height:auto; padding: 10px 20px;" closed="true"
				buttons="#video-dlg-buttons">
				<div class="ftitle">信息编辑</div>
				<form id="fm" method="post" enctype="multipart/form-data">
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
