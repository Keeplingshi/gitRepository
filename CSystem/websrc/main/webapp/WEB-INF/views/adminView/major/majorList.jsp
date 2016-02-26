<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 专业列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<form id="formId" action="${pageContext.request.contextPath}/admin/major/majorSearchList" method="post">
	<input type="hidden" id="collegeId" name="collegeId" value="" />
	<div class="breadcrumbs" id="majorListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
		
		<label style="margin-left: 30px;">学院：</label>
		<select id="college_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${collegeList }" var="collegeDomain">
				<option value="${collegeDomain.id }">${collegeDomain.name}</option>
			</c:forEach>
		</select>
	
		<input id="majorAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="majorQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th>专业名称</th>
					<th>所属学院</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${majorList }" var="majorDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${majorDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${majorDomain.name }</td>
						<td>${majorDomain.college.name }</td>
						<td style="width: 260px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewmajor('${majorDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatemajor('${majorDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="删除" onclick="deletemajor('${majorDomain.id }')"/>
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
	});
	
 	//下拉框选择后给隐藏域赋值
	$("#college_select_id").change(function(){
		var collegeIdVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
	});

	//查询
	$("#majorQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增专业按钮
	$("#majorAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增专业',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['380px' , '280px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/major/majorAdd',
	        end: function(){
				//默认加载专业列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看专业按钮
	function viewmajor(majorId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看专业',
	        shadeClose: true,
	        area : ['380px' , '280px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/major/majorView/'+majorId
	    });
	}

	//list中修改用户按钮
	function updatemajor(majorId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改专业',
	        shadeClose: true,
	        area : ['380px' , '280px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/major/majorEdit/'+majorId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deletemajor(majorId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载专业列表
			$.post("${pageContext.request.contextPath}/admin/major/delete/"+majorId, function(result){
				if(result=='success'){
					//默认加载专业列表
		        	$("#formId").ajaxSubmit(function(data){
		        	 	$("#content_page").html(data);
		    		});
					parent.layer.msg('删除成功', {
	     		        time: 1500//1.5s后自动关闭
	     		    });
				}else{
					layer.msg('删除失败');
				}
			});
		}, function(){
			
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
