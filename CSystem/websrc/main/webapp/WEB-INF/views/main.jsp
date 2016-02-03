<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/ace/assets/css/font-awesome.min.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/ace/assets/css/ace.min.css" rel="stylesheet"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ace/assets\css\cyrillic.css" />

		<script src="${pageContext.request.contextPath}/resources/ace/assets/js/jquery-2.0.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/ace/assets/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar" style="height: 45px">

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							CSystem管理系统
						</small>
					</a>
				</div>
				
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${pageContext.request.contextPath}/resources/ace/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									Jason
								</span>
								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="icon-cog"></i>设置
									</a>
								</li>
								<li>
									<a href="#">
										<i class="icon-user"></i>个人资料
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="#">
										<i class="icon-off"></i>退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<div class="main-container-inner">
				<div class="sidebar" id="sidebar">
					<ul class="nav nav-list">
						<li>
							<a href="javascript:void(0);">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 菜单 </span>
							</a>
						</li>
						<li id="user_manage">
							<a href="javascript:void(0);">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 账户管理 </span>
							</a>
						</li>
						<li id="job_manage">
							<a href="javascript:void(0);">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 就业管理 </span>
							</a>
						</li>
					</ul>
				</div>

				<div class="main-content">
					<div id="content_page"></div>
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
	</body>
	
	<script>
	
		$("#user_manage").click(function(){
			$(this).addClass("active");
			$.post("${pageContext.request.contextPath}/user/userList", function(result){
				$("#content_page").html(result);
			});
		});
	
	</script>
	
</html>

