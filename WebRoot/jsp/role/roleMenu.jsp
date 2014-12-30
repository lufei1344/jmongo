<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"${pageContext.request.contextPath}/admin/role/saveMenu.action",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var nds = zTree.getCheckedNodes(true);
				
				var ids = [];
				for(var i=0; i<nds.length; i++){
					ids.push(nds[i].id);
				}
				$("#checkedIds").val(ids.join(","));
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.status) {
					//parent.reload;
					//parent.$.modalDialog.openner.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :  result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
		
		loadTree();
	});
	
	
	var setting = {
			check: {
				enable: true
			},
			data: {
				key :{
					name : "name"
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: null
				}
			}
		};
	var zTree;	
	function loadTree(){
		var zNodes = ${menus};
		var mids = ${mids};

		zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);	
		
		var node;
		for(var i=0; i<mids.length; i++){
			node = zTree.getNodeByParam("id",mids[i]);
			if(node != null){
				node.checked = true;
			}
		}
	}	
</script>
<style>
	.easyui-textbox{
		height: 18px;
		width: 170px;
		line-height: 16px;
	    /*border-radius: 3px 3px 3px 3px;*/
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
	}
	
	textarea:focus, input[type="text"]:focus{
	    border-color: rgba(82, 168, 236, 0.8);
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
	    outline: 0 none;
		}
		table {
	    background-color: transparent;
	    border-collapse: collapse;
	    border-spacing: 0;
	    max-width: 100%;
	}

	fieldset {
	    border: 0 none;
	    margin: 0;
	    padding: 0;
	}
	legend {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #E5E5E5;
	    border-image: none;
	    border-style: none none solid;
	    border-width: 0 0 1px;
	    color: #999999;
	    line-height: 20px;
	    display: block;
	    margin-bottom: 10px;
	    padding: 0;
	    width: 100%;
	}
	input, textarea {
	    font-weight: normal;
	}
	table ,th,td{
		text-align:left;
		padding: 6px;
	}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="form" method="post">
			<input name="roleId" type="hidden" value="${param.roleId }"/>
			<input name="checkedIds" id="checkedIds" type="hidden" value=""/>
		</form>
			<fieldset>
				<legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/> 菜单</legend>
				<ul id="treeDemo" class="ztree"></ul>
			</fieldset>
	</div>
</div>
