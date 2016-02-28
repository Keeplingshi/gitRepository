<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加专业界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="majorAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/instructor/major/save" method="post">
	<input type="hidden" id="collegeId" name="college.id" value="${collegeDomain.id }" />
	<table>
		<tr>
			<td class="lesta-150">专业名称：</td>
			<td class="lestb">
				<input type="text" id="majorname" name="name" class="input_text_a" placeholder="请输入专业名称">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">选择学院：</td>
			<td class="lestb">
				${collegeDomain.name }
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
	$("#saveButton").click(function(){
		
		var majornameVal=$("#majorname").val();
		if(majornameVal==null||majornameVal==''){
			layer.tips('专业名称不能为空', '#majorname');
			return;
		}
		
		var form = $("#majorAddFormId");
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
				layer.msg('添加失败', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
			}
		});
		
	});

</script>