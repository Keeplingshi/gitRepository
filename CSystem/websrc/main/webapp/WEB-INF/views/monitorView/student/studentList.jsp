<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 学生列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@	taglib uri="/csystem-taglib" prefix="cusfun" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<div>
<form id="formId" action="${pageContext.request.contextPath}/monitor/student/studentSearchList" method="post">
	<input type="hidden" id="sortMode" name="sortMode" value="${sortMode }" />
	<input type="hidden" id="sortValue" name="sortValue" value="${sortValue }" />
	<div class="breadcrumbs" id="studentListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>
	
		<input id="studentAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="studentQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="breadcrumbs">
		<input id="studentDBToExcelButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="导出数据"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-2" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th class="center" style="width: 80px;">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th style="width: 120px;">学号
						<span>
							<c:choose>
								<c:when test="${sortMode=='asc'&&sortValue=='stuId' }">
									<img id="img_stuId_asc" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_up_24.png">
								</c:when>
								<c:when test="${sortMode=='desc'&&sortValue=='stuId' }">
									<img id="img_stuId_desc" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_down_24.png">
								</c:when>
								<c:otherwise>
									<img id="img_stuId" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_24.png">
								</c:otherwise>
							</c:choose>
						</span>
					</th>
					<th style="width: 80px;">姓名</th>
					<th style="width: 50px;">性别</th>
<%-- 					<th>出生日期
						<span>
							<c:choose>
								<c:when test="${sortMode=='asc'&&sortValue=='birthday' }">
									<img id="img_birthday_asc" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_up_24.png">
								</c:when>
								<c:when test="${sortMode=='desc'&&sortValue=='birthday' }">
									<img id="img_birthday_desc" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_down_24.png">
								</c:when>
								<c:otherwise>
									<img id="img_birthday" style="float: right;" src="${pageContext.request.contextPath}/resources/images/sorticon/table_sort_24.png">
								</c:otherwise>
							</c:choose>
						</span>
					</th> --%>
					<th style="width: 90px;">政治面貌</th>
					<!-- <th>身份证号</th> -->
					<th>籍贯</th>
					<th style="width: 90px;">宿舍</th>
					<!-- <th>年级</th> -->
<!-- 					<th>院系</th>
					<th>专业</th> -->
					<th>班级</th>
					<!-- <th>电子邮件</th> -->
					<th>教学班级</th>
					<!-- <th>手机</th> -->
					<th style="width: 200px;">操作</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${studentList }" var="studentDomain">
					<tr>
						<td class="center">
						<label> <input type="checkbox" class="ace" value="${studentDomain.id }"/> <span class="lbl"></span></label>
						</td>
						<td>${studentDomain.stuId }</td>
						<td>${studentDomain.name }</td>
						<td>${cusfun:getNameByValueAndType(studentDomain.sex,"8002")}</td>
						<%-- <td><fmt:formatDate value="${studentDomain.birthday }" type="date"/></td> --%>
						<td>${cusfun:getNameByValueAndType(studentDomain.politicalStatus,"8001")}</td>
						<%-- <td>${studentDomain.IDnumber }</td> --%>
						<td>${studentDomain.nativePlace }</td>
						<td>${studentDomain.dormitory }</td>
						<%-- <td>${studentDomain.classDomain.grade.grade }</td> --%>
<%-- 						<td>${studentDomain.classDomain.major.college.name }</td>
						<td>${studentDomain.classDomain.major.name }</td> --%>
						<td>${studentDomain.classDomain.name }</td>
						<%-- <td style="overflow:hidden;text-overflow:ellipsis;">${studentDomain.email }</td> --%>
						<td>${studentDomain.teachClass }</td> 
						<%-- <td>${studentDomain.cellphone }</td> --%>
						<td>
							<input type="button" class="btn_list_view" value="查看" onclick="viewstudent('${studentDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatestudent('${studentDomain.id }')"/>  
							<%-- <input type="button" class="btn_list_delete" value="删除" onclick="deletestudent('${studentDomain.id }')"/> --%>
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

	$("#sample-table-2 thead tr th img").click(function(){
		
		var sortValueVal=$(this)[0].id.split("_")[1];
		var sortModeVal=$(this)[0].id.split("_")[2];
		$("#sortValue").val(sortValueVal);
		if(sortModeVal=='asc'){
			$("#sortMode").val('desc');
		}else{
			$("#sortMode").val('asc');
		}
		
 		//默认加载学生列表
		$("#formId").ajaxSubmit(function(data){
    	 	$("#content_page").html(data);
		}); 
	});

	//查询
	$("#studentQueryButton").click(function(){
		$("#formId").ajaxSubmit(function(data){
		 	$("#content_page").html(data);
		});
	});

	//新增学生按钮
	$("#studentAddButton").click(function(){
	    parent.layer.open({
	        type: 2,
	        title: '新增学生',
	        shadeClose: true, //点击遮罩关闭层
	        area : ['700px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/monitor/student/studentAdd',
	        end: function(){
				//默认加载学生列表
	    		$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	});
	
	//list中查看学生按钮
	function viewstudent(studentId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '查看学生',
	        shadeClose: true,
	        area : ['700px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/monitor/student/studentView/'+studentId
	    });
	}

	//list中修改用户按钮
	function updatestudent(studentId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改学生',
	        shadeClose: true,
	        area : ['700px' , '500px'],
	        offset: ['100px'],
	        content: '${pageContext.request.contextPath}/monitor/student/studentEdit/'+studentId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//导出信息
	$("#studentDBToExcelButton").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/monitor/student/studentDBToExcel",
 			type:"POST",
			error: function(){
				layer.msg('请求出错，导出失败', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
            },   
            success:function(result){
            	if(result=='success'){

    				layer.msg('导出成功', {
    					offset: ['260px'],
         		        time: 1500//1.5s后自动关闭
         		    });
    				
    				window.location="${pageContext.request.contextPath}/monitor/student/downloadStudentInfo";

    			}else{
    				layer.msg('导出失败', {
    					offset: ['260px'],
         		        time: 1500//1.5s后自动关闭
         		    });
    			}      
            }
		});
	});
	
	//点击表格标题栏，选中所有checkbox框
	$('table th input:checkbox').on('click' , function(){
		
		var that=this;		
 		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

</script>
