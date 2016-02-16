<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 学生列表页面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/globle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />

<div>
<form id="formId" action="${pageContext.request.contextPath}/student/studentSearchList" method="post">
	<input type="hidden" id="majorId" name="majorId" value="${majorId }" />
	<input type="hidden" id="collegeId" name="collegeId" value="${collegeId }" />
	<input type="hidden" id="classId" name="classId" value="${classId }" />
	<div class="breadcrumbs" id="studentListToolbar">
	
		<span class="input-icon" style="margin: 5px;">
			<input type="text" id="nav-search-input" name="searchText" placeholder="Search ..." class="nav-search-input" autocomplete="off" value="${searchText }"/> 
			<i class="icon-search nav-search-icon"></i>
		</span>

		<label style="margin-left: 20px;">学院：</label>
		<select id="college_select_id" style="width: 100px;" onchange="getMajor(this.value)">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${collegeList }" var="collegeDomain">
				<option value="${collegeDomain.id }">${collegeDomain.name}</option>
			</c:forEach>
		</select>
		
		<label style="margin-left: 20px;">专业：</label>
		<select id="major_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${majorList }" var="majorItem">
				<option value="${majorItem.selectText }">${majorItem.selectValue}</option>
			</c:forEach>
		</select>
	
		<label style="margin-left: 20px;">班级：</label>
		<select id="class_select_id" style="width: 100px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${classList }" var="classItem">
				<option value="${classItem.id }">${classItem.name}</option>
			</c:forEach>
		</select>
	
		<input id="studentDeleteButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="删除"/>
		<input id="studentAddButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="新增"/>
		<input id="studentQueryButton" type="button" class="button button-primary button-rounded button-small" style="margin: 5px;float: right;" value="查询"/>
	</div>
	<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> <input id="theadCheckbox" type="checkbox" class="ace" /> <span class="lbl"></span></label>
					</th>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>出生日期</th>
					<th>政治面貌</th>
					<th>身份证号</th>
					<th>籍贯</th>
					<th>宿舍</th>
					<th>年级</th>
					<th>院系</th>
					<th>专业</th>
					<th>班级</th>
					<th>电子邮件</th>
					<th>联系电话</th>
					<th>手机</th>
					<th>操作</th>
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
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td>${studentDomain.name }</td>
						<td style="width: 260px">
							<input type="button" class="btn_list_view" value="查看" onclick="viewstudent('${studentDomain.id }')"/>
							<input type="button" class="btn_list_update" value="修改" onclick="updatestudent('${studentDomain.id }')"/>  
							<input type="button" class="btn_list_delete" value="删除" onclick="deletestudent('${studentDomain.id }')"/>
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

	//使权限下拉框默认选择
	$(function(){
		$("#college_select_id option[value='${collegeId}']").attr("selected",true);
		$("#major_select_id option[value='${majorId}']").attr("selected",true);
	});
	
 	//下拉框选择后给隐藏域赋值
	$("#major_select_id").change(function(){
		var majorIdVal=$(this).children('option:selected').val();
		$("#majorId").val(majorIdVal);
	});
	$("#college_select_id").change(function(){
		var collegeIdVal=$(this).children('option:selected').val();
		$("#collegeId").val(collegeIdVal);
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
	        content: '${pageContext.request.contextPath}/student/studentAdd',
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
	        area : ['380px' , '300px'],
	        content: '${pageContext.request.contextPath}/student/studentView/'+studentId
	    });
	}

	//list中修改用户按钮
	function updatestudent(studentId)
	{
	    parent.layer.open({
	        type: 2,
	        title: '修改学生',
	        shadeClose: true,
	        area : ['380px' , '320px'],
	        content: '${pageContext.request.contextPath}/student/studentEdit/'+studentId,
	        end: function(){
	        	//默认加载用户列表
	        	$("#formId").ajaxSubmit(function(data){
	        	 	$("#content_page").html(data);
	    		});
	        }
	    });
	}
	
	//删除
	function deletestudent(studentId)
	{
		//询问框
		layer.confirm('是否确定删除？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
	 		//默认加载学生列表
			$.post("${pageContext.request.contextPath}/student/delete/"+studentId, function(result){
				if(result=='success'){
					//默认加载学生列表
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
	
	
	//多选删除
	$("#studentDeleteButton").click(function(){
		var checkBoxs=$("table tbody input:checkbox");
		var studentIds=new Array();
		for(var i=0;i<checkBoxs.length;i++)
		{
			var checkBox=checkBoxs[i];
			if(checkBox.checked){
				studentIds.push(checkBox.value);
			}
		}
		if(studentIds.length=='0'){
			layer.msg('请至少选择一个');
			return;
		}
		
		//询问框
		layer.confirm('是否确定删除这些学生？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
			console.info("确定");
			$.ajax({
				url : "${pageContext.request.contextPath}/student/deleteClasses",
				async: false,
				data : {
					"studentIds" : studentIds
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

	//选择学院，得到专业
	function getMajor(college_id)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/major/getMajorByCollege?college_id='+college_id,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var major_select=$("#major_select_id");
				major_select.empty();
				major_select.append('<option value="">'+"全部"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				} 
			}
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
