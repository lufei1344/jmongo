<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>菜单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
		<script type="text/javascript">
			var $dg;
			var $grid;
			var typedata=[{"type":"F","typeName":"菜单"},{"type":"O","typeName":"操作"}];
			$(function() {
				 $dg = $("#dg");
				 $grid=$dg.treegrid({
					width : 'auto',
					height : $(this).height(),
					url : "${pageContext.request.contextPath}/admin/menu/list.action",
					rownumbers:true,
					animate: true,
					collapsible: true,
					fitColumns: true,
					striped:true,
					border:true,
					//singleSelect:false,
					idField: 'id',
					treeField: 'name',
					 frozenColumns:[[
					                 {title:'菜单名称',field:'name',editor : {type:'validatebox',options:{required:true}},width:parseInt($(this).width()*0.2),
					                  formatter:function(value){
					                   return '<span style="color:red">'+value+'</span>';
					                  }
					                 }
					    ]],
					columns : [ [ //{field:'ck',checkbox:true},
					              //{field : 'name',title : '程式名称',width : 250,editor : {type:'validatebox',options:{required:true}}},
					              {field : 'pname',title : '父菜单名称',width : parseInt($(this).width()*0.1),align : 'left'},
					              {field : 'sort',title : '排序编码',width : parseInt($(this).width()*0.1),editor:{type:'numberbox'}},
					              {field : 'iconCls',title : '菜单图标',align : 'center',width : parseInt($(this).width()*0.1),
					            	  formatter:function(value,row){
					            		  return "<span class='"+row.iconCls+"' style='display:inline-block;vertical-align:middle;width:16px;height:16px;'></span>";
										},
					            	  editor:{
											type:'combobox',
											options:{
												//valueField:'type',
												//textField:'typeName',
												data:$.iconData,
												formatter : function(v) {
													return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
												},
												value : 'wrench'
											}
										}},
					              {field : 'url',title : '菜单路径',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true}}},
					              {field : 'myid',title : '菜单编码',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true}}},
					              {field : 'type',title : '菜单类型',width : parseInt($(this).width()*0.1),align : 'left',
					            	  formatter:function(value,row){
					            		  if("F"==row.type)
												return "<font color=green>菜单<font>";
						            		  else
						            			return "<font color=red>操作<font>";  
										},
										editor:{
											type:'combobox',
											options:{
												valueField:'type',
												textField:'typeName',
												data:typedata,
												required:true
											}
										}},
					              {field : 'isused',title : '是否启用',width : parseInt($(this).width()*0.1),align : 'center',
					            	  formatter:function(value,row){
					            		  if("Y"==row.isused)
											return "<font color=green>是<font>";
					            		  else
					            			return "<font color=red>否<font>";  
										},
										editor:{type:'checkbox',options:{on:'Y',off:'N'}}
					              },
					              {field : 'description',title : '菜单描述',width : parseInt($(this).width()*0.2),align : 'left',editor : "text"}
					              ] ],toolbar:'#tb'
				});
			});
			var flag=true;
			function endEdit(){
				var select = $dg.treegrid('getSelections');
				if(select){
					var nodes = $dg.treegrid('getData');
					checkedNodes(nodes);
				}
				return flag;
			}
			//遍历节点和子节点
			function checkedNodes(nodes){
				if(nodes){
					$.each(nodes,function(i,node){
						if(node){
							$dg.treegrid('endEdit', node.permissionId);
							var temp=$dg.treegrid('validateRow', node.permissionId);
							if(!temp){ flag= false; }
						}
						if(node.children){
							checkedNodes(node.children);
						}
					});
				}
				return flag;
			}
			
			function editNode(){
				var nodes = $dg.treegrid('getSelections');
				if(nodes==null||nodes==""){
					$.messager.alert("提示", "请选择行记录!");
				}else{
					$.each(nodes,function(i,node){
						if(node){
							$dg.treegrid('beginEdit', node.permissionId);
						}
					});
				}
			}
			function removeNode(){
				var node = $dg.treegrid('getSelected');
				if(node){
					parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
					    if (r){  
					    	$.post("${pageContext.request.contextPath}/admin/menu/del.action", {id:node.id}, function(rsp) {
								if(rsp.status){
									$dg.treegrid('remove', node.id);
								}
								parent.$.messager.show({
									title : rsp.title,
									msg : rsp.message,
									timeout : 1000 * 2
								});
							}, "JSON").error(function() {
								parent.$.messager.show({
									title :"提示",
									msg :"提交错误了！",
									timeout : 1000 * 2
								});
							});
					    }  
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			function saveNodes(){
				if(endEdit()){
					if ($dg.treegrid('getChanges').length) {
						var inserted = $dg.treegrid('getChanges', "inserted");
						var deleted = $dg.treegrid('getChanges', "deleted");
						var updated = $dg.treegrid('getChanges', "updated");
						
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
						$.post("function/functionAction!persistenceFunction.action", effectRow, function(rsp) {
							if(rsp.status){
								$dg.datagrid('acceptChanges');
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
			//增加并列项
			function addStandPlaceNode(){
				var temp=jqueryUtil.getRandTime();
				var node = $dg.treegrid('getSelected');
				if (node){
					$dg.treegrid('insert', {
						after: node.permissionId,
						data: {
							permissionId:temp,
							pid:node.pid,
							pname:node.pname,
							sort:node.sort+1,
							url:'javascript:void(0);',
							status:'add'
						}
					});
					$dg.treegrid('unselect', node.permissionId);
					$dg.treegrid('select', temp);
					$dg.treegrid('beginEdit', temp);
				}else{
					$.messager.alert("提示", "请选择一行记录!");
				}
			}
			//增加子项
			function addSubitemNode(){
				var temp=jqueryUtil.getRandTime();
				var node = $dg.treegrid('getSelected');
				if (node){
					$dg.treegrid('insert', {
						after: node.permissionId,
						data: {
							permissionId:temp,
							pid:node.permissionId,
							pname:node.name,
							sort:node.sort+1,
							url:'javascript:void(0);',
							status:'add'
						}
					});
					$dg.treegrid('unselect', node.permissionId);
					$dg.treegrid('select', temp);
					$dg.treegrid('beginEdit', temp);
				}else{
					$.messager.alert("提示", "请选择一行记录!");
				}
			}
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $dg.treegrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : "编辑",
						width : 600,
						height : 400,
						href : "${pageContext.request.contextPath}/jsp/menu/edit.jsp?tempId="+row.type,
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
				var row = $dg.treegrid('getSelected');
				if(row){
					if(row.type=="O"){
						parent.$.messager.show({
							title :"提示",
							msg :"操作暂无下层!",
							timeout : 1000 * 2
						});
					}else{
						parent.$.modalDialog({
							title : "添加",
							width : 600,
							height : 400,
							href : "${pageContext.request.contextPath}/jsp/menu/edit.jsp",
							onLoad:function(){
								if(row){
									var f = parent.$.modalDialog.handler.find("#form");
									f.form("load", {"pid":row.id});
								}
							},	
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
				}else{
					parent.$.modalDialog({
						title : "添加",
						width : 600,
						height : 400,
						href : "${pageContext.request.contextPath}/jsp/menu/edit.jsp",
						onLoad:function(){
							if(row){
								var f = parent.$.modalDialog.handler.find("#form");
								f.form("load", {"pid":row.id});
							}
						},	
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
			}
		</script>
  </head>
  <body>
  	
    <div id="tb" style="padding:2px;height:auto">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<shiro:hasPermission name="funAdd">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="funEdit">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="funDel">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeNode();">删除</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</table>
		</div>
  		<table id="dg" title="程式管理"></table>
  </body>
</html>
