<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>

	<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/ace/assets/css/font-awesome.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/ace/assets/css/ace.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/ace/assets\css\cyrillic.css" rel="stylesheet"/>
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ace/assets/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ace/assets/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ace/assets/js/ace-extra.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ace/assets/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ace/assets/js/jquery.dataTables.bootstrap.js"></script>
		
	</head>

	<body>

		<table id="sample-table-2" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label> 
							<input type="checkbox" class="ace" />
							<span class="lbl"></span>
						</label>
					</th>
					<th>Domain</th>
					<th>Price</th>
					<th>Clicks</th>
					<th>Update</th>
					<th>Status</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td class="center"><label> <input type="checkbox"
							class="ace" /> <span class="lbl"></span>
					</label></td>

					<td><a href="#">app.com</a></td>
					<td>$45</td>
					<td class="hidden-480">3,330</td>
					<td>Feb 12</td>

					<td class="hidden-480"><span
						class="label label-sm label-warning">Expiring</span></td>

					<td>
					</td>
				</tr>

				<tr>
					<td class="center"><label> <input type="checkbox"
							class="ace" /> <span class="lbl"></span>
					</label></td>

					<td><a href="#">base.com</a></td>
					<td>$35</td>
					<td class="hidden-480">2,595</td>
					<td>Feb 18</td>

					<td class="hidden-480"><span
						class="label label-sm label-success">Registered</span></td>

					<td>
					</td>
				</tr>

				<tr>
					<td class="center"><label> <input type="checkbox"
							class="ace" /> <span class="lbl"></span>
					</label></td>

					<td><a href="#">once.com</a></td>
					<td>$20</td>
					<td class="hidden-480">1,400</td>
					<td>Apr 04</td>

					<td class="hidden-480"><span
						class="label label-sm label-info arrowed arrowed-righ">Sold</span>
					</td>

					<td>
					</td>
				</tr>
			</tbody>
		</table>


	<script type="text/javascript">
		jQuery(function($) {
			var oTable1 = $('#sample-table-2').dataTable({
				"aoColumns" : [ {
					"bSortable" : false
				}, null, null, null, null, null, {
					"bSortable" : false
				} ]
			});
		})
	</script>

</body>
</html>