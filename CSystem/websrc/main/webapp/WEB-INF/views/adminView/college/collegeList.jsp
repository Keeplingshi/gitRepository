<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 学院列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<form id="formId" action="${pageContext.request.contextPath}/admin/college/collegeList" method="post">
	<div class="breadcrumbs" id="collegeListToolbar">
		<input id="collegeAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th>学院名称</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${collegeList }" var="collegeDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${collegeDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${collegeDomain.name }</td>
	
						<td style="width: 260px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewCollege('${collegeDomain.id }')"/> 
							<input type="button" class="btn_list_delete" value="删除" onclick="deleteCollege('${collegeDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form>

<script type="text/javascript">

	//新增用户按钮
	$("#collegeAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增学院',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['380px' , '180px'],
	        offset: ['240px'],
	        content: '${pageContext.request.contextPath}/admin/college/collegeAdd',
	        end: function(){
				//默认加载用户列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看用户按钮
	function viewCollege(collegeId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看学院',
	        shadeClose: true,
	        area : ['380px' , '180px'],
	        offset: ['240px'],
	        content: '${pageContext.request.contextPath}/admin/college/collegeView/'+collegeId
	    });
	}
	
	//删除
	function deleteCollege(collegeId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载用户列表
			$.post("${pageContext.request.contextPath}/admin/college/delete/"+collegeId, function(result){
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
