<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加学院界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

<form id="classAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/class/save" method="post">
	<input type="hidden" id="majorId" name="major.id" value="" />
	<table>
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				<input type="text" id="stuId" name="stuId" class="input_text_a" placeholder="请输入学号">
			</td>
			<td class="lesta-150">年级：</td>
			<td class="lestb">
				<select id="grade_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${gradeList }" var="gradeDomain">
						<option value="${gradeDomain.id }">${gradeDomain.grade}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="stuname" name="name" class="input_text_a" placeholder="请输入姓名">
			</td>
			<td class="lesta-150">学院：</td>
			<td class="lestb">
				<select id="college_select_add_id" class="select_style" onchange="getMajor(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${collegeList }" var="collegeDomain">
						<option value="${collegeDomain.id }">${collegeDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				<input type="radio" name="sex" value="0" />男
				<input type="radio" name="sex" value="1" />女
			</td>
			<td class="lesta-150">专业：</td>
			<td class="lestb">
				<select id="major_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${majorList }" var="majorDomain">
						<option value="${majorDomain.id }">${majorDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb">
				<input type="text" name="birthday" class="Wdate" readonly="readonly" placeholder="出生日期" onfocus="WdatePicker()" style="width: 150px;height: 30px;"/> 
			</td>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				<select id="class_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${classList }" var="classDomain">
						<option value="${classDomain.id }">${classDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">政治面貌：</td>
			<td class="lestb">
				<select id="politicalStatus_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${politicalStatusList }" var="politicalStatusDomain">
						<option value="${politicalStatusDomain.id }">${politicalStatusDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">电子邮件：</td>
			<td class="lestb">
				<input type="text" id="stuemail" name="email" class="input_text_a" placeholder="请输入邮箱" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证号：</td>
			<td class="lestb">
				<input type="text" id="stuIDnumber" name="IDnumber" class="input_text_a" placeholder="请输入身份证号" />
			</td>
			<td class="lesta-150">联系电话：</td>
			<td class="lestb">
				<input type="text" id="stuTelephone" name="telephone" class="input_text_a" placeholder="请输入联系电话" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">籍贯：</td>
			<td class="lestb">
				<input type="text" id="stuNativePlace" name="nativePlace" class="input_text_a" placeholder="请输入籍贯" />
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="stuCellphone" name="cellphone" class="input_text_a" placeholder="请输入手机号码" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">宿舍号：</td>
			<td class="lestb">
				<input type="text" id="stuDormitory" name="dormitory" class="input_text_a" placeholder="请输入宿舍号" />
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>



</script>