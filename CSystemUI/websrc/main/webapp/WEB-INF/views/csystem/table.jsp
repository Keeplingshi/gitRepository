<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>表格</title>
	<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ace/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ace/assets\css\cyrillic.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ace/assets/css/ace.min.css" />
	<script src="${pageContext.request.contextPath}/resources/ace/assets\js\jquery-2.0.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

</head>

<body>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">
									<label> 
										<input id="theadCheckbox" type="checkbox" class="ace" /> 
										<span class="lbl"></span>
									</label>
								</th>
								<th>Domain</th>
								<th>Price</th>
								<th class="hidden-480">Clicks</th>
								<th><i class="icon-time bigger-110 hidden-480"></i> Update</th>
								<th class="hidden-480">Status</th>
								<th></th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td class="center"><label> <input id="tbodyCheckbox1" type="checkbox"
										class="ace" /> <span class="lbl"></span>
								</label></td>

								<td><a href="#">ace.com</a></td>
								<td>$45</td>
								<td class="hidden-480">3,330</td>
								<td>Feb 12</td>

								<td class="hidden-480"><span
									class="label label-sm label-warning">Expiring</span></td>

								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-success">
											<i class="icon-ok bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-info">
											<i class="icon-edit bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-danger">
											<i class="icon-trash bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-warning">
											<i class="icon-flag bigger-120"></i>
										</button>
									</div>

									<div class="visible-xs visible-sm hidden-md hidden-lg">
										<div class="inline position-relative">
											<button class="btn btn-minier btn-primary dropdown-toggle"
												data-toggle="dropdown">
												<i class="icon-cog icon-only bigger-110"></i>
											</button>

											<ul
												class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
												<li><a href="#" class="tooltip-info" data-rel="tooltip"
													title="View"> <span class="blue"> <i
															class="icon-zoom-in bigger-120"></i>
													</span>
												</a></li>

												<li><a href="#" class="tooltip-success"
													data-rel="tooltip" title="Edit"> <span class="green">
															<i class="icon-edit bigger-120"></i>
													</span>
												</a></li>

												<li><a href="#" class="tooltip-error"
													data-rel="tooltip" title="Delete"> <span class="red">
															<i class="icon-trash bigger-120"></i>
													</span>
												</a></li>
											</ul>
										</div>
									</div>
								</td>
							</tr>

							<tr>
								<td class="center"><label> <input id="tbodyCheckbox2" type="checkbox"
										class="ace" /> <span class="lbl"></span>
								</label></td>

								<td><a href="#">base.com</a></td>
								<td>$35</td>
								<td class="hidden-480">2,595</td>
								<td>Feb 18</td>

								<td class="hidden-480"><span
									class="label label-sm label-success">Registered</span></td>

								<td>
									<div
										class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-success">
											<i class="icon-ok bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-info">
											<i class="icon-edit bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-danger">
											<i class="icon-trash bigger-120"></i>
										</button>

										<button class="btn btn-xs btn-warning">
											<i class="icon-flag bigger-120"></i>
										</button>
									</div>

									<div class="visible-xs visible-sm hidden-md hidden-lg">
										<div class="inline position-relative">
											<button class="btn btn-minier btn-primary dropdown-toggle"
												data-toggle="dropdown">
												<i class="icon-cog icon-only bigger-110"></i>
											</button>

											<ul
												class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
												<li><a href="#" class="tooltip-info" data-rel="tooltip"
													title="View"> <span class="blue"> <i
															class="icon-zoom-in bigger-120"></i>
													</span>
												</a></li>

												<li><a href="#" class="tooltip-success"
													data-rel="tooltip" title="Edit"> <span class="green">
															<i class="icon-edit bigger-120"></i>
													</span>
												</a></li>

												<li><a href="#" class="tooltip-error"
													data-rel="tooltip" title="Delete"> <span class="red">
															<i class="icon-trash bigger-120"></i>
													</span>
												</a></li>
											</ul>
										</div>
									</div>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /span -->
		</div>
		<!-- /row -->
	</div>

<input type="button" id="checkButton" value="删除"/>

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
	
</body>
</html>
