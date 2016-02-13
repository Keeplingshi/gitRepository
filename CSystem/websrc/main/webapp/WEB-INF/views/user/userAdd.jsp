<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加用户界面 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="userAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/user/save" method="post">
	<input type="hidden" id="roleId" name="role.id" value="" />
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
						<option value="${roleDomain.id }">${roleDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>

	//下拉框选择后给隐藏域赋值
	$("#role_select_add_id").change(function(){
		var role_id=$(this).children('option:selected').val();
		$("#roleId").val(role_id);
	});
	
	$("#saveButton").click(function(){
		
		var usernameVal=$("#username").val();
		var passwordVal=$("#password").val();
		var roleVal=$("#roleId").val();
		if(usernameVal==null||usernameVal==''){
			layer.tips('用户名不能为空', '#username');
			return;
		}
		if(passwordVal==null||passwordVal==''){
			layer.tips('密码不能为空', '#password');
			return;
		}
		if(roleVal==null||roleVal==''){
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