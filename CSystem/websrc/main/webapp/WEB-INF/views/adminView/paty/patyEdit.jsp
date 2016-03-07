<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改就业信息界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="patyEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/paty/save" method="post">
	<input type="hidden" id="id" name="id" value="${patyDomain.id }">
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
				<input type="text" name="applicationDate" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${patyDomain.applicationDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">确定积极份子日期：</td>
			<td class="lestb">
				<input type="text" name="activeDate" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${patyDomain.activeDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">中党是否通过：</td>
			<td class="lestb">
				<c:if test="${patyDomain.isPassActive==1 }">
					<input type="radio" name="isPassActive" value="1" checked="checked"/>通过
					<input type="radio" name="isPassActive" value="2" />不通过
				</c:if>
				<c:if test="${patyDomain.isPassActive!=1 }">
					<input type="radio" name="isPassActive" value="1" />通过
					<input type="radio" name="isPassActive" value="2" checked="checked"/>不通过
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">确定发展对象日期：</td>
			<td class="lestb">
				<input type="text" name="developDate" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${patyDomain.developDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">入党日期：</td>
			<td class="lestb">
				<input type="text" name="joinpatyDate" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${patyDomain.joinpatyDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150" colspan="2">转正日期：</td>
			<td class="lestb">
				<input type="text" name="confirmDate" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${patyDomain.confirmDate }" type="date"/>" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb" colspan="3" rowspan="2">
				<textarea name="note" rows="5" cols="50" style="margin-top: 20px;">${patyDomain.note }</textarea>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:30px; margin-left: 270px;" value="确定"/>
</form>
<script>
	
	$("#saveButton").click(function(){
		
		var form = $("#patyEditFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('修改成功', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('修改失败',{
					offset: ['260px'],
	     		    time: 1500
	     		});
			}
		});
		
	});

</script>