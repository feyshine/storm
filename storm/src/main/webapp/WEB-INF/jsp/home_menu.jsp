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
	$(function(){
		$('homemenu').tree({
			url:'${pageContext.request.contextPath}/nodes/top',
			lines:true,
			onBeforeExpand:function(node,param){    
            	$('#homemenu').tree('options').url = "${pageContext.request.contextPath}/nodes/children?parentid=" + node.id;  //动态获取节点  
            },    
            loadFilter: function(data){      
            	if (data.msg){      
                	return data.msg;      
                } else {      
                    return data;      
                }      
           },  
           onClick:function(node){                      //节点的点击事件  
           	var url=<%=request.getContextPath()%>+"/home/"+node.url;   
           	addTab(node.text,url);   
             }   
		});
	});

		
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
					<a class="easyui-linkbutton" onclick="addTab('数据分析','<%=request.getContextPath()%>/home/dataAnalysis')">数据分析</a>
				</span>
			</li>
			</div>
		</ul>
</div>
</body>
</html>
