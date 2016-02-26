<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改学生界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

<form id="studentEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/student/save" method="post">
	<input type="hidden" id="id" name="id" value="${studentDomain.id }"/>
	<input type="hidden" id="stuId" name="stuId" value="${studentDomain.stuId }"/>
	<input type="hidden" id="politicalStatusId" name="politicalStatus" value="${studentDomain.politicalStatus }"/>
	<input type="hidden" id="classId" name="classDomain.id" value="${studentDomain.classDomain.id }"/>
	<input type="hidden" id="isMonitor" name="isMonitor" value="${studentDomain.isMonitor }"/>
	<table>
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				${studentDomain.stuId }
			</td>
			<td class="lesta-150">年级：</td>
			<td class="lestb">
				<select id="grade_select_edit_id" class="select_style">
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
				<input type="text" id="stuname" name="name" class="input_text_a" placeholder="请输入学号" value="${studentDomain.name }">
			</td>
			<td class="lesta-150">学院：</td>
			<td class="lestb">
				<select id="college_select_edit_id" class="select_style" onchange="getMajor(this.value)">
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
				<c:if test="${studentDomain.sex==0 }">
					<input type="radio" name="sex" value="0" checked="checked"/>男
					<input type="radio" name="sex" value="1" />女
				</c:if>
				<c:if test="${studentDomain.sex==1 }">
					<input type="radio" name="sex" value="0" />男
					<input type="radio" name="sex" value="1" checked="checked"/>女
				</c:if>
			</td>
			<td class="lesta-150">专业：</td>
			<td class="lestb">
				<select id="major_select_edit_id" class="select_style" onchange="getClass(this.value)">
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
				<input type="text" name="birthday" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${studentDomain.birthday }" type="date"/>" onfocus="WdatePicker()" style="width: 150px;height: 30px;"/>
			</td>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				<select id="class_select_edit_id" class="select_style">
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
				<select id="politicalStatus_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${politicalStatusList }" var="politicalStatusDomain">
						<option value="${politicalStatusDomain.id }">${politicalStatusDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">电子邮件：</td>
			<td class="lestb">
				<input type="text" id="stuemail" name="email" class="input_text_a" placeholder="请输入邮箱" value="${studentDomain.email }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证号：</td>
			<td class="lestb">
				<input type="text" id="stuIDnumber" name="IDnumber" class="input_text_a" placeholder="请输入身份证号" value="${studentDomain.IDnumber }"/>
			</td>
			<td class="lesta-150">联系电话：</td>
			<td class="lestb">
				<input type="text" id="stuTelephone" name="telephone" class="input_text_a" placeholder="请输入联系电话" value="${studentDomain.telephone }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">籍贯：</td>
			<td class="lestb">
				<input type="text" id="stuNativePlace" name="nativePlace" class="input_text_a" placeholder="请输入籍贯" value="${studentDomain.nativePlace }"/>
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="stuCellphone" name="cellphone" class="input_text_a" placeholder="请输入手机号码" value="${studentDomain.cellphone }"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">宿舍号：</td>
			<td class="lestb">
				<input type="text" id="stuDormitory" name="dormitory" class="input_text_a" placeholder="请输入宿舍号" value="${studentDomain.dormitory }"/>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 300px;" value="确定"/>
</form>

<script>

	$(function(){
		$("#politicalStatus_select_edit_id option[value='${studentDomain.politicalStatus}']").attr("selected",true);
		$("#class_select_edit_id option[value='${studentDomain.classDomain.id}']").attr("selected",true);
		$("#college_select_edit_id option[value='${studentDomain.classDomain.major.college.id}']").attr("selected",true);
		$("#major_select_edit_id option[value='${studentDomain.classDomain.major.id}']").attr("selected",true);
		$("#grade_select_edit_id option[value='${studentDomain.classDomain.grade.id}']").attr("selected",true);
	});

	//下拉框选择后给隐藏域赋值
	$("#politicalStatus_select_edit_id").change(function(){
		var politicalStatus_id=$(this).children('option:selected').val();
		$("#politicalStatusId").val(politicalStatus_id);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#class_select_edit_id").change(function(){
		var class_id=$(this).children('option:selected').val();
		$("#classId").val(class_id);
	});
	
	$("#saveButton").click(function(){
		
		var stunameVal=$("#stuname").val();	//姓名
		var classIdVal=$("#classId").val();	//班级
		if(stunameVal==null||stunameVal==''){
			layer.tips('姓名不能为空', '#stuname');
			return;
		}
		if(classIdVal==null||classIdVal==''){
			layer.tips('班级不能为空', '#class_select_edit_id');
			return;
		}
		var form = $("#studentEditFormId");
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

	//选择学院，得到专业
	function getMajor(college_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/admin/major/getMajorByCollege?college_id='+college_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var major_select=$("#major_select_edit_id");
				major_select.empty();
				major_select.append('<option value="">'+"选择"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
	//选择专业，得到班级
	function getClass(major_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/admin/class/getClassByMajor?major_id='+major_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var class_select=$("#class_select_edit_id");
				class_select.empty();
				class_select.append('<option value="">'+"选择"+'</option>');
				for(var i=0;i<json.length;i++){
					class_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
</script>