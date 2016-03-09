<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改违纪界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

	<table>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				${disciplineDomain.student.name }
			</td>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				${disciplineDomain.student.stuId }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">违纪：</td>
			<td class="lestb">
				${disciplineDomain.disciplineType.name }
			</td>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				${disciplineDomain.student.classDomain.name }
			</td>
		</tr>
		<c:if test="${disciplineDomain.disciplineType.id=='1' }">
			<tr>
				<td class="lesta-150">课程名称：</td>
					<td class="lestb">
						${disciplineDomain.courseName }
					</td>
				</tr>
				<tr>
					<td class="lesta-150">任课教师：</td>
					<td class="lestb">
						${disciplineDomain.courseTeacher }
					</td>
				</tr>		
		</c:if>
		<tr>
			<td class="lesta-150">时间：</td>
			<td class="lestb">
				<fmt:formatDate value="${disciplineDomain.time }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb" colspan="3" rowspan="2">
				<textarea rows="5" cols="50" readonly="readonly" style="margin-top: 20px;">${disciplineDomain.note }</textarea>
			</td>
		</tr>
	</table>
		
	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 240px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});

</script>
