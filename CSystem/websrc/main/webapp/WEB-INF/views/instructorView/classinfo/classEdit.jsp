<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加学院界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="classEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/instructor/class/save" method="post">
	<input type="hidden" id="id" name="id" value="${classDomain.id }" />
	<input type="hidden" id="gradeId" name="grade.id" value="${classDomain.grade.id }" />
	<input type="hidden" id="majorId" name="major.id" value="${classDomain.major.id }" />
	<table>
		<tr>
			<td class="lesta-150">班级名称：</td>
			<td class="lestb">
				<input type="text" id="classname" name="name" class="input_text_a" placeholder="请输入班级名称" value="${classDomain.name }">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">所属年级：</td>
			<td class="lestb">
				${classDomain.grade.grade }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">学院：</td>
			<td class="lestb">
				${classDomain.major.college.name }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">选择专业：</td>
			<td class="lestb">
				<select id="major_select_add_id" class="select_style">
					<c:forEach items="${majorList }" var="majorDomain">
						<option value="${majorDomain.id }">${majorDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>

	//下拉框默认选择
	$(function(){
		$("#major_select_add_id option[value='${classDomain.major.id}']").attr("selected",true);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#major_select_add_id").change(function(){
		var major_id=$(this).children('option:selected').val();
		$("#majorId").val(major_id);
	});
	
	$("#saveButton").click(function(){
		
		var classnameVal=$("#classname").val();
		var majorIdVal=$("#majorId").val();
		if(classnameVal==null||classnameVal==''){
			layer.tips('班级名称不能为空', '#classname');
			return;
		}
		if(majorIdVal==null||majorIdVal==''){
			layer.tips('请选择专业', '#major_select_add_id');
			return;
		}
		
		var form = $("#classEditFormId");
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
				layer.msg('修改失败', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
			}
		});
		
	});

</script>