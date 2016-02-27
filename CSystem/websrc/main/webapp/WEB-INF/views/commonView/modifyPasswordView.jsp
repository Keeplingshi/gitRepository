<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改用户界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validform.js"></script>

<form id="modifyFormId" action="${pageContext.request.contextPath}/common/modifuPassword" method="post">
	<table>
		<tr>
			<td class="lesta-150">账号：</td>
			<td class="lestb">
				${userDomain.username }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">原密码：</td>
			<td class="lestb">
				<input type="password" id="old_password" name="oldpassword" class="input_text_a" nullmsg="请输入原密码" placeholder="请输入密码">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">新密码：</td>
			<td class="lestb">
				<input type="password" id="new_password" name="password" class="input_text_a" placeholder="请输入密码" datatype="*6-16" nullmsg="请设置密码" errormsg="密码范围在6~16位之间！">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">确认密码：</td>
			<td class="lestb">
				<input type="password" id="re_password" class="input_text_a" placeholder="请输入密码"  recheck="password" datatype="*6-16" nullmsg="请设置密码" errormsg="两次输入的密码不一致">
			</td>
		</tr>
		
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>

	//表单验证
	$.Tipmsg.r=null;
	
	var showmsg=function(msg,obj){
		layer.tips(msg, obj);
	};
	
	$("#modifyFormId").Validform({
		tiptype:function(msg,o){
			showmsg(msg,o.obj[0]);
		}
	});
	
	$("#saveButton").click(function(){
		var form = $("#modifyFormId");
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
				parent.layer.msg('修改失败', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
			}
		});
		
	});

</script>