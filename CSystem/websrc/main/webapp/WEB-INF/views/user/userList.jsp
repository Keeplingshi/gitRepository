<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />

<div class="breadcrumbs" id="userListToolbar">
	<div class="nav-search" id="nav-search">
		<span class="input-icon"> <input type="text"
			placeholder="Search ..." class="nav-search-input"
			id="nav-search-input" autocomplete="off" /> <i
			class="icon-search nav-search-icon"></i>
		</span>
	</div>
</div>
<div class="table-responsive">
	<table id="sample-table-1"
		class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="center"><label> <input id="theadCheckbox"
						type="checkbox" class="ace" /> <span class="lbl"></span>
				</label></th>
				<th>账号</th>
				<th>权限</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${userList }" var="userDomain">
				<tr>
					<td class="center"><label> <input id="tbodyCheckbox1"
							type="checkbox" class="ace" /> <span class="lbl"></span>
					</label></td>

					<td>${userDomain.username }</td>
					<td>${userDomain.authority}</td>

					<td style="width: 260px"><input type="button"
						class="btn_list_view" value="查看" /> <input type="button"
						class="btn_list_update" value="修改" /> <input type="button"
						class="btn_list_delete" value="删除" /></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>
</div>
<!-- /span -->
</div>
<!-- /row -->
</div>

<input type="button" id="checkButton" value="删除" />

<script type="text/javascript">
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
