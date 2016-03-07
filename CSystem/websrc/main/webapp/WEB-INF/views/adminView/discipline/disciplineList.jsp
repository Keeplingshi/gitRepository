<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 违纪类型列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>

<form id="formId" action="${pageContext.request.contextPath}/admin/discipline/disciplineSearchList" method="post">
	<input type="hidden" id="gradeId" name="gradeId" value="${gradeId }" />
	<input type="hidden" id="collegeId" name="collegeId" value="${collegeId }" />
	<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
	<input type="hidden" id="classId" name="classId" value="${classId }" />
	<input type="hidden" id="disciplineTypeId" name="disciplineTypeId" value="${disciplineTypeId }" />
	<input type="hidden" id="sortMode" name="sortMode" value="${sortMode }" />
	<input type="hidden" id="sortValue" name="sortValue" value="${sortValue }" />

	<div class="breadcrumbs" id="disciplineListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
		
		<input id="disciplineReportButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="违纪报表"/>
		<input id="disciplineDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="disciplineAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="disciplineQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="breadcrumbs">
		<label style="margin-left: 20px;">年级：</label>
		<select id="grade_select_id" style="width: 100px;" onchange="getMajor(this.value)">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${gradeList }" var="gradeDomain">
				<option value="${gradeDomain.id }">${gradeDomain.grade}</option>
			</c:forEach>
		</select>

		<label style="margin-left: 20px;">学院：</label>
		<select id="college_select_id" style="width: 100px;" onchange="getMajor(this.value)">
			<option value="" selected="selected">全部</option>
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
	<div class="breadcrumbs">
			<label style="margin-left: 20px;">起始时间：</label>
            <input type="text" id="beginTime" name="beginTime" placeholder="起始时间" class="Wdate" 
            	style="width: 150px;height: 30px;cursor: pointer;" value="<fmt:formatDate value="${beginTime }" pattern="yyyy-MM-dd"/>"
              	onfocus="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})">
            <label style="margin-left: 20px;">结束时间：</label>
            <input type="text" id="endTime" name="endTime" placeholder="结束时间" class="Wdate" 
            	style="width: 150px;height: 30px;cursor: pointer;" value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd"/>"
              	onfocus="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginTime\')}'})">
 	
		<label style="margin-left: 20px;">违纪类型：</label>
		<select id="disciplineType_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${disciplineTypeList }" var="disciplineTypeItem">
				<option value="${disciplineTypeItem.id }">${disciplineTypeItem.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="center" style="width: 80px;">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th style="width: 100px;">学号</th>
					<th style="width: 100px;">姓名</th>
					<th style="width: 100px;">违纪</th>
					<th style="width: 120px;">班级</th>
					<th style="width: 100px;">时间
					<c:choose>
						<c:when test="${sortMode=='asc'&&sortValue=='time' }">
							<img id="img_time_asc" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_up_24.png">
						</c:when>
						<c:when test="${sortMode=='desc'&&sortValue=='time' }">
							<img id="img_time_desc" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_down_24.png">
						</c:when>
						<c:otherwise>
							<img id="img_time" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_24.png">
						</c:otherwise>
					</c:choose>
					</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${disciplineList }" var="disciplineDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${disciplineDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${disciplineDomain.student.stuId }</td>
						<td>${disciplineDomain.student.name }</td>
						<td>${disciplineDomain.disciplineType.name }</td>
						<td>${disciplineDomain.student.classDomain.name }</td>
						<td><fmt:formatDate value="${disciplineDomain.time }" type="date"/></td>
						<td>${disciplineDomain.note }</td>
						<td style="width: 260px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewdiscipline('${disciplineDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatediscipline('${disciplineDomain.id }')"/>
							<input type="button" class="btn_list_delete" value="删除" onclick="deletediscipline('${disciplineDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pageId"><tags:paged /></div>
	</div>
</form>

<script type="text/javascript">

	$("#sample-table-1 thead tr th img").click(function(){
		var sortValueVal=$(this)[0].id.split("_")[1];
		var sortModeVal=$(this)[0].id.split("_")[2];
		$("#sortValue").val(sortValueVal);
		if(sortModeVal=='asc'){
			$("#sortMode").val('desc');
		}else{
			$("#sortMode").val('asc');
		}
		
 		//默认加载学生列表
		$("#formId").ajaxSubmit(function(data){
    	 	$("#content_page").html(data);
		}); 
	});

	//使下拉框默认选择
	$(function(){
		$("#grade_select_id option[value='${gradeId}']").attr("selected",true);
		$("#college_select_id option[value='${collegeId}']").attr("selected",true);
		$("#major_select_id option[value='${majorId}']").attr("selected",true);
		$("#class_select_id option[value='${classId}']").attr("selected",true);
		$("#disciplineType_select_id option[value='${disciplineTypeId}']").attr("selected",true);
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
	$("#college_select_id").change(function(){
		var collegeIdVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});
	$("#grade_select_id").change(function(){
		var gradeIdVal=$(this).children('option:selected').val();
		$("#gradeId").val(gradeIdVal);
	});
	$("#disciplineType_select_id").change(function(){
		var disciplineTypeIdVal=$(this).children('option:selected').val();
		$("#disciplineTypeId").val(disciplineTypeIdVal);
	});
	
	//查询
	$("#disciplineQueryButton").click(function(){
		//加载列表
		$("#formId").ajaxSubmit(function(data){
	    	$("#content_page").html(data);
		});	
	});
	
	//报表
	$("#disciplineReportButton").click(function(){
		parent.layer.open({
	        type: 2,
	        title: '违纪信息统计',
	        shadeClose: true,
	        area : ['600px' , '400px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/discipline/disciplineCountView'
	    });
	});

	//新增违纪类型按钮
	$("#disciplineAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增违纪',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['800px' , '650px'],
	        offset: '130px',
	        content: '${pageContext.request.contextPath}/admin/discipline/disciplineAdd',
	        end: function(){
				//默认加载违纪类型列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看违纪类型按钮
	function viewdiscipline(disciplineId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看违纪',
	        shadeClose: true,
	        area : ['600px' , '470px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/discipline/disciplineView/'+disciplineId
	    });
	}

	//list中修改违纪类型按钮
	function updatediscipline(disciplineId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改违纪类型',
	        shadeClose: true,
	        area : ['600px' , '470px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/admin/discipline/disciplineEdit/'+disciplineId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deletediscipline(disciplineId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
			offset: '200px',
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载违纪类型列表
			$.post("${pageContext.request.contextPath}/admin/discipline/delete/"+disciplineId, function(result){
				if(result=='success'){
					//默认加载违纪类型列表
		        	$("#formId").ajaxSubmit(function(data){
		        	 	$("#content_page").html(data);
		    		});
					parent.layer.msg('删除成功', {
						offset: '200px',
	     		        time: 1500//1.5s后自动关闭
	     		    });
				}else{
					layer.msg('删除失败', {
						offset: '200px',
	     		        time: 1500//1.5s后自动关闭
	     		    });
				}
			});
		}, function(){
			
		});
	}
	
	//多选删除
	$("#disciplineDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var disciplineIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				disciplineIds.push(checkBox.value);
			}
		}
		if(disciplineIds.length=='0'){
			layer.msg('请至少选择一个',{
				offset: ['260px']
			});
			return;
		}
		
		//询问框
		layer.confirm('是否确定删除这些记录？', {
			offset: ['260px'],
		    btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url : "${pageContext.request.contextPath}/admin/discipline/deleteDisciplines",
				async: false,
				data : {
					"disciplineIds" : disciplineIds
				},
				dataType : "text",
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.msg('删除失败',{
						offset: ['260px']
					});
                },
				success : function(result) {
					if(result=='success'){
						//默认加载用户列表
			        	$("#formId").ajaxSubmit(function(data){
			        	 	$("#content_page").html(data);
			    		});
						parent.layer.msg('删除成功', {
							offset: ['260px'],
		     		        time: 1500//1.5s后自动关闭
		     		    });
					}else{
						layer.msg('删除失败',{
							offset: ['260px']
						});
					}
				}
			});
			
		}, function(){
			
		});
	});

	//点击表格标题栏，选中所有checkbox框
	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
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
