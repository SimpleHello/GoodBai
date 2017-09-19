<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" type="image/x-icon" href="image/favicon1.ico">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录管理</title>

<!-- CSS 
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
-->
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/form-elements.css">
<link rel="stylesheet" href="css/style.css">
</head>

<body style="background-image:url('image/1.jpg')">
	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>HaIm</strong> Login Form
						</h1>
						<div class="description">
							<p>
								This is a free responsive login form made with Bootstrap.
								Download it on <a href="http://azmind.com"><strong>AZMIND</strong></a>,
								customize and use it as you like!
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Login to our site</h3>
								<p>Enter your username and password to log on:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">Username</label> <input
										type="text" name="name" placeholder="Username..."
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label> <input
										type="password" name="password" placeholder="Password..."
										class="form-password form-control" id="form-password">
								</div>
								<button type="button" class="btn" id="sys_login" onclick="sys_login()">Sign in!</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="backstretch"
		style="left: 0px; top: 0px; overflow: hidden; margin: 0px; padding: 0px; height: 520px; width: 1903px; z-index: -999999; position: fixed;" />
</body>

</html>