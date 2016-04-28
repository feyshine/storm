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
				closable : true,
				enableConextMenu: true,
				refreshable:true
			});
		}
	}

</script>
</head>

<body>
<div data-options="region:'west',border:true" title="导航" style="width:15%">
	<div id="menu" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
					<ul id="tree" class="easyui-tree" style="margin:10px">
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
					<a class="easyui-linkbutton" onclick="addTab('数据分析','<%=request.getContextPath()%>/home/dataAnalysis')">数据分析</a>
				</span>
			</li>
			</div>
		</ul>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:'post',
		dataType:'json',
		url:'${pageContext.request.contextPath}/nodes/top',
		success:function(data){
			var result = eval(data);
			$.each(result.data,function(i,n){
				$('#menu').accordion('add',{
					title:n.name,
					content:tree(n.children),//'<div style="padding:10px;agin:center"><ul name="'+n.name+'"></ul></div>',
					selected:false
				});
			});
		}
	});
	$('#menu').accordion({
		onSelect:function(title,index){
			$("ul[name='"+ title +"']").tree({
				url:'',
				method:'post',
			});
			
		}
	});
	
	function tree(parent){
		var text="";
		for(var i=0;i<parent.length;i++){
			var state = parent[i].state,
			text = '<a href ="#" onclick = "addTab(\''+state+'\',\''+parent[i].text+'\',\''+parent[i].url+'\'")>'+parent[i].text+'</a></br>'
		}
		return text;
	}
	
	
	function addTab(state,text,url){
		if(state!=null && state!=''){
			if($('#tab').tabs('exists',text)){
				$('#tab').tabs('select', text);
			}else{
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
				$('#tab').tabs('add', {
				title : text,
				content : content,
				closable : true,
			   });
		    }
		}
	}
});
</script>
</body>
</html>
