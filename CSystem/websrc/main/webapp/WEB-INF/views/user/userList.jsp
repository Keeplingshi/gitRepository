<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />


<div class="breadcrumbs" id="userListToolbar">
	<span class="input-icon" style="margin: 5px"> 
		<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> 
		<i class="icon-search nav-search-icon"></i>
	</span>
	
	<input id="userDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
	<input id="userEditButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="修改"/>
	<input id="userAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
</div>
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
					<td class="center">
						<label> <input id="tbodyCheckbox1" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</td>
					<td>${userDomain.username }</td>
					<td>${userDomain.authority}</td>

					<td style="width: 260px">
						<input type="button" class="btn_list_view" value="查看" /> 
						<input type="button" class="btn_list_update" value="修改" /> 
						<input type="button" class="btn_list_delete" value="删除" />
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>

<input type="button" id="checkButton" value="选中" />

<script type="text/javascript">

	$("#userAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增用户',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['380px' , '280px'],
	        content: '${pageContext.request.contextPath}/user/userAdd'
	    });
	});

		jQuery(function($) {
			//checkbox选择全部
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
					//console.info($(this));
				});
				//console.info(that.id);
			});
		});
		
		//checkbox点击事件
		$("tbody input:checkbox").on('click',function(){
			
			//console.info($(this)[0].id);
		});
		
		//判断checkbox是否被选中
		$("#checkButton").click(function(){
			
			var checkBoxs=$("table tbody input:checkbox");
			for(var i=0;i<checkBoxs.length;i++)
			{
				var checkBox=checkBoxs[i];
				if(checkBox.checked){
					console.info(checkBox.id);
				}
			}
			
		});
	</script>
