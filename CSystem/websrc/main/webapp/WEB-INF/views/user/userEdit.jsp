<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改用户界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
	

<style type="text/css">
	
	.add {
		margin-right: 3px;
	    margin-top: 2px;
	    border-radius: 3px;
	    width: 30px;
	    height: 24px;
	    float: left;
	    border: 1px solid #ccc;
	    background: #eee;
	    cursor: pointer;
	}
	.jian{
		margin-left: -5px;
	    margin-top: 2px;
	    border-radius: 3px;
	    width: 30px;
	    height: 24px;
	    float: none;
	    border: 1px solid #ccc;
	    background: #eee;
	    cursor: pointer;
	}
</style>

<form id="userEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/user/save" method="post">
	<input type="hidden" id="id" name="id" value="${userDomain.id }"/>
	<input type="hidden" id="id" name="username" value="${userDomain.username }"/>
	<input type="hidden" id="id" name="password" value="${userDomain.password }"/>
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
			<td class="lesta-150">权限：</td>
			<td id="authority_td" class="lestb">
				<input type="button" id="add" class="add" value="+" />
				<input type="text" id="weight" name="authority" class="input_text_b" maxlength="3" value="${userDomain.authority }" />
				<input type="button" id="jian" class="jian" value="-" />
			</td>
		</tr>
		
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
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

	$("#add").click(function() {
		var n = $("#weight").val();
		var num = parseInt(n) + 1;
		if (num == 5) {
			return
		}
		$("#weight").val(num);
	});
	$("#jian").click(function() {
		var n = $("#weight").val();
		var num = parseInt(n) - 1;
		if (num == 0) {
			return
		}
		$("#weight").val(num);
	});
	
	$("#weight").blur(function() {
		if(isNaN(this.value)){
			$("#weight").val(1);
		}
	});


</script>