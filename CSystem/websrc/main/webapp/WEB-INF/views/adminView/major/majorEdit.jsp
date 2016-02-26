<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改专业界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="majorEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/major/save" method="post">
	<input type="hidden" id="id" name="id" value="${majorDomain.id }"/>
	<input type="hidden" id="collegeId" name="college.id" value="${majorDomain.college.id }"/>
	<table>
		<tr>
			<td class="lesta-150">专业名称：</td>
			<td class="lestb">
			<input type="text" id="majorname" name="name" value="${majorDomain.name }" class="input_text_a" placeholder="请输入专业名称">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">选择学院：</td>
			<td class="lestb">
				<select id="college_select_add_id" class="select_style">
					<c:forEach items="${collegeList }" var="collegeDomain">
						<option value="${collegeDomain.id }">${collegeDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:20px; margin-left: 140px;" value="确定"/>
</form>

<script>

	//下拉框默认值
	$(function(){
		$("#college_select_add_id option[value='${majorDomain.college.id}']").attr("selected",true);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#college_select_add_id").change(function(){
		var college_id=$(this).children('option:selected').val();
		$("#collegeId").val(college_id);
	});
	
	$("#saveButton").click(function(){
		
		var majornameVal=$("#majorname").val();
		var collegeIdVal=$("#collegeId").val();
		if(majornameVal==null||majornameVal==''){
			layer.tips('专业名称不能为空', '#majorname');
			return;
		}
		if(collegeIdVal==null||collegeIdVal==''){
			layer.tips('请选择学院', '#college_select_add_id');
			return;
		}
		
		var form = $("#majorEditFormId");
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

</script>