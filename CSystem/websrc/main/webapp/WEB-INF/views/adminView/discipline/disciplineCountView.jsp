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
	<form id="disciplineCountFormId" action="${pageContext.request.contextPath}/admin/discipline/disciplineExcel" method="post">
		<input type="hidden" id="gradeId" name="gradeId" value="${gradeId }" />
		<input type="hidden" id="classId" name="classId" value="${classId }" />
		<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
		<input type="hidden" id="collegeId" name="collegeId" value="${collegeId }" />
		<input type="hidden" id="disciplineTypeId" name="disciplineTypeId" value="${disciplineTypeId }" />
		<table>
			<tr style="height: 60px;">
				<td>
					<label style="margin-left: 15px;">年级：</label>
					<select id="grade_discipline_select_id" class="select_style">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${gradeList }" var="gradeDomain">
							<option value="${gradeDomain.id }">${gradeDomain.grade}</option>
						</c:forEach>
					</select>
					
					<label style="margin-left: 15px;">学院：</label>
					<select id="college_discipline_select_id" class="select_style" onchange="getMajor(this.value)">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${collegeList }" var="collegeDomain">
							<option value="${collegeDomain.id }">${collegeDomain.name}</option>
						</c:forEach>
					</select>
					
					<label style="margin-left: 15px;">专业：</label>
					<select id="major_discipline_select_id" class="select_style" onchange="getClass(this.value)">
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
					<select id="class_discipline_select_id" class="select_style">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${classList }" var="classItem">
							<option value="${classItem.selectText }">${classItem.selectValue}</option>
						</c:forEach>
					</select>	
					
					<label style="margin-left: 15px;">违纪类型：</label>
					<select id="type_discipline_select_id" class="select_style">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${disciplineTypeList }" var="disciplineTypeItem">
							<option value="${disciplineTypeItem.id }">${disciplineTypeItem.name}</option>
						</c:forEach>
					</select>	
					
				</td>
			</tr>
			<tr style="height: 60px;">
				<td>
				<label style="margin-left: 20px;">起始时间：</label>
				
	            <input type="text" id="countView_beginTime" name="countView_beginTime" placeholder="起始时间" class="Wdate" 
	            	style="width: 150px;height: 30px;cursor: pointer;" value="<fmt:formatDate value="<%=DateUtil.getDayAfterBeforeToday(DateUtil.getToday(), -7) %>" pattern="yyyy-MM-dd"/>"
	              	onfocus="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'countView_endTime\')}'})">
	            <label style="margin-left: 20px;">结束时间：</label>
	            <input type="text" id="countView_endTime" name="countView_endTime" placeholder="结束时间" class="Wdate" 
	            	style="width: 150px;height: 30px;cursor: pointer;" value="<fmt:formatDate value="<%=DateUtil.getToday() %>" pattern="yyyy-MM-dd"/>"
	              	onfocus="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'countView_beginTime\')}'})">
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="disciplineCountButton" class="button button-primary button-rounded button-small" style="margin-top: 30px;margin-left: 140px;" value="导出违纪报表"/>
					<input type="button" id="studentQueryButton" class="button button-primary button-rounded button-small" style="margin-top: 30px;margin-left: 30px;" value="学生个人违纪报表"/>
				</td>
			</tr>
		</table>
		
	</form>

<script>

	//学生个人违纪查询
	$("#studentQueryButton").click(function(){
	 	parent.layer.open({
	        type: 2,
	        title: '选择学生',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['800px' , '650px'],
	        offset: '100px',
	        moveOut: true,
	        scrollbar: false,
	        content: '${pageContext.request.contextPath}/admin/discipline/studentDiscipline',
	        end: function(){
				
	        }
	    });
	});

	//查询按钮
	$("#disciplineCountButton").click(function() {
		var form = $("#disciplineCountFormId");
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
				window.location="${pageContext.request.contextPath}/admin/discipline/"+data+"/downloadDisciplineInfo";
			};
		});
	});

	//下拉框选择后给隐藏域赋值
	$("#grade_discipline_select_id").change(function() {
		var gradeIdVal = $(this).children('option:selected').val();
		$("#gradeId").val(gradeIdVal);
	});

	//下拉框选择后给隐藏域赋值
	$("#class_discipline_select_id").change(function() {
		var classIdVal = $(this).children('option:selected').val();
		$("#classId").val(classIdVal);
	});

	//下拉框选择后给隐藏域赋值
	$("#major_discipline_select_id").change(function() {
		var majorIdVal = $(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});

	//下拉框选择后给隐藏域赋值
	$("#college_discipline_select_id").change(function() {
		var collegeIdVal = $(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});
	
	$("#type_discipline_select_id").change(function() {
		var disciplineTypeIdVal = $(this).children('option:selected').val();
		$("#disciplineTypeId").val(disciplineTypeIdVal);
	});

	//选择学院，得到专业
	function getMajor(college_id) {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/major/getMajorByCollege?college_id='+ college_id,
			type : 'post',
			success : function(data) {
				var json = new Function("return" + data)();
				var major_select = $("#major_discipline_select_id");
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
				var class_select = $("#class_discipline_select_id");
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
