<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" tagdir="/WEB-INF/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>权限编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		var $role;
		var $function;
		var $grid;
		$(function() {
			
			$role = $("#role");
			$grid=$role.datagrid({
					url : "${pageContext.request.contextPath}/admin/role/list.action",
					width : 'auto',
					height : $(this).height(),
					pagination:true,
					border:false,
					rownumbers:true,
					singleSelect:true,
					striped:true,
					columns : [ [ {field : 'name',title : '角色名称',width :parseInt($(this).width()*0.1),align : 'center',editor : {type:'validatebox',options:{required:true}}},
					              {field : 'sort',title : '排序',width :parseInt($(this).width()*0.1),align : 'center',editor : "numberbox"},
					              {field : 'description',title : '角色描述',width : parseInt($(this).width()*0.1),align : 'center',editor : "text"}
					              ] ],toolbar:'#tbRole'
				});
				
			
			//搜索框
			$("#searchbox").searchbox({ 
				 menu:"#mm", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"roleName\":\""+name+"\",\"value\":\""+value+"\"}";
		            var obj = eval('('+str+')');
		            $role.datagrid('reload',obj); 
			    }
			   
			});
		});
		function endEdit(){
			var flag=true;
			var rows = $role.datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$role.datagrid('endEdit', i);
				var temp=$role.datagrid('validateRow', i);
				if(!temp){flag=false;}
			}
			return flag;
		}
		function addRows(){
			$role.datagrid('appendRow', {});
			var rows = $role.datagrid('getRows');
			$role.datagrid('beginEdit', rows.length - 1);
		}
		function editRows(){
			var rows = $role.datagrid('getSelections');
			$.each(rows,function(i,row){
				if (row) {
					var rowIndex = $role.datagrid('getRowIndex', row);
					$role.datagrid('beginEdit', rowIndex);
				}
			});
		}
		function removeRows(){
			var rows = $role.datagrid('getSelections');
			$.each(rows,function(i,row){
				if (row) {
					var rowIndex = $role.datagrid('getRowIndex', row);
					$role.datagrid('deleteRow', rowIndex);
				}
			});
		}
		function saveRows(){
			if(endEdit()){
				if ($role.datagrid('getChanges').length) {
					var inserted =$role.datagrid('getChanges', "inserted");
					var deleted =$role.datagrid('getChanges', "deleted");
					var updated = $role.datagrid('getChanges', "updated");
					
					var effectRow = new Object();
					if (inserted.length) {
						effectRow["inserted"] = JSON.stringify(inserted);
					}
					if (deleted.length) {
						effectRow["deleted"] = JSON.stringify(deleted);
					}
					if (updated.length) {
						effectRow["updated"] = JSON.stringify(updated);
					}
					$.post("${pageContext.request.contextPath}/admin/role/save.action", effectRow, function(rsp) {
						if(rsp.status){
							$role.datagrid('acceptChanges');
						}
						$.messager.alert(rsp.title, rsp.message);
					}, "JSON").error(function() {
						$.messager.alert("提示", "提交错误了！");
					});
				}
			}else{
				$.messager.alert("提示", "字段验证未通过!请查看");
			}
		}
		function collapseAll(){
			var node = $function.treegrid('getSelected');
			if (node) {
				$function.treegrid('collapseAll', node.id);
			} else {
				$function.treegrid('collapseAll');
			}
		}
		function expandAll(){
			var node = $function.treegrid('getSelected');
			if (node) {
				$function.treegrid('expandAll', node.id);
			} else {
				$function.treegrid('expandAll');
			}
		}
		function refresh(){
			$function.treegrid('reload');
		}
		function selectNode(){
			$function.treegrid('select','1');
		}
		function getLoad(){
			$role.datagrid('load',{ 
				roleName:$("#roleName").val()
			}); 
		}
		
		function savePermission(){
			var selections=$function.treegrid('getSelections');
			var selectionRole=$role.datagrid('getSelected');
			var checkedIds=[];
			$.each(selections,function(i,e){
				checkedIds.push(e.id);
			});
			if(selectionRole){
				$.ajax({
					url:"${pageContext.request.contextPath}/admin/role/saveMenu.action",
					data: "roleId="+selectionRole.roleId+"&checkedIds="+(checkedIds.length==0?"":checkedIds),
					success: function(rsp){
						parent.$.messager.show({
							title :rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					},
					error:function(){
						parent.$.messager.show({
							title :"提示",
							msg : "分配失败！",
							timeout : 1000 * 2
						});
					}

				});
			}else{
				 parent.$.messager.show({
						title :"提示",
						msg : "请选择角色！",
						timeout : 1000 * 2
					});
			 }
		}
		function delRows(){
			var row = $role.datagrid('getSelected');
			if(row){
				var rowIndex = $role.datagrid('getRowIndex', row);
				$role.datagrid('deleteRow', rowIndex);
				$.ajax({
					url:"${pageContext.request.contextPath}/admin/role/del.action",
					data: "roleId="+row.roleId,
					success: function(rsp){
						parent.$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					}
				});
			}else{
				parent.$.messager.show({
					title : "提示",
					msg : "请选择行数据!",
					timeout : 1000 * 2
				});
			}
		}
		//弹窗修改
		function updRowsOpenDlg() {
			var row = $role.datagrid('getSelected');
			if (row) {
				parent.$.modalDialog({
					title : "编辑角色",
					width : 600,
					height : 400,
					href : "${pageContext.request.contextPath}/jsp/role/edit.jsp",
					onLoad:function(){
						var f = parent.$.modalDialog.handler.find("#form");
						f.form("load", row);
					},			
					buttons : [ {
						text : '编辑',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}else{
				parent.$.messager.show({
					title :"提示",
					msg :"请选择一行记录!",
					timeout : 1000 * 2
				});
			}
		}
		//弹窗增加
		function addRowsOpenDlg() {
			parent.$.modalDialog({
				title : "添加角色",
				width : 600,
				height : 400,
				href : "${pageContext.request.contextPath}/jsp/role/edit.jsp",
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}
				]
			});
		}
		//菜单
		function menuOpenDlg() {
			var row = $role.datagrid('getSelected');
			if (row) {
				parent.$.modalDialog({
					title : "角色菜单",
					width : 600,
					height : 400,
					href : "${pageContext.request.contextPath}/admin/role/roleMenu.action?roleId="+row.id+"&s="+Math.random(),
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			
			}else{
				parent.$.messager.show({
					title :"提示",
					msg :"请选择一行记录!",
					timeout : 1000 * 2
				});
			}
			
		}
	</script>
	</head>
  <body>
   
				<div id="tbRole" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
								<shiro:hasPermission name="perConfig">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-role" plain="true" onclick="menuOpenDlg();">权限</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="roleAdd">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="roleEdit">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="roleDel">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
								</shiro:hasPermission>
							</td>
						</tr>
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
								<input id="searchbox" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="mm">
						<div name="name">角色名称</div>
						<div name="description">角色描述</div>
				</div>
				<table id="role" title="角色"></table>
	
  </body>
</html>
