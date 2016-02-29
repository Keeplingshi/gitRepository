<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/webuploader/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/webuploader/csystem/studentexcelupload.js"></script>

<input type="hidden" id="classExcelViewId" value="" />
<table>
	<tr style="height: 60px;">
		<td>
		<a href="${pageContext.request.contextPath}/admin/student/downloadStudentExcel"><input type="button" id="excelDownload" class="button button-primary button-rounded button-small" value="模板下载"/></a>  
		</td>
	</tr>
	<tr style="height: 60px;">
		<td>
			<label style="margin-left: 15px;">学院：</label>
			<select id="college_excelView_select_id" class="select_style" onchange="getMajor(this.value)">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${collegeList }" var="collegeDomain">
					<option value="${collegeDomain.id }">${collegeDomain.name}</option>
				</c:forEach>
			</select>		
			
			<label style="margin-left: 15px;">专业：</label>
			<select id="major_excelView_select_id" class="select_style" onchange="getClass(this.value)">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${majorList }" var="majorItem">
					<option value="${majorItem.selectText }">${majorItem.selectValue}</option>
				</c:forEach>
			</select>	
			
			<label style="margin-left: 15px;">班级：</label>
			<select id="class_excelView_select_id" class="select_style">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${classList }" var="classItem">
					<option value="${classItem.selectText }">${classItem.selectValue}</option>
				</c:forEach>
			</select>	
		</td>
	</tr>
  	<tr style="height: 60px;">
		<td>
			<div id="thelist">
				仅支持xls类型文件，请下载模板后上传
			</div>	
			<div id="theclassinfo">
			</div>	
		</td>
	</tr>
  	<tr style="height: 60px;">
		<td>
			<div id="uploader">
			    <div class="btns">
			        <div id="picker">选择文件</div>
			    </div>
			</div>
		</td>
	</tr>
	<tr style="height: 60px;">
		<td>
			<input type="button" id="ctlBtn" class="button button-primary button-rounded button-small" value="开始上传"/>
		</td>
	</tr> 
</table>


<script>

	//下拉框选择后给隐藏域赋值
	$("#class_excelView_select_id").change(function(){
		$("#theclassinfo").empty();
		var classExcelViewIdVal=$(this).children('option:selected').val();
		$("#classExcelViewId").val(classExcelViewIdVal);
	});

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
				var major_select=$("#major_excelView_select_id");
				major_select.empty();
				major_select.append('<option value="">'+"全部"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
	//选择专业，得到班级
	function getClass(major_id)
	{
		$.ajax({
			url:'${pageContext.request.contextPath}/admin/class/getClassByMajor?major_id='+major_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
				var class_select=$("#class_excelView_select_id");
				class_select.empty();
				class_select.append('<option value="">'+"全部"+'</option>');
				for(var i=0;i<json.length;i++){
					class_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
</script>