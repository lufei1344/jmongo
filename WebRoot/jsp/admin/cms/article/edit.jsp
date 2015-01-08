<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		
		
		$("#form").form({
			url :"${pageContext.request.contextPath}/admin/cms/article/edit.action",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
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
					parent.reload;
					parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
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
	});
	
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
			<fieldset>
				<legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>文章编辑</legend>
				<input name="id" id="id"  type="hidden"/>
				 <table>
					 <tr>
							<td width="10%" align="right">栏目：</td>
							<td colspan="2">
								<select name="entity.channel.id" id="entity.channel.id">
									<c:forEach var="channel" items="${channels}">
										<option value="${channel.id}">${channel.name}</option>
									</c:forEach>
								</select>
								 状态 <select name="entity.checkState" id="entity.checkState">
									<option value="draft" selected="selected">草稿箱</option>
									<option value="noCheck">待审核</option>
									<option value="pass">已审核</option>
									<option value="noPass">未通过</option>
									<option value="recycle">回收站</option>
									</select> 
									<script>
										document.getElementById("entity.checkState").value = '${entity.checkState}';
									</script> 
									推荐级别 <select name="entity.recommendLevel" id="entity.recommendLevel">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									</select> <script>
										document.getElementById("entity.recommendLevel").value = '${entity.recommendLevel}';
									</script> 
									允许评论 <input type="checkbox" name="entity.commentState" id="entity.commentState" value="true" /> <c:if test="${entity.commentState==true||entity.commentState==null}">
									<script>
										document.getElementById("entity.commentState").checked = "checked"
									</script>
								</c:if></td>
						</tr>
						<tr>
							<td align="right">标题：</td>
							<td colspan="2"><input name="title" value="${entity.title}" type="text" id="entity.title" size="40" maxlength="60" /><span class="info1">&nbsp;*&nbsp;${message1}</span></td>
						</tr>
						<tr>
							<td align="right">作者：</td>
							<td colspan="2"><input name="author" value="${entity.author}" type="text" id="entity.author" size="40" maxlength="20" /><span class="info1">&nbsp;*&nbsp;${message2}</span></td>
						</tr>
						<tr>
							<td align="right">发布时间：</td>
							<td colspan="2"><input name="releaseDate" value="${entity.releaseDate}" type="text" id="entity.releaseDate" size="40" maxlength="20" /><span class="info1">&nbsp;*&nbsp;${message3}</span></td>
						</tr>
						<tr>
							<td align="right">来源：</td>
							<td colspan="2"><input name="origin" value="${entity.origin}" type="text" id="entity.origin" size="40" maxlength="60" /></td>
						</tr>
						<tr>
							<td align="right">tag标签：</td>
							<td colspan="2"><input name="tags" value="${entity.tags}" type="text" id="entity.tags" size="40" maxlength="30" /></td>
						</tr>
						<tr>
							<td align="right" nowrap>标题缩略图：</td>
							<td colspan="2"><input name="titleImg" type="text" id="entity.titleImg" size=40 maxlength="80" style="display:" value="${entity.titleImg}" /></td>
						</tr>
						<tr>
							<td align="right">内容缩略图：</td>
							<td colspan="2">
								<div id="p1">
									<input name="contentImg" type="text" id="entity.contentImg" size=40 maxlength="80" style="display:" value="${entity.contentImg}" />
								</div>
							</td>
						</tr>

						<tr>
							<td align="right" nowrap>相关文章ID：</td>
							<td colspan="2"><input type="text" name="relatedID" id="entity.relatedID" value="${entity.relatedID}" size="40" /> <span class="info1"></span></td>
						</tr>
						<tr>
							<td align="right">摘要：</td>
							<td colspan="2"><textarea name="summary" rows="3" cols="97" id="entity.summary" value="${entity.summary}"></textarea> </td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="2">
								<textarea name="content" rows="3" cols="97" id="entity.content" value="${entity.content}"></textarea>
							</td>
						</tr>
				 </table>
			</fieldset>
		</form>
	</div>
</div>
