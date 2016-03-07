<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 学生列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/font-awesome.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/ace/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<div id="dialog_content_page">
<form id="dialogFormId" action="${pageContext.request.contextPath}/instructor/discipline/studentDiscipline" method="post">
	<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
	<input type="hidden" id="classId" name="classId" value="${classId }" />
	<div class="breadcrumbs" id="studentListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="dialog_query_btn" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="breadcrumbs" id="studentListToolbar">
		
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
		<table id="sample-table-3" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="center" style="width: 80px;">
						
					</th>
					<th style="width: 200px;">学号</th>
					<th>姓名</th>
					<th style="width: 80px;">性别</th>
					<th>班级</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${studentList }" var="studentDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${studentDomain.id }" onclick="check(this)"/> <span class="lbl"></span></label>
						</td>
						<td>${studentDomain.stuId }</td>
						<td>${studentDomain.name }</td>
						<td>${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}</td>
						<td>${studentDomain.classDomain.name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<div id="pageId" style="width: 500px;float: left;">
				<tags:dialogpaged/>
			</div>
			<input id="yesBtn" type="button" class="button button-highlight button-rounded button-small" style="float: right;margin-right: 40px;" value="确定"/>
		</div>
	</div>
	
</form>


<script type="text/javascript">

	//使下拉框默认选择
	$(function(){
		$("#major_select_id option[value='${majorId}']").attr("selected",true);
		$("#class_select_id option[value='${classId}']").attr("selected",true);
	});

 	$("#class_select_id").change(function(){
		var classIdVal=$(this).children('option:selected').val();
		$("#classId").val(classIdVal);
	});
	$("#major_select_id").change(function(){
		var majorIdVal=$(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});

	//查询
	$("#dialog_query_btn").click(function(){
		$("#dialogFormId").ajaxSubmit(function(data){
		 	$("#dialog_content_page").html(data);
		});
	});
	
	$("#yesBtn").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var studentId=null;
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				//学生id
				studentId=checkBox.value;
				
				$.ajax({
					type: "POST",
					async: false,
             		url: "${pageContext.request.contextPath}/instructor/discipline/studentDisciplineExcel/"+studentId,
             		success: function(data){
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
							
							window.location="${pageContext.request.contextPath}/instructor/discipline/"+data+"/downloadDisciplineInfo";
							
						};
                    }
         		});

			}
		}
		if(studentId==null||studentId==''){
			layer.msg('请选择学生',{
				offset: ['260px']
			});
			return;
		}
		
	});
	
	//使checkbox只能选择一个
	function check(obj){
		$('table td input:checkbox').each(function () {
	        if (this != obj){
	        	$(this).attr("checked", false);
	        }else {
	            if ($(this).prop("checked"))
	                $(this).attr("checked", true);
	            else
	                $(this).attr("checked", false);
	        }
	    });
	}
	
	//选择专业，得到班级
	function getClass(major_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/instructor/class/getClassByMajor?major_id='+major_id,
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
</div>