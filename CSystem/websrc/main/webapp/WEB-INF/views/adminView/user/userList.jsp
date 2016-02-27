<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 用户列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<form id="formId" modelAttribute="pageInfo" action="${pageContext.request.contextPath}/admin/user/userSearchList" method="post">
	<input type="hidden" id="roleId" name="roleId" value="" />
	<div class="breadcrumbs" id="userListToolbar">
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
		
		<label style="margin-left: 30px;">角色：</label>
		<select id="role_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${roleList }" var="roleDomain">
				<option value="${roleDomain.id }">${roleDomain.name}</option>
			</c:forEach>
		</select>
		
		<input id="userDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="userAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="userQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th>账号</th>
					<th>角色</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${userList }" var="userDomain">
					<tr>
						<%-- <input type="hidden" id="userId" value="${userDomain.id }"/> --%>
						<td class="center">
						<c:if test="${userDomain.role.authority!=0}">
							<label> <input type="checkbox" class="ace" value="${userDomain.id }"/> <span class="lbl"></span></label>
						</c:if>
						</td>
						<td>${userDomain.username }</td>
						<td>${userDomain.role.name}</td>
	
						<td style="width: 260px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewUser('${userDomain.id }')"/> 
							<c:if test="${userDomain.role.authority!=0}">
								<%-- <input type="button" class="btn_list_update" value="修改" onclick="updateUser('${userDomain.id }')"/>  --%>
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

	//使权限下拉框默认选择
 	$(function(){
		$("#role_select_id option[value='${roleId}']").attr("selected",true);
	}); 

 	//下拉框选择后给隐藏域赋值
	$("#role_select_id").change(function(){
		var roleIdVal=$(this).children('option:selected').val();
		$("#roleId").val(roleIdVal);
	});
	
 	//查询
	$("#userQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
    	 	$("#content_page").html(data);
		});
	});

	//新增用户按钮
	$("#userAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增用户',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['380px' , '340px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/user/userAdd',
	        end: function(){
				//默认加载用户列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//多选删除
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
				url : "${pageContext.request.contextPath}/admin/user/deleteUsers",
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
	
/* 	//list中修改用户按钮
	function updateUser(userId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改用户',
	        shadeClose: true,
	        area : ['380px' , '280px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/user/userEdit/'+userId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	} */
	
	//list中查看用户按钮
	function viewUser(userId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看用户',
	        shadeClose: true,
	        area : ['380px' , '340px'],
	        offset: ['150px'],
	        content: '${pageContext.request.contextPath}/admin/user/userView/'+userId
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
			$.post("${pageContext.request.contextPath}/admin/user/delete/"+userId, function(result){
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
