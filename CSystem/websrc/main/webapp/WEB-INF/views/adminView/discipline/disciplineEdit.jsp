<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加违纪类型界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

<form id="disciplineEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/discipline/save" method="post">
<input type="hidden" id="id" name="id" value="${disciplineDomain.id }"/>
	<input type="hidden" id="stuId" name="student.id" value="${disciplineDomain.student.id }"/>
	<input type="hidden" id="disciplineTypeId" name="disciplineType.id" value="${disciplineDomain.disciplineType.id }"/>
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
			<td class="lesta-150">违纪类型：</td>
			<td class="lestb">
				<select id="disciplineType_select_choose_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${disciplineTypeList }" var="disciplineTypeDomain">
						<option value="${disciplineTypeDomain.id }">${disciplineTypeDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				${disciplineDomain.student.classDomain.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">时间：</td>
			<td class="lestb">
				<input type="text" name="time" class="Wdate" readonly="readonly" placeholder="请输入时间" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" style="width: 150px;height: 30px;cursor: pointer;" value="<fmt:formatDate value="${disciplineDomain.time }" type="date"/>"/> 
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb" colspan="3" rowspan="2">
				<textarea rows="5" cols="50" id="note" name="note" style="margin-top: 20px;">${disciplineDomain.note }</textarea>
			</td>
		</tr>
	</table>
<%-- 	<c:if test="${disciplineDomain.disciplineType.id=='1' }"> --%>
		<div id="course_div" style="display: none;">
			<table>
			<tr>
				<td class="lesta-150">课程名称：</td>
				<td class="lestb">
					<input type="text" id="courseName" name="courseName" class="input_text_a" placeholder="请输入课程名称" value="${disciplineDomain.courseName }"/>
				</td>
			</tr>
			<tr>
				<td class="lesta-150">任课教师：</td>
				<td class="lestb">
					<input type="text" id="courseTeacher" name="courseTeacher" class="input_text_a" placeholder="请输入任课教师" value="${disciplineDomain.courseTeacher }"/>
				</td>
			</tr>
			</table>
		</div>	
<%-- 	</c:if> --%>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 240px;" value="确定"/>
</form>

<script>
	
	$(function(){
		if("${disciplineDomain.disciplineType.id}"=='1'){
			$("#course_div").show();
		}
		
		$("#disciplineType_select_choose_id option[value='${disciplineDomain.disciplineType.id}']").attr("selected",true);
	});

	
	//违纪类型选择
	$("#disciplineType_select_choose_id").click(function(){
		var disciplineType_id=$(this).children('option:selected').val();
		if(disciplineType_id=='1'){
			$("#course_div").show();
		}else{
			//清空学院，年级值
			$("#course_div").hide();
			$("#courseName").val('');
			$("#courseTeacher").val('');
		}
		
		$("#disciplineTypeId").val(disciplineType_id);
	});
	
	$("#saveButton").click(function(){
		
		var stuIdVal=$("#stuId").val();
		if(stuIdVal==null||stuIdVal==''){
			layer.tips('请选择学生', '#stuname');
			return;
		}
		
		var disciplineTypeVal=$("#disciplineTypeId").val();
		if(disciplineTypeVal==null||disciplineTypeVal==''){
			layer.tips('违纪类型不能为空', '#disciplineType_select_choose_id');
			return;
		}
		
		var form = $("#disciplineEditFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg("修改成功！", {
					offset: ['260px'],
					time: 1500//1.5s后自动关闭
				});
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg("修改失败！", {
					offset: ['260px'],
					time: 1500//1.5s后自动关闭
				});
			}
		});
		
	});

</script>
