<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改就业信息界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

	<table>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				${patyDomain.student.name }
			</td>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				${patyDomain.student.stuId }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				${patyDomain.student.classDomain.name }
			</td>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(patyDomain.student.sex,"8002")}
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">提交入党申请书日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${patyDomain.applicationDate }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">确定积极份子日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${patyDomain.activeDate }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">中党是否通过：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(patyDomain.isPassActive,"8007")} 
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">确定发展对象日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${patyDomain.developDate }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">入党日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${patyDomain.joinpatyDate }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">转正日期：</td>
			<td class="lestb">
				<fmt:formatDate value="${patyDomain.confirmDate }" type="date"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb" colspan="3" rowspan="2">
				<textarea rows="5" cols="50" readonly="readonly" style="margin-top: 20px;">${patyDomain.note }</textarea>
			</td>
		</tr>
	</table>
	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 290px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});


</script>