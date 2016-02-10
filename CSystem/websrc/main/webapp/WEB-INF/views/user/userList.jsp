<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<div class="breadcrumbs" id="userListToolbar">
	<span class="input-icon" style="margin: 5px;">
		<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> 
		<i class="icon-search nav-search-icon"></i>
	</span>

	<input id="userDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
	<input id="userAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
	<input id="userQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
</div>
<form id="formId" modelAttribute="pageInfo" action="${pageContext.request.contextPath}/user/userList" method="post">
<div class="table-responsive">
	<table id="sample-table-1" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="center">
					<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
				</th>
				<th>账号</th>
				<th>权限</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${userList }" var="userDomain">
				<tr>
					<%-- <input type="hidden" id="userId" value="${userDomain.id }"/> --%>
					<td class="center">
					<c:if test="${userDomain.authority!=0}">
						<label> <input type="checkbox" class="ace" value="${userDomain.id }"/> <span class="lbl"></span></label>
					</c:if>
					</td>
					<td>${userDomain.username }</td>
					<td>${userDomain.authority}</td>

					<td style="width: 260px">
						<input type="button" class="btn_list_view" value="查看" onclick="viewUser('${userDomain.id }')"/> 
						<c:if test="${userDomain.authority!=0}">
							<input type="button" class="btn_list_update" value="修改" onclick="updateUser('${userDomain.id }')"/> 
							<input type="button" class="btn_list_delete" value="删除" onclick="deleteUser('${userDomain.id }')"/>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="pageId"><tags:paged /></div>
</div>
</form>

<script type="text/javascript">

	//新增用户按钮
	$("#userAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增用户',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['380px' , '280px'],
	        content: '${pageContext.request.contextPath}/user/userAdd',
	        end: function(){
				//默认加载用户列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	$("#userDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var userIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				userIds.push(checkBox.value);
			}
		}
		if(userIds.length=='0'){
			layer.msg('请至少选择一个');
			return;
		}
		
		//询问框
		layer.confirm('是否确定删除这些账户？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
			console.info("确定");
			$.ajax({
				url : "${pageContext.request.contextPath}/user/deleteUsers",
				async: false,
				data : {
					"userIds" : userIds
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
	
	//list中修改用户按钮
	function updateUser(userId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改用户',
	        shadeClose: true,
	        area : ['380px' , '280px'],
	        content: '${pageContext.request.contextPath}/user/userEdit/'+userId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//list中查看用户按钮
	function viewUser(userId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看用户',
	        shadeClose: true,
	        area : ['380px' , '280px'],
	        content: '${pageContext.request.contextPath}/user/userView/'+userId
	    });
	}
	
	//删除
	function deleteUser(userId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载用户列表
			$.post("${pageContext.request.contextPath}/user/delete/"+userId, function(result){
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

	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

		
	</script>
