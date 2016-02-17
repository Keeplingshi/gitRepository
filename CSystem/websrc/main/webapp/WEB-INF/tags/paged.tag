<%-- 
    Document   : page
    Created on : 2016-02-08
    Author     : chenbin
--%>

<%@tag description="分页标签" pageEncoding="UTF-8" body-content="empty"%>
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/ace.min.css" rel="stylesheet"/>

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>

<style type="text/css">
	.pageback{
		height: 40px;
		padding-top: 3px;
		background: rgba(7, 79, 169, 0.12);
	}
</style>

	<div >
		<table class="ui-pg-table" style="margin:auto;">
			<tbody>
				<tr>
					<td id="first_grid-pager" class="ui-pg-button ui-corner-all" style="cursor: default;">
						<span class="ui-icon icon-double-angle-left bigger-140"></span>
					</td>
					<td id="prev_grid-pager" class="ui-pg-button ui-corner-all" style="cursor: default;">
						<span class="ui-icon icon-angle-left bigger-140"></span>
					</td>
					<td class="ui-pg-button ui-state-disabled" style="width: 4px; cursor: default;">
						<span class="ui-separator"></span>
					</td>
					<td dir="ltr">
						Page <input id="ui-pg-input" class="ui-pg-input" type="text" size="2" maxlength="7" value="${pageInfo.currentPageNo }" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" onkeypress="EnterPress(event)" onkeydown="EnterPress()" > of 
						<span id="sp_1_grid-pager">${pageInfo.totalPages }</span>
					</td>
					<td class="ui-pg-button ui-state-disabled" style="width: 4px; cursor: default;">
						<span class="ui-separator"></span>
					</td>
					<td id="next_grid-pager" class="ui-pg-button ui-corner-all" style="cursor: default;">
						<span class="ui-icon icon-angle-right bigger-140"></span>
					</td>
					<td id="last_grid-pager" class="ui-pg-button ui-corner-all" style="cursor: default;">
						<span class="ui-icon icon-double-angle-right bigger-140"></span>
					</td>
					<td dir="ltr">
						<select id="pg-selbox" class="ui-pg-selbox">
							<option value="10" selected="selected">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		<input type='text' style='display:none'/>
		<input type="hidden" id="currentPageNo" name="pageInfo.currentPageNo" value="${pageInfo.currentPageNo}" >
		<input type="hidden" id="sizePerPage" name="pageInfo.sizePerPage" value="${pageInfo.sizePerPage}" >
		<input type="hidden" id="totalPages" name="pageInfo.totalPages" value="${pageInfo.totalPages}" >
	</div>
	
<script>

	$(function(){
		var pageSize = "${pageInfo.sizePerPage}";
		$("#pg-selbox option[value='"+pageSize+"']").attr("selected",true);
	});

	$("#pg-selbox").change(function(){
		var sizePerPage=$(this).children('option:selected').val();
		
		$("#sizePerPage").val(sizePerPage);
		doPage();
	});

	$("#first_grid-pager").click(function(){
		$("#currentPageNo").val("1");
		layer.msg("第一页！", {
			offset: ['260px'],
			time: 1500//1.5s后自动关闭
		});
		doPage();
	});
	
	$("#prev_grid-pager").click(function(){
		if("${pageInfo.currentPageNo}"==1){
			layer.msg("当前为第一页！", {
				offset: ['260px'],
				time: 1500//1.5s后自动关闭
			});
			return;
		}
		$("#currentPageNo").val("${pageInfo.currentPageNo-1}");
		doPage();
	});
	
	$("#next_grid-pager").click(function(){
 		if("${pageInfo.currentPageNo}"=="${pageInfo.totalPages}"){
			layer.msg("当前为最后一页！", {
				offset: ['260px'],
				time: 1500//1.5s后自动关闭
			});
			return;
		}
		$("#currentPageNo").val("${pageInfo.currentPageNo+1}");
		doPage();
	});
	
	$("#last_grid-pager").click(function(){
		$("#currentPageNo").val("${pageInfo.totalPages}");
		layer.msg("最后一页！", {
			offset: ['260px'],
			time: 1500//1.5s后自动关闭
		});
		doPage();
	});
	
	//输入页数，回车响应时间
	function EnterPress(e){ //传入 event 
		var e = e || window.event; 
		if(e.keyCode == 13){
			$("#currentPageNo").val($("#ui-pg-input").val());
			doPage();
		}
	} 
	
	//翻页
	function doPage()
	{
		var form = $("#formId");
    	form.ajaxSubmit(function(data){
    	 	var $target = $("#content_page");
  	   	 	$target.html(data);
		});
	}
	
</script>