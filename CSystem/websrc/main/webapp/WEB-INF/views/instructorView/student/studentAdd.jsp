<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加学生界面 -->

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

<form id="studentAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/instructor/student/save" method="post">
	<input type="hidden" id="politicalStatusId" name="politicalStatus" value=""/>
	<input type="hidden" id="classId" name="classDomain.id" value=""/>
	<table>
		<tr>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				<input type="text" id="stuId" name="stuId" class="input_text_a" placeholder="请输入学号">
			</td>
			<td class="lesta-150">年级：</td>
			<td class="lestb">
				${userDomain.grade.grade }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				<input type="text" id="stuname" name="name" class="input_text_a" placeholder="请输入姓名">
			</td>
			<td class="lesta-150">学院：</td>
			<td class="lestb">
				${userDomain.college.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">性别：</td>
			<td class="lestb">
				<input type="radio" name="sex" value="0" checked="checked"/>男
				<input type="radio" name="sex" value="1" />女
			</td>
			<td class="lesta-150">专业：</td>
			<td class="lestb">
				<select id="major_select_add_id" class="select_style" onchange="getClass(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${majorList }" var="majorItem">
						<option value="${majorItem.selectText }">${majorItem.selectValue}</option>
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
					<c:forEach items="${classList }" var="classItem">
						<option value="${classItem.selectText }">${classItem.selectValue }</option>
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
				<input type="text" id="stuemail" name="email" class="input_text_a" datatype="e" ignore="ignore" placeholder="请输入邮箱" errormsg="请输入正确邮箱" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">身份证号：</td>
			<td class="lestb">
				<input type="text" id="stuIDnumber" name="IDnumber" class="input_text_a" placeholder="请输入身份证号" />
			</td>
			<td class="lesta-150">教学班级：</td>
			<td class="lestb">
				<input type="text" id="stuteachClass" name="teachClass" class="input_text_a" placeholder="请输入教学班级" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">籍贯：</td>
			<td class="lestb">
				<input type="text" id="stuNativePlace" name="nativePlace" class="input_text_a" placeholder="请输入籍贯" />
			</td>
			<td class="lesta-150">手机号码：</td>
			<td class="lestb">
				<input type="text" id="stuCellphone" name="cellphone" class="input_text_a" datatype="m" ignore="ignore" placeholder="请输入手机号码" errormsg="请输入正确手机号" />
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

	//表单验证
	$.Tipmsg.r=null;
	
	var showmsg=function(msg,obj){
		layer.tips(msg, obj);
	};
	
	$("#studentAddFormId").Validform({
		tiptype:function(msg,o){
			showmsg(msg,o.obj[0]);
		}
	});

	//下拉框选择后给隐藏域赋值
	$("#politicalStatus_select_add_id").change(function(){
		var politicalStatus_id=$(this).children('option:selected').val();
		$("#politicalStatusId").val(politicalStatus_id);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#class_select_add_id").change(function(){
		var class_id=$(this).children('option:selected').val();
		$("#classId").val(class_id);
	});
	
	$("#saveButton").click(function(){
		
		var stuIdVal=$("#stuId").val();		//学号
		var stunameVal=$("#stuname").val();	//姓名
		var classIdVal=$("#classId").val();	//班级
		var stuemailIdVal=$("#stuemail").val();	//邮箱
		var stuCellphoneIdVal=$("#stuCellphone").val();	//手机号
		
		if(stuIdVal==null||stuIdVal==''){
			layer.tips('姓名不能为空', '#stuId');
			return;
		}

		if(stunameVal==null||stunameVal==''){
			layer.tips('学号不能为空', '#stuname');
			return;
		}
		
		if(classIdVal==null||classIdVal==''){
			layer.tips('班级不能为空', '#class_select_add_id');
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
		
		var form = $("#studentAddFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('添加成功', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('新增失败',{
					offset: ['260px']
				});
			}
		});
		
	});
	
	//选择专业，得到班级
	function getClass(major_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/instructor/class/getClassByMajor?major_id='+major_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var class_select=$("#class_select_add_id");
				class_select.empty();
				class_select.append('<option value="">'+"选择"+'</option>');
				for(var i=0;i<json.length;i++){
					class_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
</script>