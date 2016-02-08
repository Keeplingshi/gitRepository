<%-- 
    Document   : page
    Created on : 2016-02-08
    Author     : chenbin
--%>

<%@tag description="分页标签" pageEncoding="UTF-8" body-content="empty"%>
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/ace/assets/css/ace.min.css" rel="stylesheet"/>

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
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
						Page <input class="ui-pg-input" type="text" size="2" maxlength="7" value="0"> of 
						<span id="sp_1_grid-pager">3</span>
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
						<select class="ui-pg-selbox">
							<option value="10" selected="selected">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
<script>
	$("#first_grid-pager").click(function(){
		
	});
</script>