<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加年级界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="gradeAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/grade/save" method="post">
	<table>
		<tr>
			<td class="lesta-150">年级名称：</td>
			<td class="lestb">
				<input type="text" id="gradename" name="grade" class="input_text_a" placeholder="请输入年级" style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"/>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
	$("#saveButton").click(function(){
		
		var gradenameVal=$("#gradename").val();
		if(gradenameVal==null||gradenameVal==''){
			layer.tips('年级不能为空', '#gradename');
			return;
		}
		
		var form = $("#gradeAddFormId");
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