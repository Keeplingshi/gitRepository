<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 设置班长界面 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="monitorAddFormId" action="${pageContext.request.contextPath}/instructor/class/setmonitor" method="post">
	<input type="hidden" id="classId" name="classId" value="${classId }">
	<table>
		<tr>
			<td class="lesta-150">班长学号：</td>
			<td class="lestb">
				<input type="text" id="monitorId" name="monitorId" class="input_text_a" placeholder="请输入班长学号" value=${monitorId }>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
	$("#saveButton").click(function(){
		var monitorIdVal=$("#monitorId").val();
		if(monitorIdVal==null||monitorIdVal==''){
			layer.tips('班长学号不能为空', '#monitorId');
			return;
		}
		
		var form = $("#monitorAddFormId");
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
				parent.layer.msg('添加失败', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
			}
		});
		
	});

</script>
