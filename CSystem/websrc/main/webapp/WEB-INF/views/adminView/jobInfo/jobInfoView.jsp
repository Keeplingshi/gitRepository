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
				${jobInfoDomain.student.name }
			</td>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				${jobInfoDomain.student.stuId }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				${jobInfoDomain.student.classDomain.name }
			</td>
			<td class="lesta-150">当前状态：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(jobInfoDomain.nowState,"8005")}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">签约状态：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(jobInfoDomain.contractStatus,"8003")}
			</td>
			<td class="lesta-150">协议书状态：</td>
			<td class="lestb">
				${cusfun:getNameByValueAndType(jobInfoDomain.protocalState,"8004")}
			</td>
		</tr>
		<tr>
			<td class="lesta-150">签约单位：</td>
			<td class="lestb" colspan="3">
				${jobInfoDomain.company }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">薪水/月：</td>
			<td class="lestb">
				${jobInfoDomain.salary }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">派遣地址：</td>
			<td class="lestb" colspan="3">
				${jobInfoDomain.city }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb" colspan="3" rowspan="2">
				<textarea rows="5" cols="50" readonly="readonly" style="margin-top: 20px;">${jobInfoDomain.note }</textarea>
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