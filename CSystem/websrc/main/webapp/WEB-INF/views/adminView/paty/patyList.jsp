<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 党建列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/admin/paty/patySearchList" method="post">
	<input type="hidden" id="gradeId" name="gradeId" value="${gradeId }" />
	<input type="hidden" id="collegeId" name="collegeId" value="${collegeId }" />
	<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
	<input type="hidden" id="classId" name="classId" value="${classId }" />
	<input type="hidden" id="sortMode" name="sortMode" value="${sortMode }" />
	<input type="hidden" id="sortValue" name="sortValue" value="${sortValue }" />
	<div class="breadcrumbs" id="patyListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
		
		<!-- <input id="testBtn" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="测试"/> -->
		<!-- <input id="patyExcelToDBButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="导入党建信息"/> -->
		<input id="patyDBToExcelButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="信息导出"/>
		<input id="patyQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="breadcrumbs">
	
		<label style="margin-left: 20px;">年级：</label>
		<select id="grade_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${gradeList }" var="gradeDomain">
				<option value="${gradeDomain.id }">${gradeDomain.grade}</option>
			</c:forEach>
		</select>
		
		<label style="margin-left: 20px;">学院：</label>
		<select id="college_select_id" class="select_style" onchange="getMajor(this.value)">
			<option value="" selected="selected">选择</option>
			<c:forEach items="${collegeList }" var="collegeDomain">
				<option value="${collegeDomain.id }">${collegeDomain.name}</option>
			</c:forEach>
		</select>
		
		<label style="margin-left: 20px;">专业：</label>
		<select id="major_select_id" style="width: 100px;" onchange="getClass(this.value)">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${majorList }" var="majorItem">
				<option value="${majorItem.selectText }">${majorItem.selectValue}</option>
			</c:forEach>
		</select>
	
		<label style="margin-left: 20px;">班级：</label>
		<select id="class_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${classList }" var="classItem">
				<option value="${classItem.selectText }">${classItem.selectValue}</option>
			</c:forEach>
		</select>
	
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th style="width: 100px;">学号</th>
					<th style="width: 80px;">姓名</th>
					<th style="width: 50px;">性别</th>
					<th>班级</th>
					<th>提交入党申请书日期</th>
					<th>确定积极份子日期</th>
					<th>确定发展对象日期</th>
					<th>入党日期</th>
					<th>转正日期</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${patyList }" var="patyDomain">
					<tr>
						<td>${patyDomain.student.stuId }</td>
						<td>${patyDomain.student.name }</td>
						<td>${cusfun:getNameByValueAndType(patyDomain.student.sex,"8002")}</td>
						<td>${patyDomain.student.classDomain.name }</td>
						<td><fmt:formatDate value="${patyDomain.applicationDate }" type="date"/></td>
						<c:if test="${patyDomain.isPassActive=='1'}">
							<td><fmt:formatDate value="${patyDomain.activeDate }" type="date"/></td>
						</c:if>
						<c:if test="${patyDomain.isPassActive!='1'}">
							<td style="color: red;"><fmt:formatDate value="${patyDomain.activeDate }" type="date"/></td>
						</c:if>
						<td><fmt:formatDate value="${patyDomain.developDate }" type="date"/></td>
						<td><fmt:formatDate value="${patyDomain.joinpatyDate }" type="date"/></td>
						<td><fmt:formatDate value="${patyDomain.confirmDate }" type="date"/></td>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewpaty('${patyDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatepaty('${patyDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pageId"><tags:paged /></div>
	</div>
</form>
</div>

<script type="text/javascript">
	
	//使下拉框默认选择
	$(function(){
		$("#grade_select_id option[value='${gradeId}']").attr("selected",true);
		$("#college_select_id option[value='${collegeId}']").attr("selected",true);
		$("#major_select_id option[value='${majorId}']").attr("selected",true);
		$("#class_select_id option[value='${classId}']").attr("selected",true);
	});
	
		//下拉框选择后给隐藏域赋值
	$("#class_select_id").change(function(){
		var classIdVal=$(this).children('option:selected').val();
		$("#classId").val(classIdVal);
	});
	$("#major_select_id").change(function(){
		var majorIdVal=$(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});
	$("#grade_select_id").change(function(){
		var gradeIdVal=$(this).children('option:selected').val();
		$("#gradeId").val(gradeIdVal);
	});
	$("#college_select_id").change(function(){
		var collegeIdVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});
	
	//查询
	$("#patyQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});
	
	//党建信息导出
	$("#patyDBToExcelButton").click(function(){
		parent.layer.open({
	        type: 2,
	        title: '导入党建信息',
	        shadeClose: true,
	        area : ['600px' , '350px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/paty/patyDBToExcelView',
	        end: function(){
	        
	        }
	    });
	});
	
/* 	$("#patyExcelToDBButton").click(function(){
		//layer.tips('正在开发','#patyExcelToDBButton');
		parent.layer.open({
	        type: 2,
	        title: '导入党建信息',
	        shadeClose: true,
	        area : ['600px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/paty/patyExcelToDBView',
	        end: function(){
	        
	        }
	    });
		
	});
	 */
	//测试
	$("#testBtn").click(function(){
	
      	$.ajax({
			url:'${pageContext.request.contextPath}/admin/paty/test',
			type:"post",
			error:function(e){
			},
			success:function(data){
				console.info(data);
			}
		}); 
	});
	
	//list中修改党建信息按钮
	function updatepaty(patyId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改党建信息',
	        shadeClose: true,
	        area : ['700px' , '600px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/paty/patyEdit/'+patyId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//list中查看党建信息按钮
	function viewpaty(patyId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看党建信息',
	        shadeClose: true,
	        area : ['700px' , '600px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/paty/patyView/'+patyId
	    });
	}
	
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
 				var major_select=$("#major_select_id");
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
 				var class_select=$("#class_select_id");
				class_select.empty();
				class_select.append('<option value="">'+"全部"+'</option>');
				for(var i=0;i<json.length;i++){
					class_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
		});
	}
	
</script>
