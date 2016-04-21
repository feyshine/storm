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
<script type="text/javascript">
	function addTab(title, url) {
		if ($('#tab').tabs('exists', title)) {
			$('#tab').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			$('#tab').tabs('add', {
				title : title,
				content : content,
				closable : true
			});
		}
	}

	//加载树型菜单   
	$('#homemenu').tree({
		onSelect : function(node) {
			openMenuTow(node);
		}
	});

	function openMenuTow(node) {
		//树型菜单的名字   
		var noteText = $(".tree-title", node.target).text();
		var exist_tab = $('#tab').tabs('getTab', noteText);
		//判断是否已经打开该选项卡  
		if (exist_tab) {
			$('#tab').tabs('select', noteText);
			return;
		} else {
			$('#tab').tabs('add', {
				'id' : 'tab',
				title : noteText,
				fit : true,
				content : '',
				closable : true
			});
			//获取最后一个tabs 在新加的选项卡后面添加"关闭全部"  
			var li = $(".tabs-wrap ul li:last-child");
			$("#close").remove();
			li
					.after("<li id='close'><a class='tabs-inner' href='javascript:void()' onClick='javascript:closeAll()'>关闭全部</a></li>");
		}
	}

	function closeAll() {
		$(".tabs li").each(function(index, obj) {
			//获取所有可关闭的选项卡  
			var tab = $(".tabs-closable", this).text();
			$(".easyui-tabs").tabs('close', tab);
		});
		$("#close").remove();//同时把此按钮关闭  
	}
</script>
</head>

<body>
<div data-options="region:'west',border:true" title="导航" style="width:15%">
		<ul id="homemenu" class="easyui-tree" style="margin:10px">
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" onclick="addTab('菜单管理','<%=request.getContextPath()%>/home/menu')">菜单管理</a>
				</span>
			</li>
			</div>
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" targe="_parent" onclick="addTab('消息管理','<%=request.getContextPath()%>/home/message')">消息管理</a>
				</span>
			</li>
			</div>
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" targe="_parent" onclick="addTab('微信客服','<%=request.getContextPath()%>/home/kservice')">微信客服</a>
				</span>
			</li>
			</div>
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" targe="_parent" onclick="addTab('网页管理','<%=request.getContextPath()%>/home/web')">网页管理</a>
				</span>
			</li>
			</div>
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" targe="_parent" onclick="addTab('素材管理','<%=request.getContextPath()%>/home/material')">素材管理</a>
				</span>
			</li>
			</div>
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" targe="_parent" onclick="addTab('用户管理','<%=request.getContextPath()%>/home/userManager')">用户管理</a>
				</span>
			</li>
			</div>
			<div style="margin:10px">
			<li>
				<span>
					<a class="easyui-linkbutton" onclick="addTab('','<%=request.getContextPath()%>/home/dataAnalysis')">数据分析</a>
				</span>
			</li>
			</div>
		</ul>
</div>
</body>
</html>
