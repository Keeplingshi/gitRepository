<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>		

<!-- 修改就业信息界面 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addEditView.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validform.js"></script>

<form id="jobInfoEditFormId" modelAttribute="domain" action="${pageContext.request.contextPath}/monitor/jobInfo/save" method="post">
	<input type="hidden" id="id" name="id" value="${jobInfoDomain.id }" />
	<input type="hidden" id="contractStatus" name="contractStatus" value="${jobInfoDomain.contractStatus }" />
	<input type="hidden" id="protocalState" name="protocalState" value="${jobInfoDomain.protocalState }" />
	<input type="hidden" id="nowState" name="nowState" value="${jobInfoDomain.nowState }" />
	<input type="hidden" id="isPositive" name="isPositive" value="${jobInfoDomain.isPositive }" />
	<table>
		<tr>
			<td class="lesta-150">姓名：</td>
			<td class="lestb">
				${jobInfoDomain.student.name }
			</td>
			<td class="lesta-150">学号：</td>
			<td class="lestb">
				${jobInfoDomain.student.stuId }
			</td>
		</tr>
		<tr>
			<td class="lesta-150">班级：</td>
			<td class="lestb">
				${jobInfoDomain.student.classDomain.name }
			</td>
			<td class="lesta-150">当前状态：</td>
			<td class="lestb">
				<select id="nowState_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${nowStateList }" var="nowStateDomain">
						<option value="${nowStateDomain.value }">${nowStateDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">签约状态：</td>
			<td class="lestb">
				<select id="contractStatus_select_edit_id" class="select_style" onchange="getProtocalState(this.value)">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${contractStatusList }" var="contractStatusDomain">
						<option value="${contractStatusDomain.value }">${contractStatusDomain.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="lesta-150">协议书状态：</td>
			<td class="lestb">
				<select id="protocalState_select_edit_id" class="select_style">
					<option value="" selected="selected">选择</option>
					<c:forEach items="${protocalStateList }" var="protocalStateDomain">
						<option value="${protocalStateDomain.value }">${protocalStateDomain.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">签约单位：</td>
			<td class="lestb" colspan="3">
				<input type="text" id="company" name="company" class="input_text_a" placeholder="请输入签约单位" value="${jobInfoDomain.company }" style="width:300px;">
			</td>
		</tr>
		<tr>
			<td class="lesta-150">薪水/月：</td>
			<td class="lestb">
				<input type="text" id="salary" name="salary" class="input_text_a" datatype="n" ignore="ignore" placeholder="请输入薪水" value="${jobInfoDomain.salary }" />
			</td>
		</tr>
		<tr>
			<td class="lesta-150">派遣地址：</td>
			<td class="lestb" colspan="3">
				<input type="text" id="city" name="city" class="input_text_a" placeholder="便于统计档案派遣" value="${jobInfoDomain.city }" style="width:300px;"/>
			</td>
		</tr>
		<tr>
			<td class="lesta-150">备注：</td>
			<td class="lestb" colspan="3" rowspan="2">
				<textarea rows="5" cols="50" id="note" name="note" style="margin-top: 20px;">${jobInfoDomain.note }</textarea>
			</td>
		</tr>
	</table>
	<input id="saveButton" type="button" class="button button-highlight button-rounded button-small" style="margin-top:30px; margin-left: 270px;" value="确定"/>
</form>

<script>

	//表单验证
	$.Tipmsg.r=null;
	
	var showmsg=function(msg,obj){
		layer.tips(msg, obj);
	};

	$("#jobInfoEditFormId").Validform({
		tiptype:function(msg,o){
			showmsg(msg,o.obj[0]);
		}
	});


	//初始化赋值
	$(function(){
		$("#nowState_select_edit_id option[value='${jobInfoDomain.nowState}']").attr("selected",true);
		$("#contractStatus_select_edit_id option[value='${jobInfoDomain.contractStatus}']").attr("selected",true);
		$("#protocalState_select_edit_id option[value='${jobInfoDomain.protocalState}']").attr("selected",true);
	});

	//下拉框选择后给隐藏域赋值
	$("#nowState_select_edit_id").change(function(){
		var nowState_value=$(this).children('option:selected').val();
		$("#nowState").val(nowState_value);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#contractStatus_select_edit_id").change(function(){
		var contractStatus_value=$(this).children('option:selected').val();
		$("#contractStatus").val(contractStatus_value);
	});
	
	//下拉框选择后给隐藏域赋值
	$("#protocalState_select_edit_id").change(function(){
		
		var contractStatus_value=$("#contractStatus").val();
		if(contractStatus_value==''||contractStatus_value==null){
			layer.tips('请先选择签约状态', '#contractStatus_select_edit_id');
			return ;
		}
		
		var protocalState_value=$(this).children('option:selected').val();
		$("#protocalState").val(protocalState_value);
	});
	
	$("#saveButton").click(function(){
		
 		var salaryVal=$("#salary").val();	//薪水
 		if(salaryVal!=null&&salaryVal!=''){
 			if(!isNumber(salaryVal)){
 				layer.tips('请输入整数', '#salary');
 				return;
 			}
 		}

		var form = $("#jobInfoEditFormId");
		form.ajaxSubmit(function(result){
			if(result=='success'){

				parent.layer.msg('修改成功', {
					offset: ['260px'],
     		        time: 1500//1.5s后自动关闭
     		    });
				//关闭当前新增页面页
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭    
			}else{
				layer.msg('修改失败',{
					offset: ['260px'],
	     		    time: 1500
	     		});
			}
		});
		
	});

	//根据签约状态获取签约书状态
	function getProtocalState(contractStatus_value)
	{
    	$.ajax({
			url:'${pageContext.request.contextPath}/common/getProtocalState?contractStatusValue='+contractStatus_value,
			type:"post",
			error:function(e){
			},
			success:function(data){
				var json = new Function("return" + data)();
 				var major_select=$("#protocalState_select_edit_id");
				major_select.empty();
				major_select.append('<option value="">'+"选择"+'</option>');
				for(var i=0;i<json.length;i++){
					major_select.append('<option value="'+json[i].selectText+'">'+json[i].selectValue+'</option>');
				}
				$("#protocalState").val('');
			}
		});
	}
	
</script>