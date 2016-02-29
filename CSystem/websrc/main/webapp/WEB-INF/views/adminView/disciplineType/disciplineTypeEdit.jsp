<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改违纪类型界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="disciplineTypeEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/disciplineType/save" method="post">
	<input type="hidden" id="id" name="id" value="${disciplineTypeDomain.id }">
	<table>
		<tr>
			<td class="lesta-150">违纪类型名称：</td>
			<td class="lestb">
				<input type="text" id="disciplineTypename" name="name" class="input_text_a" value="${disciplineTypeDomain.name }" placeholder="请输入违纪类型" style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"/>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
	$("#saveButton").click(function(){
		
		var disciplineTypenameVal=$("#disciplineTypename").val();
		if(disciplineTypenameVal==null||disciplineTypenameVal==''){
			layer.tips('违纪类型不能为空', '#disciplineTypename');
			return;
		}
		
		var form = $("#disciplineTypeEditFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg("新增成功！", {
					offset: ['260px'],
					time: 1500//1.5s后自动关闭
				});
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg("新增失败！", {
					offset: ['260px'],
					time: 1500//1.5s后自动关闭
				});
			}
		});
		
	});

</script>
