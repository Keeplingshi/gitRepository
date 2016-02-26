<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 增加学院界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<form id="classAddFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/admin/class/save" method="post">
	<input type="hidden" id="majorId" name="major.id" value="" />
	<input type="hidden" id="gradeId" name="grade.id" value="" />
	<table>
		<tr>
			<td class="lesta-150">班级名称：</td>
			<td class="lestb">
				<input type="text" id="classname" name="name" class="input_text_a" placeholder="请输入班级名称">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">所属年级：</td>
			<td class="lestb">
				<select id="grade_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${gradeList }" var="gradeDomain">
						<option value="${gradeDomain.id }">${gradeDomain.grade}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">选择学院：</td>
			<td class="lestb">
				<select id="college_select_add_id" class="select_style" onchange="getMajor(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${collegeList }" var="collegeDomain">
						<option value="${collegeDomain.id }">${collegeDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">选择专业：</td>
			<td class="lestb">
				<select id="major_select_add_id" class="select_style">
					<option value="" selected="selected">选择</option>
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

	//选择学院，得到专业
	function getMajor(college_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/admin/major/getMajorByCollege?college_id='+college_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var major_select=$("#major_select_add_id");
				major_select.empty();
				major_select.append('<option value="">'+"选择"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
	//下拉框选择后给隐藏域赋值
	$("#major_select_add_id").change(function(){
		var major_id=$(this).children('option:selected').val();
		$("#majorId").val(major_id);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#grade_select_add_id").change(function(){
		var grade_id=$(this).children('option:selected').val();
		$("#gradeId").val(grade_id);
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
		
		var form = $("#classAddFormId");
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