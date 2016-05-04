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
$(document).ready(function(){
	$.ajax({
		type:'post',
		dataType:'json',
		url:'${pageContext.request.contextPath}/node/accordion',
		success:function(data){
			addNav(data);
		},
		error:function(){
			alert("error");
		}
	});
	
	$('#menu').accordion({
		autoHeight:false,
		navigator:true
	});
	
	function addNav(data){
		var result = eval(data);
			$.each(result.data,function(i,n){
				$('#menu').accordion('add',{
					title:n.name,
					content:'<div style="padding:10px;agin:center"><ul id = "'+n.name+'" name="'+n.name+'"></ul></div>',
					selected:false
				});
				addTree(n);
			});
			closeAll();
	}
	
	function addTree(n){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'${pageContext.request.contextPath}/node/tree?pname=' + encodeURI(n.name),
			success:function(data){
					var result = eval(data);
					$('#'+ n.name).tree({
						data:result.data,
						onClick:function (node){
							if($('#'+node.id).tree('isLeaf',node.target)){
								addTab(node.text,node.attributes);
							}
						}
					});
				}
		});
 
	}

	function closeAll(){
		var panels = $('#menu').accordion("panels");
		var p = panels[0].panel('options').title;
		$('#menu').accordion('unselect',p);
	}

	function addTab(text,url){
		if($('#tab').tabs('exists',text)){
				$('#tab').tabs('select', text);
			}else{
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:95%;"></iframe>';
				$('#tab').tabs('add', {
				title : text,
				content : content,
				closable : true,
			   });
		    }
	}
});
</script>
</head>

<body>
<div data-options="region:'west',border:true" title="导航" style="width:15%;height:90%">
	<div id="menu" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">

	</div>
</div>

</body>
</html>
