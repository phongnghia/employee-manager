<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Manager</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon"
	href='<c:url value = "/template/static/images/icon/icon-employee.png"/>'>
<!-- Link Default, Bootstrap 4 -->
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/libs/angular-material.min.css"/>'>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,400italic">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" />
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/libs/bootstrap.min.css"/>'>
<!-- Link CSS custom -->
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/css/dashboard.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/css/employee.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/css/detailemployee.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/css/team.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value = "/template/static/css/position.css"/>'>
<!-- AngularJS (Default, animation, messages, sanitize, route) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-animate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-aria.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-messages.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-sanitize.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.js"></script>
<script
	src='<c:url value = "/template/static/libs/angular-material.min.js"/>'></script>
<script
	src='<c:url value = "template/static/libs/ckeditor/ckeditor.js"/>'></script>
<script
	src='<c:url value = "template/static/libs/angular-ckeditor-master/angular-ckeditor.js"/>'></script>
</head>
<body class="bg-secondary" ng-app="myApp" ng-controller="appCtrl"
	ng-cloak>
	<div class="dashboard">
		<!-- Sidebar -->
		<div class="navbars">
			<div class="navbars__header">
				<div class="navbars__header__title">
					<div class="navbars__menu__close" ng-click="closeModal()">
						<i class="fas fa-times"></i>
					</div>
					<div class="navbars__header__img">
						<img src="<c:url value="/template/static/images/logo/eco-e.png"/>"
							alt="">
					</div>
					<div class="navbars__header__logo"></div>
					<label class="py-3">Employee Manager</label>
				</div>
			</div>
			<!-- Nav item -->
			<div class="navbars__body">
				<ul>
					<li><a class="navItem currentNavItem" href="#!"><i
							class="fas fa-chalkboard-teacher mr-3"></i>Employee</a></li>
					<li><a class="navItem" href="#!team"><i
							class="fas fa-users mr-3"></i>Team</a></li>
					<li><a class="navItem" href="#!position"><i
							class="fas fa-vote-yea mr-3"></i>Position</a></li>
					<li><a class="navItem" class="logout__dashboard" href="#"
						ng-click="showConfirm($event)"><i
							class="fas fa-sign-out-alt mr-3"></i>Logout</a></li>
				</ul>
			</div>
			<div class="navbars__footer"></div>
		</div>
		<!-- Dashboard -->
		<div class="dashboard__main">
			<!-- Header -->
			<div class="navbars__main">
				<div class="navbars__menu__open" ng-click="openModal()">
					<i class="fas fa-bars"></i>
				</div>
				<div>
					<label class="m-0 mr-3">{{dataUser.name}}</label> <img
						src="<c:url value="/template/static/images/user/employee.png"/>"
						alt="employee">
				</div>
			</div>
			<!-- Main dashboard -->
			<div class="main">
				<div class="main__app" ng-view></div>
			</div>
			<!-- Footer -->
			<footer class="footer">
				<div class="footer__title">
					<label class="m-0">Employee Manager</label>
				</div>
			</footer>
		</div>
	</div>

	<!-- Bootstrap 4 -->
	<script
		src='<c:url value = "/template/static/libs/jquery-3.5.1.slim.min.js"/>'></script>
	<script src='<c:url value = "/template/static/libs/bootstrap.min.js"/>'></script>
	<script src='<c:url value = "/template/static/libs/popper.min.js"/>'></script>

	<!-- JavaScript -->
	<script src='<c:url value = "/template/static/js/dashboard.js"/>'></script>
	<script src='<c:url value = "/template/static/js/employee.js"/>'></script>
	<script src='<c:url value = "/template/static/js/team.js"/>'></script>
	<script src='<c:url value = "/template/static/js/position.js"/>'></script>
	<script src='<c:url value = "/template/static/js/function.js"/>'></script>
	<script src='<c:url value = "/template/static/js/addemployee.js"/>'></script>
	<script src='<c:url value = "/template/static/js/detailemployee.js"/>'></script>
	<script src='<c:url value = "/template/static/js/dirPagination.js"/>'></script>

	<!-- File -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet"
		href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	<script src="https://code.angularjs.org/1.3.15/angular.js"></script>
	<script
		src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.1.min.js"></script>
</body>
</html>