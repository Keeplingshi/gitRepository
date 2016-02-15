<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 查看专业界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
	

	<table>
		<tr>
			<td class="lesta-150">专业名称：</td>
			<td class="lestb">
				${majorDomain.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">所属学院：</td>
			<td class="lestb">
				${majorDomain.college.name }
			</td>
		</tr>
		
	</table>
	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});


</script>