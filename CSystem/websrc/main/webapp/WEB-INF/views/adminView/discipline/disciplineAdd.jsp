<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加违纪类型界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="disciplineAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/discipline/save" method="post">
	<input type="hidden" id="stuId" name="student.id" value=""/>
	<input type="hidden" id="disciplineTypeId" name="disciplineType.id" value=""/>
	<table>
		<tr>
			<td class="lesta-150">学生：</td>
			<td class="lestb">
				<input type="text" id="stuname" class="input_text_a" placeholder="请选择学生" readonly="readonly"/>
				<input type="button" id="chooseStudentButton" class="button button-primary button-rounded button-small" value="选择">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">违纪类型：</td>
			<td class="lestb">
				<select id="disciplineType_select_choose_id" class="select_style" onchange="getProtocalState(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${disciplineTypeList }" var="disciplineTypeDomain">
						<option value="${disciplineTypeDomain.id }">${disciplineTypeDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">时间：</td>
			<td class="lestb">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb">
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>
	
	//选择学生
	$("#chooseStudentButton").click(function(){
 	    layer.open({
	        type: 2,
	        title: '选择学生',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['800px' , '650px'],
	        offset: '-40px',
	        content: '${pageContext.request.contextPath}/admin/student/studentChooseView',
	        end: function(){
				//默认加载违纪类型列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    }); 
	});
	
	//违纪类型选择
	$("#disciplineType_select_choose_id").click(function(){
		var disciplineType_id=$(this).children('option:selected').val();
		$("#disciplineTypeId").val(disciplineType_id);
	});
	
	$("#saveButton").click(function(){
		
		var disciplinenameVal=$("#disciplinename").val();
		if(disciplinenameVal==null||disciplinenameVal==''){
			layer.tips('违纪类型不能为空', '#disciplinename');
			return;
		}
		
		var form = $("#disciplineAddFormId");
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
