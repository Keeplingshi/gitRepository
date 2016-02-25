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
	
		<input id="jobInfoQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="center" style="width: 60px;">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th style="width: 100px;">学号</th>
					<th style="width: 80px;">姓名</th>
					<th style="width: 60px;">性别</th>
					<th>班级</th>
					<th>签约状态</th>
					<th>签约单位</th>
					<th>协议书</th>
					<th>当前状态</th>
					<th>薪水</th>
					<!-- <th>备注</th> -->
					<th style="width: 120px;">最后修改时间</th>
					<th>操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${jobInfoList }" var="jobInfoDomain">
					<tr style="">
						<td class="center">
							<label> <input type="checkbox" class="ace" value="${jobInfoDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${jobInfoDomain.student.stuId }</td>
						<td>${jobInfoDomain.student.name }</td>
						<td>${cusfun:getNameByValueAndType(jobInfoDomain.student.sex,"8002")}</td>
						<td>${jobInfoDomain.student.classDomain.name }</td>
						<td>${cusfun:getNameByValueAndType(jobInfoDomain.contractStatus,"8003")}</td>
						<td>${jobInfoDomain.company }</td>
						<td>${cusfun:getNameByValueAndType(jobInfoDomain.protocalState,"8004")}</td>
						<td>${cusfun:getNameByValueAndType(jobInfoDomain.nowState,"8005")}</td>
						<td>${jobInfoDomain.salary }</td>
						<%-- <td>${jobInfoDomain.note }</td> --%>
						<td>${jobInfoDomain.modifyTime }</td>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewjobInfo('${jobInfoDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatejobInfo('${jobInfoDomain.id }')"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pageId"><tags:paged /></div>
	</div>
</form>
</div>

<script type="text/javascript">
	
	//list中修改就业信息按钮
	function updatejobInfo(jobInfoId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改就业信息',
	        shadeClose: true,
	        area : ['630px' , '480px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/jobInfo/jobInfoEdit/'+jobInfoId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//list中查看就业信息按钮
	function viewjobInfo(jobInfoId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看就业信息',
	        shadeClose: true,
	        area : ['630px' , '480px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/jobInfo/jobInfoView/'+jobInfoId
	    });
	}

</script>
