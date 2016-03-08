<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.cb.system.util.DateUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

<div id="pop_content_page">
	<form id="patyCountFormId" action="${pageContext.request.contextPath}/admin/paty/patyExcelSave" method="post">
		<input type="hidden" id="gradeId" name="gradeId" value="${gradeId }" />
		<input type="hidden" id="classId" name="classId" value="${classId }" />
		<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
		<input type="hidden" id="collegeId" name="collegeId" value="${collegeId }" />
		<table>
			<tr style="height: 60px;">
				<td>
					<label style="margin-left: 15px;">年级：</label>
					<select id="grade_paty_select_id" class="select_style">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${gradeList }" var="gradeDomain">
							<option value="${gradeDomain.id }">${gradeDomain.grade}</option>
						</c:forEach>
					</select>
					
					<label style="margin-left: 15px;">学院：</label>
					<select id="college_paty_select_id" class="select_style" onchange="getMajor(this.value)">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${collegeList }" var="collegeDomain">
							<option value="${collegeDomain.id }">${collegeDomain.name}</option>
						</c:forEach>
					</select>
					
					<label style="margin-left: 15px;">专业：</label>
					<select id="major_paty_select_id" class="select_style" onchange="getClass(this.value)">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${majorList }" var="majorItem">
							<option value="${majorItem.selectText }">${majorItem.selectValue}</option>
						</c:forEach>
					</select>	
					
				</td>
			</tr>
			<tr style="height: 60px;">
				<td>
					
					<label style="margin-left: 15px;">班级：</label>
					<select id="class_paty_select_id" class="select_style">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${classList }" var="classItem">
							<option value="${classItem.selectText }">${classItem.selectValue}</option>
						</c:forEach>
					</select>	
					
					
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="patyCountButton" class="button button-primary button-rounded button-small" style="margin-top: 30px;margin-left: 200px;" value="导出党建报表"/>
				</td>
			</tr>
		</table>
		
	</form>

<script>

	//查询按钮
	$("#patyCountButton").click(function() {
		var form = $("#patyCountFormId");
		form.ajaxSubmit(function(data) {
			if(data=='error'){
				layer.msg("遇到未知错误，请重新查询！", {
					offset: ['260px'],
					time: 1500//1.5s后自动关闭
				});
			}else{
				parent.layer.msg('导出成功', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
				window.location="${pageContext.request.contextPath}/admin/paty/"+data+"/downloadPatyExcel";
			};
		});
	});

	//下拉框选择后给隐藏域赋值
	$("#grade_paty_select_id").change(function() {
		var gradeIdVal = $(this).children('option:selected').val();
		$("#gradeId").val(gradeIdVal);
	});

	//下拉框选择后给隐藏域赋值
	$("#class_paty_select_id").change(function() {
		var classIdVal = $(this).children('option:selected').val();
		$("#classId").val(classIdVal);
	});

	//下拉框选择后给隐藏域赋值
	$("#major_paty_select_id").change(function() {
		var majorIdVal = $(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});

	//下拉框选择后给隐藏域赋值
	$("#college_paty_select_id").change(function() {
		var collegeIdVal = $(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});

	//选择学院，得到专业
	function getMajor(college_id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/major/getMajorByCollege?college_id='+ college_id,
			type : 'post',
			success : function(data) {
				var json = new Function("return" + data)();
				var major_select = $("#major_paty_select_id");
				major_select.empty();
				major_select.append('<option value="">' + "全部"+ '</option>');
				for (var i = 0; i < json.length; i++) {
					major_select.append('<option value="'+json[i].selectText+'">'+ json[i].selectValue + '</option>');
				}
			}
		});
	}

	//选择专业，得到班级
	function getClass(major_id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/class/getClassByMajor?major_id='+ major_id,
			type : 'post',
			success : function(data) {
				var json = new Function("return" + data)();
				var class_select = $("#class_paty_select_id");
				class_select.empty();
				class_select.append('<option value="">' + "全部"+ '</option>');
				for (var i = 0; i < json.length; i++) {
					class_select.append('<option value="'+json[i].selectText+'">'+ json[i].selectValue + '</option>');
				}
			}
		});
	}
</script>

</div>
