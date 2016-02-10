<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 查看用户界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
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
			<td>
				${userDomain.authority }
			</td>
		</tr>
		
	</table>
	<input id="closeButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="关闭"/>

<script>
	
	$("#closeButton").click(function(){
		//关闭当前页面
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭     
	});


</script>