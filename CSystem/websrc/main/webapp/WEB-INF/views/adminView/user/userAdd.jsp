<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加用户界面 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="userAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/user/save" method="post">
	<input type="hidden" id="roleId" name="role.id" />
	<input type="hidden" id="roleAuthority" />
	<input type="hidden" id="collegeId" name="college.id" />
	<input type="hidden" id="gradeId" name="grade.id" />
	<table>
		<tr>
			<td class="lesta-150">账号：</td>
			<td class="lestb">
				<input type="text" id="username" name="username" class="input_text_a" placeholder="请输入账号">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">密码：</td>
			<td class="lestb">
				<input type="password" id="password" name="password" class="input_text_a" placeholder="请输入密码">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">角色：</td>
			<td class="lestb">
				<select id="role_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${roleList }" var="roleDomain">
						<c:if test="${roleDomain.authority!=3 }">
							<option value="${roleDomain.id }_${roleDomain.authority }">${roleDomain.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<div id="college_div" style="display: none">
		<table>
			<tr>
				<td class="lesta-150">学院：</td>
				<td class="lestb">
					<select id="college_select_add_id" class="select_style">
						<option value="" selected="selected">选择</option>
						<c:forEach items="${collegeList }" var="collegeDomain">
							<option value="${collegeDomain.id }">${collegeDomain.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div id="grade_div" style="display: none">
		<table>
			<tr>
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
		</table>
	</div>
	
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>

	//下拉框选择后给隐藏域赋值
	$("#role_select_add_id").change(function(){
		var roleselectVal=$(this).children('option:selected').val();
		var role_id=roleselectVal.split('_')[0];
		var role_authorty=roleselectVal.split('_')[1];
		if(role_authorty=='1'){
			//辅导员
			$("#college_div").show();
			$("#grade_div").show();
		}else if(role_authorty=='2'){
			//老师
			$("#college_div").show();
			$("#grade_div").show();
			
		}else if(role_authorty=='5'){
			//违纪管理员
			$("#college_div").show();
		}else{
			//清空学院，年级值
			$("#college_div").hide();
			$("#grade_div").hide();
			$("#college_select_add_id").val('');
			$("#grade_select_add_id").val('');
			$("#collegeId").val(null);
			$("#gradeId").val(null);
		}
		
		$("#roleId").val(role_id);
		$("#roleAuthority").val(role_authorty);
	});
	
	$("#college_select_add_id").change(function(){
		var collegeselectVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeselectVal);
	});
	
	$("#grade_select_add_id").change(function(){
		var gradeselectVal=$(this).children('option:selected').val();
		$("#gradeId").val(gradeselectVal);
	});
	
	$("#saveButton").click(function(){
		
		var usernameVal=$("#username").val();
		var passwordVal=$("#password").val();
		var roleIdVal=$("#roleId").val();
		var roleAuthorityVal=$("#roleAuthority").val();
		if(roleAuthorityVal=='1'||roleAuthorityVal=='2'){
			//辅导员,老师,判断年级学院是否为空
			var collegeIdVal=$("#collegeId").val();
			var gradeIdVal=$("#gradeId").val();
			if(collegeIdVal==null||collegeIdVal==''){
				layer.tips('请选择学院', '#college_select_add_id');
				return;
			}
			if(gradeIdVal==null||gradeIdVal==''){
				layer.tips('请选择年级', '#grade_select_add_id');
				return;
			}
			
		}else if(roleAuthorityVal=='5'){
			var collegeIdVal=$("#collegeId").val();
			if(collegeIdVal==null||collegeIdVal==''){
				layer.tips('请选择学院', '#college_select_add_id');
				return;
			}
			
		}else{
			
		}
		
		if(usernameVal==null||usernameVal==''){
			layer.tips('用户名不能为空', '#username');
			return;
		}
		if(passwordVal==null||passwordVal==''){
			layer.tips('密码不能为空', '#password');
			return;
		}
		if(roleIdVal==null||roleIdVal==''){
			layer.tips('请选择用户角色', '#role_select_add_id');
			return;
		}
		
		var form = $("#userAddFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('添加成功', {
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('新增失败');
			}
		});
		
	});

</script>