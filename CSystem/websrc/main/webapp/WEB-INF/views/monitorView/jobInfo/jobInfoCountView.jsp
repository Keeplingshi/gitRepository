<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<div id="pop_content_page">
	<form id="jobInfoCountFormId" action="${pageContext.request.contextPath}/monitor/jobInfo/jobInfoCountView" method="post">
<%-- 		<input type="hidden" id="classId" name="classId" value="${classId }" />
		<table>
			<tr style="height: 60px;">
				<td>
					<label style="margin-left: 15px;">班级：</label>
					<select id="class_jobCount_select_id" class="select_style">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${classList }" var="classItem">
							<option value="${classItem.selectText }">${classItem.selectValue}</option>
						</c:forEach>
					</select>	
				</td>
			</tr>
			<tr>
				<td>
			<input type="button" id="jobInfoCountButton" class="button button-primary button-rounded button-small" style="margin-top: 30px;margin-left: 300px;" value="查询"/>
				</td>
			</tr>
		</table> --%>

		<table class="table table-bordered table-striped" style="margin-top: 30px;">
			<thead class="thin-border-bottom">
				<tr>
					<c:forEach items="${jobInfoCountList }" var="jobInfoCount">
						<th>${jobInfoCount.selectText }</th>
					</c:forEach>
				</tr>
			</thead>

			<tbody>
				<tr>
					<c:forEach items="${jobInfoCountList }" var="jobInfoCount">
						<td>${jobInfoCount.selectValue }</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
	</form>

<script>

/* 	//使下拉框默认选择
	$(function() {
		$("#class_jobCount_select_id option[value='${classId}']").attr("selected", true);
	}); */

	//查询按钮
	$("#jobInfoCountButton").click(function() {
		var form = $("#jobInfoCountFormId");
		form.ajaxSubmit(function(data) {
			$("#pop_content_page").html(data);
		});
	});

/* 	//下拉框选择后给隐藏域赋值
	$("#class_jobCount_select_id").change(function() {
		var classIdVal = $(this).children('option:selected').val();
		$("#classId").val(classIdVal);
	}); */

</script>

</div>