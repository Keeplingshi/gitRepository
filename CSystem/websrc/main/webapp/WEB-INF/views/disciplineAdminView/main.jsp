<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 管理系统主界面 -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/ace/assets/css/font-awesome.min.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/ace/assets/css/ace.min.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/ace/assets\css\cyrillic.css" rel="stylesheet"/>

		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/ace/assets/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/ace/assets/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/resources/ace/assets/js/ace-extra.min.js"></script>
	
		<title>CSystem管理系统</title>
	
		<script type="text/javascript">
			$(function(){
				//默认加载用户列表
				$.post("${pageContext.request.contextPath}/disciplineAdmin/discipline/disciplineList", function(result){
					$("#content_page").empty();
					$("#content_page").html(result);
				});
			});
		</script>
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
									${userDomain.username}
								</span>
								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li id="user_password">
									<a href="javascript:void(0);">
										<i class="icon-cog"></i>修改密码
									</a>
								</li>
								<li class="divider"></li>
								<li id="user_logout">
									<a href="javascript:void(0);">
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
					<ul id="menulist" class="nav nav-list">
						<li>
							<a href="javascript:void(0);">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 菜单 </span>
							</a>
						</li>
						<li id="discipline_manage" class="active">
							<a href="javascript:void(0);">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 违纪管理 </span>
							</a>
						</li>
					</ul>
					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>
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
		
		//使菜单栏只有一项被选中
		$("#menulist li").click(function(){
			if($(this).is('[id]')){
				$("#menulist li").removeClass("active");
				$(this).addClass("active");
				
			}
		});
		
		//修改密码
		$("#user_password").click(function(){
		    parent.layer.open({
		        type: 2,
		        title: '修改密码',
		        shadeClose: true,
		        area : ['360px' , '320px'],
		        offset: ['100px'],
		        content: '${pageContext.request.contextPath}/common/modifyPasswordView',
		        end: function(){
		        	
		        }
		    });
		});
		
		//登出
		$("#user_logout").click(function(){
			window.location.href="${pageContext.request.contextPath}/logout";
		});

		//违纪管理
		$("#discipline_manage").click(function(){
			//默认加载用户列表
			$.post("${pageContext.request.contextPath}/disciplineAdmin/discipline/disciplineList", function(result){
				$("#content_page").empty();
				$("#content_page").html(result);
			});
		});

	
	</script>
	
</html>

