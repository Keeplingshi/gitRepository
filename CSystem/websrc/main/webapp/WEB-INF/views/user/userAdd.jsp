<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加用户界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
	

<style type="text/css">

	.lesta-150 {
		color: #073662;
		height: 40px;
		width: 130px;
		text-align: right;
		padding-right: 4px;
	}
	
	.lestb {
		background: #FFF;
		color: #333;
		height: 40px;
		padding-left: 7px;
		text-align: left;
	}
	
	.input_text_a{
	    color: #858585;
	    background-color: #fff;
	    border: 1px solid #ccc;
	    padding: 5px 4px;
	    line-height: 1.2;
	    font-size: 14px;
	    font-family: inherit;
	    border-radius:5px;
	}
	
	.input_text_b{
		width: 50px;
	    text-align: center;
	    color: #858585;
	    background-color: #fff;
	    border: 1px solid #ccc;
	    padding: 5px 4px;
	    line-height: 1.2;
	    font-size: 14px;
	    font-family: inherit;
	    border-radius:5px;
	}
	
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

<form id="userAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/user/save" method="post">
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
			<td class="lesta-150">权限：</td>
			<td id="authority_td" class="lestb">
				<input type="button" id="add" class="add" value="+" />
				<input type="text" id="weight" name="authority" class="input_text_b" maxlength="3" value="1" />
				<input type="button" id="jian" class="jian" value="-" />
			</td>
		</tr>
		
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
/* 
	$("#authority_td").on("mouseover",function(){
		layer.tips('权限共有4级，', '#authority_td', {
		    tips: [2, '#0FA6D8']
		});
	}); */
	
	$("#saveButton").click(function(){
		
		var usernameVal=$("#username").val();
		var passwordVal=$("#password").val();
		if(usernameVal==null||usernameVal==''){
			layer.tips('用户名不能为空', '#username');
			return;
		}
		if(passwordVal==null||passwordVal==''){
			layer.tips('密码不能为空', '#password');
			return;
		}
		
		var form = $("#userAddFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){
				
				//默认加载用户列表
				$.post("${pageContext.request.contextPath}/user/userList", function(result){
					$("#content_page").html(result);
				});
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