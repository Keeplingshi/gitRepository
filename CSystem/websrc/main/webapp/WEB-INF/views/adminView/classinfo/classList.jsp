<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 班级列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/commonutil-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<form id="formId" action="${pageContext.request.contextPath}/admin/class/classSearchList" method="post">
	<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
	<input type="hidden" id="collegeId" name="collegeId" value="${collegeId }" />
	<div class="breadcrumbs" id="classListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>

		<label style="margin-left: 30px;">学院：</label>
		<select id="college_select_id" style="width: 100px;" onchange="getMajor(this.value)">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${collegeList }" var="collegeDomain">
				<option value="${collegeDomain.id }">${collegeDomain.name}</option>
			</c:forEach>
		</select>
		
		<label style="margin-left: 30px;">专业：</label>
		<select id="major_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${majorList }" var="majorItem">
				<option value="${majorItem.selectText }">${majorItem.selectValue}</option>
			</c:forEach>
		</select>
	
		<input id="classDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="classAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="classQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th>班级名称</th>
					<th>班长</th>
					<th>所属专业</th>
					<th>所属学院</th>
					<th>所属年级</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${classList }" var="classDomain">
					<tr>
						<td class="center">
							<label> <input type="checkbox" class="ace" value="${classDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${classDomain.name }</td>
						<td>${cusfun:getMonitorNameByClassId(classDomain.id)}</td>
						<td>${classDomain.major.name }</td>
						<td>${classDomain.major.college.name }</td>
						<td>${classDomain.grade.grade }</td>
						<td style="width: 330px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewclass('${classDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updateclass('${classDomain.id }')"/>  
							<input type="button" class="btn_list_techset" value="设置班长" onclick="setmonitor('${classDomain.id }')"/>
							<input type="button" class="btn_list_delete" value="删除" onclick="deleteclass('${classDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pageId"><tags:paged /></div>
	</div>
</form>

<script type="text/javascript">

	//使权限下拉框默认选择
	$(function(){
		$("#college_select_id option[value='${collegeId}']").attr("selected",true);
		$("#major_select_id option[value='${majorId}']").attr("selected",true);
	});
	
 	//下拉框选择后给隐藏域赋值
	$("#major_select_id").change(function(){
		var majorIdVal=$(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});
	$("#college_select_id").change(function(){
		var collegeIdVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});

	//查询
	$("#classQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增班级按钮
	$("#classAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增班级',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['380px' , '300px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/class/classAdd',
	        end: function(){
				//默认加载班级列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看班级按钮
	function viewclass(classId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看班级',
	        shadeClose: true,
	        area : ['380px' , '360px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/class/classView/'+classId
	    });
	}

	//list中修改用户按钮
	function updateclass(classId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改班级',
	        shadeClose: true,
	        area : ['380px' , '300px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/class/classEdit/'+classId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deleteclass(classId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载班级列表
			$.post("${pageContext.request.contextPath}/admin/class/delete/"+classId, function(result){
				if(result=='success'){
					//默认加载班级列表
		        	$("#formId").ajaxSubmit(function(data){
		        	 	$("#content_page").html(data);
		    		});
					parent.layer.msg('删除成功', {
						offset: ['260px'],
	     		        time: 1500//1.5s后自动关闭
	     		    });
				}else{
					layer.msg('删除失败');
				}
			});
		}, function(){
			
		});
		
	}
	
	function setmonitor(classId){
	    parent.layer.open({
	        type: 2,
	        title: '设置班长',
	        shadeClose: true,
	        area : ['380px' , '300px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/class/setmonitorView/'+classId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	
	//多选删除
	$("#classDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var classIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				classIds.push(checkBox.value);
			}
		}
		if(classIds.length=='0'){
			layer.msg('请至少选择一个');
			return;
		}
		
		//询问框
		layer.confirm('是否确定删除这些班级？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
			console.info("确定");
			$.ajax({
				url : "${pageContext.request.contextPath}/admin/class/deleteClasses",
				async: false,
				data : {
					"classIds" : classIds
				},
				dataType : "text",
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.msg('删除失败');
                },
				success : function(result) {
					if(result=='success'){
						//默认加载用户列表
			        	$("#formId").ajaxSubmit(function(data){
			        	 	$("#content_page").html(data);
			    		});
						parent.layer.msg('删除成功', {
		     		        time: 1500//1.5s后自动关闭
		     		    });
					}else{
						layer.msg('删除失败');
					}
				}
			});
			
		}, function(){
			
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
	
	//点击表格标题栏，选中所有checkbox框
	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

		
</script>
