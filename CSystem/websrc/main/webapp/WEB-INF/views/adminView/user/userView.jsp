<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 查看用户界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
	

	<table>
		<tr>
			<td class="lesta-150">账号：</td>
			<td class="lestb">
				${userDomain.username }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">密码：</td>
			<td class="lestb">
				******
			</td>
		</tr>
		<tr>
			<td class="lesta-150">角色：</td>
			<td>
				${userDomain.role.name }
			</td>
		</tr>
		<c:if test="${userDomain.role.authority==1||userDomain.role.authority==2 }">
			<tr>
				<td class="lesta-150">学院：</td>
				<td>
					${userDomain.college.name }
				</td>
			</tr>
			<tr>
				<td class="lesta-150">年级：</td>
				<td>
					${userDomain.grade.grade }
				</td>
			</tr>
		</c:if>
		<c:if test="${userDomain.role.authority==3 }">
			<tr>
				<td class="lesta-150">班级：</td>
				<td>
					${userDomain.classDomain.name }
				</td>
			</tr>
		</c:if>
	</table>
	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});


</script>