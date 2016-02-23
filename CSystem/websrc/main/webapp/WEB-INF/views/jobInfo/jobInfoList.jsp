<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 就业列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/jobInfo/jobInfoSearchList" method="post">

	<div class="breadcrumbs" id="jobInfoListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="jobInfoDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="jobInfoAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="jobInfoQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="center" style="width: 80px;">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th style="width: 120px;">学号</th>
					<th style="width: 80px;">姓名</th>
					<th style="width: 60px;">性别</th>
					<th>班级</th>
					<th>签约状态</th>
					<th>签约单位</th>
					<th>协议书状态</th>
					<th>当前状态</th>
					<th>薪水</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${studentList }" var="studentDomain">
					<tr>
						<td class="center">
							<label> <input type="checkbox" class="ace" value="${jobInfoDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${studentDomain.stuId }</td>
						<td>${studentDomain.name }</td>
						<td>${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}</td>
						<td>${studentDomain.classDomain.name }</td>
						<td>签约状态</td>
						<td>签约单位</td>
						<td>协议书状态</td>
						<td>当前状态</td>
						<td>薪水</td>
						<td>备注</td>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewjobInfo('${jobInfoDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatejobInfo('${jobInfoDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="删除" onclick="deletejobInfo('${jobInfoDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
<%-- 				<c:forEach items="${studentList }" var="studentDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${studentDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${studentDomain.stuId }</td>
						<td>${studentDomain.name }</td>
						<td>${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}</td>
						<td><fmt:formatDate value="${studentDomain.birthday }" type="date"/></td>
						<td>${cusfun:getNameByValueAndType(studentDomain.politicalStatus,"8001")}</td>
						<td>${studentDomain.IDnumber }</td>
						<td>${studentDomain.nativePlace }</td>
						<td>${studentDomain.dormitory }</td>
						<td>${studentDomain.classDomain.grade.grade }</td>
						<td>${studentDomain.classDomain.major.college.name }</td>
						<td>${studentDomain.classDomain.major.name }</td>
						<td>${studentDomain.classDomain.name }</td>
						<td style="overflow:hidden;text-overflow:ellipsis;">${studentDomain.email }</td>
						<td>${studentDomain.telephone }</td>
						<td>${studentDomain.cellphone }</td>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewstudent('${studentDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatestudent('${studentDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="删除" onclick="deletestudent('${studentDomain.id }')"/>
						</td>
					</tr>
				</c:forEach> --%>
			</tbody>
		</table>
		<div id="pageId"><tags:paged /></div>
	</div>
</form>
</div>

<script type="text/javascript">

	//新增学生按钮
	$("#jobInfoAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增学生',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['700px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/jobInfo/jobInfoAdd',
	        end: function(){
				//默认加载学生列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});

</script>
