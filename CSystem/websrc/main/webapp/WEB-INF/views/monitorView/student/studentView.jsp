<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>	
<%@ page language="java" import="com.cb.csystem.util.*" %>	

<!-- 增加学生界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

	<table>
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.stuId }
			</td>
			<td class="lesta-150">年级：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.grade.grade }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.name }
			</td>
			<td class="lesta-150">学院：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.major.college.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb" style="width:150px;">
				${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}
			</td>
			<td class="lesta-150">专业：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.major.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb" style="width:150px;">
				<fmt:formatDate value="${studentDomain.birthday }" type="date"/>
			</td>
			<td class="lesta-150">班级：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.classDomain.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">政治面貌：</td>
			<td class="lestb" style="width:150px;">
				${cusfun:getNameByValueAndType(studentDomain.politicalStatus,"8001")}
			</td>
			<td class="lesta-150">电子邮件：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.email }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证号：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.IDnumber }
			</td>
			<td class="lesta-150">联系电话：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.telephone }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">籍贯：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.nativePlace }
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.cellphone }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">宿舍号：</td>
			<td class="lestb" style="width:150px;">
				${studentDomain.dormitory }
			</td>
		</tr>
	</table>

	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});


</script>