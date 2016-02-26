<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改用户界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="userEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/user/save" method="post">
	<input type="hidden" id="id" name="id" value="${userDomain.id }"/>
	<input type="hidden" id="username" name="username" value="${userDomain.username }"/>
	<input type="hidden" id="password" name="password" value="${userDomain.password }"/>
	<input type="hidden" id="roleId" name="role.id" value="" />
	<table>
		<tr>
			<td class="lesta-150">账号：</td>
			<td class="lestb">
				${userDomain.username }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">密码：</td>
			<td class="lestb">
				******
			</td>
		</tr>
		<tr>
			<td class="lesta-150">角色：</td>
			<td class="lestb">
				<select id="role_select_edit_id" class="select_style">
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
	
	//使权限下拉框默认选择
	$(function(){
		$("#role_select_edit_id option[value='${userDomain.role.id}']").attr("selected",true);
	});

	//下拉框选择后给隐藏域赋值
	$("#role_select_edit_id").change(function(){
		var role_id=$(this).children('option:selected').val();
		$("#roleId").val(role_id);
	});
	
	$("#saveButton").click(function(){
		var form = $("#userEditFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('修改成功', {
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭     
				
			}else{
				layer.msg('修改失败');
			}
		});
		
	});

</script>