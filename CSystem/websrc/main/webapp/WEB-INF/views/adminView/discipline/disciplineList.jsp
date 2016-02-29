<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 违纪类型列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<form id="formId" action="${pageContext.request.contextPath}/admin/discipline/disciplineList" method="post">
	<div class="breadcrumbs" id="disciplineListToolbar">
		<input id="disciplineAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th>姓名</th>
					<th>违纪名称</th>
					<th>时间</th>
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
						<td>${disciplineDomain.name }</td>
	
						<td style="width: 260px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewdiscipline('${disciplineDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatediscipline('${disciplineDomain.id }')"/>
							<input type="button" class="btn_list_delete" value="删除" onclick="deletediscipline('${disciplineDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form>

<script type="text/javascript">

	//新增违纪类型按钮
	$("#disciplineAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增违纪类型',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['400px' , '240px'],
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
	        title: '查看违纪类型',
	        shadeClose: true,
	        area : ['340px' , '200px'],
	        offset: ['150px'],
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
	        area : ['380px' , '300px'],
	        offset: ['150px'],
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

	//点击表格标题栏，选中所有checkbox框
	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

	</script>
