	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%
		//String easyuiThemeName="metro";
		String easyuiThemeName="default";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("themeName")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	%>
	<link rel="stylesheet" type="text/css" id="uiTheme" href="${pageContext.request.contextPath}/themes/<%=easyuiThemeName %>/easyui.css"></link>
	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.all-3.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/FusionCharts/FusionChartsExportComponent.js"></script>
	<script type="text/javascript">
		
	</script>
	<style type="text/css">
		body {
		    font-family:helvetica,tahoma,verdana,sans-serif;
		    font-size:13px;
		    margin:0px 0px 0px 0px;
		    padding:0px 0px 0px 0px;
		}
	</style>