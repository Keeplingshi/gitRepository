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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validform.js"></script>

<form id="studentEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/monitor/student/save" method="post">
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
				${studentDomain.classDomain.grade.grade }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="stuname" name="name" class="input_text_a" placeholder="请输入学号" value="${studentDomain.name }">
			</td>
			<td class="lesta-150">学院：</td>
			<td class="lestb">
				${studentDomain.classDomain.major.college.name }
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
				${studentDomain.classDomain.major.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">出生日期：</td>
			<td class="lestb">
				<input type="text" name="birthday" class="Wdate" readonly="readonly"  value="<fmt:formatDate value="${studentDomain.birthday }" type="date"/>" onfocus="WdatePicker()" style="width: 150px;height: 30px;"/>
			</td>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				${studentDomain.classDomain.name }
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
				<input type="text" id="stuemail" name="email" class="input_text_a"  datatype="e" ignore="ignore" errormsg="请输入正确邮箱" placeholder="请输入邮箱" value="${studentDomain.email }"/>
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
				<input type="text" id="stuCellphone" name="cellphone" class="input_text_a" datatype="m" ignore="ignore" placeholder="请输入手机号码" errormsg="请输入正确手机号" value="${studentDomain.cellphone }"/>
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
	});

	//表单验证
	$.Tipmsg.r=null;
	
	var showmsg=function(msg,obj){
		layer.tips(msg, obj);
	};
	
	$("#studentEditFormId").Validform({
		tiptype:function(msg,o){
			showmsg(msg,o.obj[0]);
		}
	});
	
	//下拉框选择后给隐藏域赋值
	$("#politicalStatus_select_edit_id").change(function(){
		var politicalStatus_id=$(this).children('option:selected').val();
		$("#politicalStatusId").val(politicalStatus_id);
	});
	
	$("#saveButton").click(function(){
		
		var stunameVal=$("#stuname").val();	//姓名
		var classIdVal=$("#classId").val();	//班级
		var stuemailIdVal=$("#stuemail").val();	//邮箱
		var stuCellphoneIdVal=$("#stuCellphone").val();	//手机号
		
		if(stunameVal==null||stunameVal==''){
			layer.tips('姓名不能为空', '#stuname');
			return;
		}
		if(classIdVal==null||classIdVal==''){
			layer.tips('班级不能为空', '#class_select_edit_id');
			return;
		}
		if(stuemailIdVal!=null&&stuemailIdVal!=''){
			if(!isEmail(stuemailIdVal)){
				layer.tips('请输入正确邮箱', '#stuemail');
				return;
			}
		}
		if(stuCellphoneIdVal!=null&&stuCellphoneIdVal!=''){
			if(!checkMobile(stuCellphoneIdVal)){
				layer.tips('请输入正确手机号', '#stuCellphone');
				return;
			}
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
	
</script>